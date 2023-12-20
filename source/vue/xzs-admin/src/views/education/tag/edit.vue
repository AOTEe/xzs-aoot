<template>
  <div class="app-container">

    <el-form :model="form" ref="form" label-width="100px" v-loading="formLoading">
      <el-form-item label="所属学科：" required>
        <el-select v-model="form.subjectId" placeholder="学科" @change="changeSubject">
          <el-option v-for="item in subjectList" :key="item.id" :value="item.id" :label="item.name"></el-option>
        </el-select>
      </el-form-item>
      <!-- 添加一个隐藏的输入框，用于存储 label 的值 -->
      <el-form-item style="display: none;">
        <el-input v-model="form.subjectName" name="subjectName"></el-input>
      </el-form-item>
      <el-form-item label="知识点：" required>
        <el-input v-model="form.tagName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import { mapGetters, mapState, mapActions } from 'vuex'
import tagApi from '@/api/tag'
import commonRequest from "@/api/commonRequest";
export default {
  data () {
    return {
      form: {
        tagId: null,
        tagName: '',
        subjectId: '',
        subjectName: ''
      },
      subjectList:[],
     formLoading: false
    }
  },
  created () {
    let id = this.$route.query.id
    let _this = this
    if (id && parseInt(id) !== 0) {
      _this.formLoading = true
      tagApi.select(id).then(re => {
        _this.form = re.response
        _this.formLoading = false
      })
    }
    this.initSubjectInfo()
  },
  methods: {
    submitForm () {
      let _this = this
      this.formLoading = true

      tagApi.edit(this.form).then(data => {
        if (data.code === 200) {
          _this.$message.success(data.message)
          _this.delCurrentView(_this).then(() => {
            _this.$router.push('/education/tag/list')
          })
        } else {
          _this.$message.error(data.message)
          _this.formLoading = false
        }
      }).catch(e => {
        _this.formLoading = false
      })
    },
    resetForm () {
      let lastId = this.form.id
      this.$refs['form'].resetFields()
      this.form = {
        id: null,
        tagName: '',
        subjectId: '',
        subjectName: ''
      }
      this.form.id = lastId
    },
    initSubjectInfo(){
      commonRequest.postApi("/api/admin/education/subject/list").then(res => {
        console.log("res。。。。。")
        console.log(res)
        this.subjectList = res.response
      })
    },
    changeSubject(){
      var selectedSubject = this.subjectList.find(subject => subject.id === this.form.subjectId);
      if (selectedSubject) {
        this.form.subjectName = selectedSubject.name;
      }
    },
    ...mapActions('tagsView', { delCurrentView: 'delCurrentView' })
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
  }
}
</script>
