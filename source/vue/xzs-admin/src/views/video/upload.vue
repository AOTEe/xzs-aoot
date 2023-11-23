<template>
  <div>
    <div class="upload">
      <el-upload
        drag
        action="/api/admin/video/upload"
        :on-success="uploadSuccess"
        multiple>
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
      </el-upload>
    </div>

    <!--  视频表单-->
    <div>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="封面">
<!--          <el-input v-model="form.cover"></el-input>-->
          <el-image
            style="width: 100px; height: 100px"
            :src="form.cover"
            >
          </el-image>
          <el-upload
            class="avatar-uploader"
            action="/api/admin/video/coverUpload"
            :show-file-list="false"
            :on-success="coverUploadSuccess">
            <img v-if="false" :src="form.cover" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>

        </el-form-item>
        <el-form-item label="标题">
          <el-input v-model="form.title"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-radio-group v-model="form.type">
            <el-radio label="自制"></el-radio>
            <el-radio label="转载"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="分区">
          <el-select v-model="form.category" >
            <el-option  v-for="item in categoryList" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标签">
          <el-tag
            v-for="tag in form.tags"
            :key="tag"
            :closable="true"
            @close="closeTag(tag)">
            {{tag}}
          </el-tag>
          <el-input
            class="input-new-tag"
            v-if="tagInputVisible"
            v-model="tagInputValue"
            ref="saveTagInput"
            size="small"
            @keyup.enter.native="inputTagConfirm"
            @blur="inputTagConfirm"
          >
          </el-input>
          <el-button v-else class="button-new-tag" size="small" @click="showTagInput">+  添加标签</el-button>
        </el-form-item>
        <el-form-item label="简介">
          <el-input type="textarea" v-model="form.introduce"></el-input>
        </el-form-item>
<!--        <el-form-item label="定时发布">-->

<!--        </el-form-item>-->
        <el-form-item>
          <el-button type="primary" @click="onSubmit">发布</el-button>
          <el-button>存草稿</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import commonApi from "@/api/commonRequest";
export default {
  name: "edit",
  data() {
    return{
      baseUrl : "127.0.0.1:8000",
      form : {
        cover : null,
        title : null,
        type : null,
        category : null,
        tags : [],
        introduction : null
      },
      tagInputValue : null,
      tagInputVisible : false,
      categoryList : []
    }
  },
  methods : {
    uploadSuccess(res,file,fileLise){
      //上传成功
      console.log(res)
      this.form = res.data;
    },
    coverUploadSuccess(res){
      //
      console.log(res)
      this.form.cover = res.data
    },
    inputTagConfirm(){
      console.log("inputTagConfirm")
      let tagInputValue = this.tagInputValue;
      if (tagInputValue && this.form.tags.findIndex(item =>item == tagInputValue) ==-1) {
        this.form.tags.push(tagInputValue);
      }
      this.tagInputVisible = false;
      this.tagInputValue = '';
    },
    showTagInput(){
      this.tagInputVisible = true;
      this.$nextTick(_ => {
        this.$refs.saveTagInput.$refs.input.focus();
      });
    },
    closeTag(tag){
      this.form.tags.splice(this.form.tags.indexOf(tag), 1);
    },
    onSubmit(){
      commonApi.postApi("/api/admin/video/save")
    },
    initCategory(){
      commonApi.postApi("/api/admin/video/categoryList").then(res=>{
        this.categoryList = res.data
      })
    }
  },
  mounted() {
    this.initCategory();

    this.tagsList = this.form.tags.splice()
  }
}
</script>

<style scoped>
.upload {
  text-align: center;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}

</style>
