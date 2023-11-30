package com.mindskip.xzs.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.mindskip.xzs.domain.*;
import com.mindskip.xzs.domain.other.KeyValue;
import com.mindskip.xzs.domain.enums.QuestionStatusEnum;
import com.mindskip.xzs.domain.enums.QuestionTypeEnum;
import com.mindskip.xzs.domain.question.QuestionItemObject;
import com.mindskip.xzs.domain.question.QuestionObject;
import com.mindskip.xzs.repository.QuestionMapper;
import com.mindskip.xzs.service.QuestionService;
import com.mindskip.xzs.service.SubjectService;
import com.mindskip.xzs.service.TagService;
import com.mindskip.xzs.service.TextContentService;
import com.mindskip.xzs.utility.*;
import com.mindskip.xzs.viewmodel.admin.question.QuestionEditItemVM;
import com.mindskip.xzs.viewmodel.admin.question.QuestionEditRequestVM;
import com.mindskip.xzs.viewmodel.admin.question.QuestionPageRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuestionServiceImpl extends BaseServiceImpl<Question> implements QuestionService {

    protected final static ModelMapper modelMapper = ModelMapperSingle.Instance();
    private final QuestionMapper questionMapper;
    private final TextContentService textContentService;
    private final SubjectService subjectService;

    @Autowired
    private TagService tagService;

    @Autowired
    public QuestionServiceImpl(QuestionMapper questionMapper, TextContentService textContentService, SubjectService subjectService) {
        super(questionMapper);
        this.textContentService = textContentService;
        this.questionMapper = questionMapper;
        this.subjectService = subjectService;
    }

    @Override
    public PageInfo<Question> page(QuestionPageRequestVM requestVM) {
        //startPage(当前页码,页大小,)
//        PageInfo<Question> pageInfo = PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(new ISelect() {
//            @Override
//            public void doSelect() {
//                questionMapper.page(requestVM);
//            }
//        });

        //lambda写法
        PageInfo<Question> pageInfo = PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(
                ()->questionMapper.page(requestVM)
        );
        return pageInfo;
    }


    @Override
    @Transactional
    public Question insertFullQuestion(QuestionEditRequestVM model, String userId) {
        Date now = new Date();
        Integer gradeLevel = subjectService.levelBySubjectId(model.getSubjectId());

        //题干、解析、选项等 插入
        TextContent infoTextContent = new TextContent();
        infoTextContent.setCreateTime(now);
        setQuestionInfoFromVM(infoTextContent, model);
        textContentService.insertByFilter(infoTextContent);

        Question question = new Question();
        question.setSubjectId(model.getSubjectId());
        question.setGradeLevel(gradeLevel);
        question.setCreateTime(now);
        question.setQuestionType(model.getQuestionType());
        question.setStatus(QuestionStatusEnum.OK.getCode());
        question.setCorrectFromVM(model.getCorrect(), model.getCorrectArray());
        question.setScore(ExamUtil.scoreFromVM(model.getScore()));
        question.setDifficult(model.getDifficult());
        question.setInfoTextContentId(infoTextContent.getId());
        question.setCreateUser(userId);
        question.setDeleted(false);
        questionMapper.insertSelective(question);
        return question;
    }

    @Override
    @Transactional
    public Question updateFullQuestion(QuestionEditRequestVM model) {
        Integer gradeLevel = subjectService.levelBySubjectId(model.getSubjectId());//年级
        Question question = questionMapper.selectByPrimaryKey(model.getId());
        question.setSubjectId(model.getSubjectId());
        question.setGradeLevel(gradeLevel);
        question.setScore(ExamUtil.scoreFromVM(model.getScore()));
        question.setDifficult(model.getDifficult());
        question.setCorrectFromVM(model.getCorrect(), model.getCorrectArray());
        question.setTags(model.getTags());
        question.setTagsName(model.getTagsName());
        questionMapper.updateByPrimaryKeySelective(question);

        //题干、解析、选项等 更新
        TextContent infoTextContent = textContentService.selectById(question.getInfoTextContentId());
        setQuestionInfoFromVM(infoTextContent, model);
        textContentService.updateByIdFilter(infoTextContent);

        return question;
    }

    @Override
    public QuestionEditRequestVM getQuestionEditRequestVM(String questionId) {
        //题目映射
        Question question = questionMapper.selectByPrimaryKey(questionId);
        return getQuestionEditRequestVM(question);
    }

    @Override
    public QuestionEditRequestVM getQuestionEditRequestVM(Question question) {
        //题目映射
        TextContent questionInfoTextContent = textContentService.selectById(question.getInfoTextContentId());
        QuestionObject questionObject = JsonUtil.toJsonObject(questionInfoTextContent.getContent(), QuestionObject.class);
        QuestionEditRequestVM questionEditRequestVM = modelMapper.map(question, QuestionEditRequestVM.class);
        questionEditRequestVM.setTitle(questionObject.getTitleContent());

        //答案
        QuestionTypeEnum questionTypeEnum = QuestionTypeEnum.fromCode(question.getQuestionType());
        switch (questionTypeEnum) {
            case SingleChoice:
            case TrueFalse:
                questionEditRequestVM.setCorrect(question.getCorrect());
                break;
            case MultipleChoice:
                questionEditRequestVM.setCorrectArray(ExamUtil.contentToArray(question.getCorrect()));
                break;
            case GapFilling:
                List<String> correctContent = questionObject.getQuestionItemObjects().stream().map(d -> d.getContent()).collect(Collectors.toList());
                questionEditRequestVM.setCorrectArray(correctContent);
                break;
            case ShortAnswer:
                questionEditRequestVM.setCorrect(questionObject.getCorrect());
                break;
            default:
                break;
        }
        questionEditRequestVM.setScore(ExamUtil.scoreToVM(question.getScore()));
        questionEditRequestVM.setAnalyze(questionObject.getAnalyze());


        //题目项映射
        List<QuestionEditItemVM> editItems = questionObject.getQuestionItemObjects().stream().map(o -> {
            QuestionEditItemVM questionEditItemVM = modelMapper.map(o, QuestionEditItemVM.class);
            if (o.getScore() != null) {
                questionEditItemVM.setScore(ExamUtil.scoreToVM(o.getScore()));
            }
            return questionEditItemVM;
        }).collect(Collectors.toList());
        questionEditRequestVM.setItems(editItems);
        return questionEditRequestVM;
    }

    public void setQuestionInfoFromVM(TextContent infoTextContent, QuestionEditRequestVM model) {
        List<QuestionItemObject> itemObjects = model.getItems().stream().map(i ->
                {
                    QuestionItemObject item = new QuestionItemObject();
                    item.setPrefix(i.getPrefix());
                    item.setContent(i.getContent());
                    item.setItemUuid(i.getItemUuid());
                    item.setScore(ExamUtil.scoreFromVM(i.getScore()));
                    return item;
                }
        ).collect(Collectors.toList());
        QuestionObject questionObject = new QuestionObject();
        questionObject.setQuestionItemObjects(itemObjects);
        questionObject.setAnalyze(model.getAnalyze());
        questionObject.setTitleContent(model.getTitle());
        questionObject.setCorrect(model.getCorrect());
        infoTextContent.setContent(JsonUtil.toJsonStr(questionObject));
    }

    @Override
    public Integer selectAllCount() {
        return questionMapper.selectAllCount();
    }

    @Override
    public List<Integer> selectMothCount() {
        Date startTime = DateTimeUtil.getMonthStartDay();
        Date endTime = DateTimeUtil.getMonthEndDay();
        List<String> mothStartToNowFormat = DateTimeUtil.MothStartToNowFormat();
        List<KeyValue> mouthCount = questionMapper.selectCountByDate(startTime, endTime);
        return mothStartToNowFormat.stream().map(md -> {
            KeyValue keyValue = mouthCount.stream().filter(kv -> kv.getName().equals(md)).findAny().orElse(null);
            return null == keyValue ? 0 : keyValue.getValue();
        }).collect(Collectors.toList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public void batchImportQuestionFromExcel(File file, User user) {

        FileInputStream fis = null;
        XSSFWorkbook workbook = null;
        try {
            fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建工作簿

        //从第二行开始获取内容
        Sheet sheet = workbook.getSheetAt(0);
        // 获取表中的内容
        int rowCount = sheet.getPhysicalNumberOfRows(); //多少行

        //
        SnowFlakeGenerateIDUtil IDGenerator = new SnowFlakeGenerateIDUtil();

        //查询已有的学科以及知识点
        List<Subject> subjects = subjectService.allSubject();
        List<Tag> tags = tagService.allTags();
        List<Question> questionList = new ArrayList<>();
        List<TextContent> textContentList = new ArrayList<>();
        //都转换为map
        Map<String,Subject> subjectMap = new HashMap<>();
        for (Subject subject : subjects) {
            subjectMap.put(subject.getName(),subject);
        }
        Map<String,Tag> tagMap = new HashMap<>();
        for (Tag tag : tags) {
            tagMap.put(tag.getTagName(),tag);
        }

        for (int i = 1; i < rowCount; i++) {
            Row rowData = sheet.getRow(i);

            //question.setCreateTime(DateTimeUtil.dateFormat(new Date()));
            if (rowData != null) {
                //每一行记录穿件question对象和textContent对象，存放到list中
                Question question = new Question();
                question.setId(IDGenerator.generateID());
                question.setCreateUser(user.getId());
                question.setCreateTime(new Date());
                question.setStatus(1);
                question.setDeleted(false);
                QuestionObject  questionObject = new QuestionObject();
                questionObject.setQuestionItemObjects(new ArrayList<>());
                TextContent textContent = new TextContent();
                int question_type = 1;//默认单选
                Subject subject = new Subject();
                //问题类型
                //学科ID\NAME
                //知识点ID\NAME
                //0题型、1试题内容、2试题选项、3答案、4学科、5知识点、6难度、7解析
                // 读取列
                int columns = rowData.getPhysicalNumberOfCells();
                for (int j = 0; j <= columns; j++) {
                    XSSFCell cell = (XSSFCell) rowData.getCell(j);

                    if (cell==null)
                        continue;
                    //判断每一个cell中数据类型
                    //拿到单元格数据
                    String value = "";
                    switch (cell.getCellType()) {
                        case XSSFCell.CELL_TYPE_STRING://String
                            value=cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC://number
                            value = String.valueOf(cell.getNumericCellValue());
                            break;
                    }
                    value = value.trim();
                    if (j==0){//题型
                        if ("单选题".equals(value))
                            question_type=1;
                        else if ("多选题".equals(value))
                            question_type=2;
                        else if ("判断题".equals(value))
                            question_type=3;
                        else if ("填空题".equals(value))
                            question_type=4;
                        else if ("简答题".equals(value))
                            question_type=5;
                        question.setQuestionType(question_type);
                    }else if (j==1){//题干
                        questionObject.setTitleContent(value);
                        if (question_type==4){
                            int gaps = (value.length()-value.replaceAll("#_#","").length())/"#_#".length();
                            for (int i1 = 1; i1 < gaps+1; i1++) {
                                value = value.replaceFirst("#_#","<span class=\"gapfilling-span \">"+i1+"</span>");
                            }
                            questionObject.setTitleContent(value);
                        }
                    }else if (j==2){//选项
                        //封装选项
                        List<QuestionItemObject> itemList = new ArrayList<>();
                        String[] items = value.split(" ");
                        for (int i1 = 0; i1 < 4; i1++) {
                            QuestionItemObject itemObject = new QuestionItemObject();
                            if (i1==0)
                                itemObject.setPrefix("A");
                            else if (i1==1)
                                itemObject.setPrefix("B");
                            else if (i1==2)
                                itemObject.setPrefix("C");
                            else if (i1==3)
                                itemObject.setPrefix("D");
                            else
                                //设置错误信息提醒
                                ;
                            //选项内容
                            if (question_type == 3 && i1 > 1) {
                                continue;
                            }
                            System.out.println(items[i1].substring(items[i1].indexOf(":") + 1));
                            itemObject.setContent(items[i1].substring(items[i1].indexOf(":") + 1));
                            itemList.add(itemObject);
                        }
                        questionObject.setQuestionItemObjects(itemList);


                    }else if (j==3){//答案
                        question.setCorrect(value);
                        questionObject.setCorrect(value);
                        if (question_type==4){
                            String[] gaps = value.split("、");
                            String titleContent = questionObject.getTitleContent();
                            List<QuestionItemObject> itemList = new ArrayList<>();
                            for (int i1 = 1; i1 < gaps.length+1; i1++) {
                                QuestionItemObject itemObject = new QuestionItemObject();
                                itemObject.setPrefix(String.valueOf(i1));
                                itemObject.setContent(gaps[i1-1]);
                                itemObject.setItemUuid(UUID.randomUUID().toString());
                                itemList.add(itemObject);
                                //titleContent的填空和选项的uuid对应
                                titleContent = titleContent.replaceFirst("gapfilling-span","gapfilling-span "+itemObject.getItemUuid());
                            }
                            questionObject.setQuestionItemObjects(itemList);
                            questionObject.setTitleContent(titleContent);
                        }
                    }else if (j==4){
                        if (subjectMap.containsKey(value)){
                            subject = subjectMap.get(value);
                            question.setSubjectId(subject.getId());
                        }else {
                            subject.setDeleted(false);
                            subject.setId( IDGenerator.generateID());
                            subject.setName(value);
                            question.setSubjectId(subject.getId());
                            subjectMap.put(value,subject);
                            subjectService.insert(subject);
                        }
                    }else if (j==5){
                        String all_tag_id ="";
                        String all_tag_name = "";
                        String[] tagsArr = value.split(",");
                        for (String tagName : tagsArr) {
                            if (tagMap.containsKey(tagName)){
                                Tag tag = tagMap.get(tagName);
                                all_tag_id = all_tag_id + tag.getTagId()+",";
                            }
                            else {//创建数据库插入新的tag对象
                                Tag tag = new Tag();
                                tag.setTagId(IDGenerator.generateID());
                                tag.setSubjectId(String.valueOf(subject.getId()));
                                tag.setSubjectName(subject.getName());
                                tag.setTagName(tagName);
                                tagMap.put(tagName,tag);
                                tagService.insert(tag);
                                all_tag_id = all_tag_id + tag.getTagId()+",";
                            }
                        }
                        if (all_tag_id.length()>1)
                            all_tag_id = all_tag_id.substring(0,all_tag_id.length()-1);
                        question.setTags(all_tag_id);
                        question.setTagsName(value);
                    }else if (j==6){
                        question.setDifficult(Integer.valueOf(value.substring(0,1)));
                    }else if (j==7){//解析
                        questionObject.setAnalyze(value);
                    }

                }
                textContent.setContent(JsonUtil.toJsonStr(questionObject));
                textContent.setCreateTime(new Date());

                textContent.setId( IDGenerator.generateID());

                //插入数据、之后再换成批量插入
                textContentService.insert(textContent);
                question.setInfoTextContentId(textContent.getId());
                questionMapper.insert(question);

            }
        }
    }

    /**
     *
     * @param type  试题类型:可为null
     * @param subjectId 学科ID
     * @param points   知识点ID列表
     * @return
     */
    @Override
    public List<Question> queryQuestionsBySubjectAndPoints(Integer type,String subjectId,List<String> points){
        // 一道题有多个知识点，数据库总用,分割
        // tags like '' or tags like
        List<Question> questionList = questionMapper.queryQuestionsBySubjectAndPoints(type,subjectId, points);
        return questionList;

    }

}
