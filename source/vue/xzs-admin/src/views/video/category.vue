<template>
  <div class="app-container">
    <el-form  ref="queryForm" :inline="true">
      <!-- Form -->
      <el-form-item>
          <el-button  @click="dialogFormVisible = true" type="primary">添加</el-button>
      </el-form-item>
    </el-form>

    <el-table  :data="categoryList" border fit highlight-current-row style="width: 100%">
      <el-table-column prop="id" label="Id"  v-if="false"/>
      <el-table-column prop="name" label="分区名称"/>
      <el-table-column prop="createTime" label="创建时间"/>
      <el-table-column prop="updateTime" label="修改时间"/>
      <el-table-column width="220px" label="操作" align="center">
        <template slot-scope="{row}">
            <el-button size="mini">编辑</el-button>
          <el-button   size="mini" type="danger"  class="link-left">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="编辑分区" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="分区名称" >
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>

import commonApi from "@/api/commonRequest";
export default {

  data () {
    return {
      categoryList : [],
      dialogFormVisible : false,
      form : {
        name : ""
      }
    }
  },
  created () {

  },
  mounted() {
    this.getList()
  },
  methods: {
    getList(){
      commonApi.postApi("/api/admin/video/categoryList").then( res => {
        this.categoryList = res.data;
      })
    },
    save(){
      commonApi.postApi("/api/admin/video/saveCategory",this.form).then( res => {
        console.log(res)
        this.categoryList.push(res.data)
        this.form = {name : ""}
        this.dialogFormVisible = false
      })
    },
    cancel(){
      this.form = {name : ""}
      this.dialogFormVisible = false
    }

  }
}
</script>
