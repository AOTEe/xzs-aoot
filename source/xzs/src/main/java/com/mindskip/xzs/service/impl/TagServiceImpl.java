package com.mindskip.xzs.service.impl;


import com.github.pagehelper.PageHelper;
import com.mindskip.xzs.bean.Org;
import com.mindskip.xzs.domain.Tag;
import com.mindskip.xzs.repository.TagMapper;
import com.mindskip.xzs.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    TagMapper tagMapper;


    @Override
    public Map<String,Object>  list(Tag tag) {


        PageHelper.startPage(tag.getPageIndex(),tag.getPageSize());

        //数据集合
        List<Tag> tagList =  tagMapper.list(tag);
        //数据总数
        Integer total = tagMapper.listCount(tag);

        Map<String,Object> resMap = new HashMap();
        resMap.put("list",tagList);
        resMap.put("total",total);
        resMap.put("pageNum",tag.getPageIndex());

        return resMap;
    }



    @Override
    public int deleteById(String id) {
        return tagMapper.deleteById(id);
    }

    @Override
    public int insert(Tag record) {
        return tagMapper.insert(record);
    }

    @Override
    public int insertByFilter(Tag record) {
        return 0;
    }

    @Override
    public Tag selectById(String id) {
        return tagMapper.selectById(id);
    }


    @Override
    public int updateByIdFilter(Tag record) {
        return 0;
    }

    @Override
    public int updateById(Tag record) {
        return tagMapper.updateById(record);
    }

    @Override
    public List<Tag> allTags(){
        List<Tag> tags = tagMapper.list(null);
        return tags;
    }
}
