<template>
  <div class="app-container">
    <el-col :span="4" :xs="24">
      <div class="head-container">
      </div>
      <div class="head-container">
        <el-tree
          :data="classTree"
          :expand-on-click-node="false"
          :default-expand-all="false"
          node-key="id"
          ref="tree"
          @node-click="handleNodeClick"
        />
      </div>
    </el-col>
    <el-col :span="20" :xs="24">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="用户名：">
        <el-input v-model="queryParam.userName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
        <router-link :to="{path:'/user/class/edit'}" class="link-left">
          <el-button type="primary">添加班级</el-button>
        </router-link>
      </el-form-item>
    </el-form>

    <el-table v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%">
      <el-table-column prop="uuid" label="班级编号" />
      <el-table-column prop="orgName" label="班级名称"/>
      <el-table-column prop="belongToName" label="所属年级" />
      <el-table-column prop="createTime" label="创建时间" width="160px"/>
      <el-table-column width="220px" label="操作" align="center">
        <template slot-scope="{row}">
          <router-link :to="{path:'/user/class/edit', query:{id:row.uuid}}" class="link-left">
            <el-button size="mini">编辑</el-button>
          </router-link>
          <el-button size="mini" type="danger"  @click="deleteUser(row)" class="link-left">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParam.pageIndex" :limit.sync="queryParam.pageSize"
                @pagination="search"/>
    </el-col>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex'
import Pagination from '@/components/Pagination'
import userApi from '@/api/user'

export default {
  components: { Pagination },
  data () {
    return {
      queryParam: {
        pageIndex: 1,
        pageSize: 10
      },
      treeSearch: false,
      nodeUuid: '',
      listLoading: true,
      tableData: [],
      total: 0,
      classTree:[]
    }
  },
  created () {
    this.search()
    this.initOrgTree()
  },
  methods: {
    search () {
      this.listLoading = true
      if (this.treeSearch){
        console.log("if")
        let param ={uuid:this.nodeUuid,...this.queryParam}
        userApi.childrenList(param).then(res => {
          const re = res.data
          this.tableData = re.list
          this.total = re.total
          this.queryParam.pageIndex = re.pageNum
          this.listLoading = false
        })
      }else {
        console.log("else")
        userApi.getOrgPageList(this.queryParam).then(data => {
          const re = data.data
          this.tableData = re.list
          this.total = re.total
          this.queryParam.pageIndex = re.pageNum
          this.listLoading = false
        })
      }
    },
    changeStatus (row) {
      let _this = this
      userApi.changeStatus(row.id).then(re => {
        if (re.code === 1) {
          row.status = re.data
          _this.$message.success(re.message)
        } else {
          _this.$message.error(re.message)
        }
      })
    },
    deleteUser (row) {
      let _this = this
      userApi.deleteOrg(row.uuid).then(re => {
        if (re.code === 200) {
          _this.search()
          _this.$message.success(re.message)
        } else {
          _this.$message.error(re.message)
        }
      })
    },
    initOrgTree(){
      let _this = this
      console.log(this)
      userApi.getOrgTree().then(res =>{
        if (res.code === 200) {
          console.log(res.data)
          _this.classTree = res.data;
          console.log(_this.classTree)
        }
      })
    },
    handleNodeClick(node) {
      this.nodeUuid=node.id
      this.treeSearch = true
      let queryParam ={uuid:node.id,pageIndex: this.queryParam.pageIndex,pageSize: this.queryParam.pageSize}
      userApi.childrenList(queryParam).then(res => {
        const re = res.data
        this.tableData = re.list
        this.total = re.total
        this.queryParam.pageIndex = re.pageNum
        this.listLoading = false
      })
    },
    submitForm () {
      this.treeSearch = false
      this.queryParam.pageIndex = 1
      this.search()
    },
    sexFormatter  (row, column, cellValue, index) {
      return this.enumFormat(this.sexEnum, cellValue)
    },
    statusFormatter (status) {
      return this.enumFormat(this.statusEnum, status)
    },
    statusTagFormatter (status) {
      return this.enumFormat(this.statusTag, status)
    },
    statusBtnFormatter (status) {
      return this.enumFormat(this.statusBtn, status)
    }
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
    ...mapState('enumItem', {
      sexEnum: state => state.user.sexEnum,
      statusEnum: state => state.user.statusEnum,
      statusTag: state => state.user.statusTag,
      statusBtn: state => state.user.statusBtn
    })
  }
}
</script>
