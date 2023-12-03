<template>
  <div>
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="学科：" required style="width: 20%">
        <el-select v-model="form.subjectId" placeholder="学科" @change="changeSubject(form.subjectId)">
          <el-option v-for="item in subjectFilter" :key="item.id" :value="item.id"
                     :label="item.name"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="知识点：" prop="tags" required>
        <el-tag
          v-for="tag in form.pointsName"
          :key="tag"
          :closable="true"
          :type="tag.type"
          @close="closeTag(tag)">
          {{ tag }}
        </el-tag>
        <el-button class="button-new-tag" size="small" @click="pointVisible()" >+添加</el-button>
      </el-form-item>
      <el-form-item label="试卷难度" required style="width: 20%">
        <el-input v-model="form.difficulty"></el-input>
      </el-form-item>
      <el-form-item label="试卷总分" required style="width: 20%">
        <el-input v-model="form.totalMark"></el-input>
      </el-form-item>
      <el-form-item :key="index" :label="'题型'+(index+1)+'：'" required v-for="(typeItem,index) in form.questionTypeItems">
        <el-button type="text" class="link-left" size="mini" @click="form.titleItems.splice(index,1)">删除</el-button>
        <div class="demo-input-suffix">
          题型：
          <el-select v-model="typeItem.questionType" style="width: 20%" clearable>
            <el-option v-for="item in questionTypeEnum" :key="item.key" :value="item.key"
                       :label="item.value"></el-option>
          </el-select>
        </div>
        <div class="demo-input-suffix">
          题数：
          <el-input v-model="typeItem.num" type="number" style="width: 20%"/>
        </div>
        <div class="demo-input-suffix">
          分值(每题)：
          <el-input v-model="typeItem.score" type="number" style="width: 20%"/>
        </div>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="generate">开始组卷</el-button>
        <el-button>取消</el-button>
        <el-button type="success" @click="addType">添加题型</el-button>
      </el-form-item>
    </el-form>

    <!--  添加知识点弹出层-->
    <el-dialog title="选择知识点" :visible.sync="addPointVisible">
      <el-table :data="queryTags">
        <el-table-column prop="tagName" label="名称">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="100">
          <template slot-scope="row">
            <el-button type="text" size="small" @click="addPoint(row)">添加</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script>
import {mapActions, mapState} from "vuex";
import tagApi from "@/api/tag";
import commonRequest from "@/api/commonRequest";

export default {
  name: "PaperAutoGenerate",
  data() {
    return {
      form: {
        subjectId: null,
        difficulty: null,
        points: [],
        pointsName :[],
        questionTypeItems: [],
        totalMark : 0
      },
      subjectFilter: null,
      addPointVisible : false,
      queryTags: [],

    }
  },
  created() {
    let _this = this
    this.initSubject(function () {
      _this.subjectFilter = _this.subjects
    })
  },
  computed:{
    ...mapState('exam', { subjects: state => state.subjects }),
    ...mapState('enumItem', {
      questionTypeEnum: state => state.exam.question.typeEnum,
      paperTypeEnum: state => state.exam.examPaper.paperTypeEnum,
    }),
  },
  methods: {
    addType() {
      this.form.questionTypeItems.push({
        name: '',
        questionType: null,
        score: null
      })
    },
    pointVisible(){
      this.addPointVisible = true;
      this.doQueryTag()
    },
    addPoint(data){
      if (this.form.points.findIndex(item => item == data.row.tagId) == -1) {
        this.form.points.push(data.row.tagId)
        this.form.pointsName.push(data.row.tagName)
      }
    },
    changeSubject(subjectId) {
      for (let i = 0; i < this.subjectFilter.length; i++) {
        if (subjectId == this.subjectFilter[i].id) {
          this.form.subjectName = this.subjectFilter[i].name
          console.log(this.subjectFilter[i])
        }
      }
    },
    doQueryTag() {//查询某学科下的知识点
      if (this.form.subjectId != "" && this.form.subjectId != null){
        tagApi.list({subjectId:this.form.subjectId}).then(re => {
          this.queryTags = re.data.list
        })
      }
    },
    generate(){
      commonRequest.postApi("/api/admin/exam/paper/autoGenerate",this.form).then( res =>{
        console.log(res)
      })
    },
    ...mapActions('exam', {initSubject: 'initSubject'}),
  }
}
</script>

<style scoped>

</style>
