<template>
  <div class="app-container">
    <el-form :model="queryParam" ref="queryForm" :inline="true">
      <el-form-item label="所属学科：">
        <el-input v-model="queryParam.subjectName"></el-input>
      </el-form-item>
      <el-form-item label="知识点：">
        <el-input v-model="queryParam.tagName"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">查询</el-button>
        <router-link :to="{path:'/education/tag/edit'}" class="link-left">
          <el-button type="primary">添加</el-button>
        </router-link>
      </el-form-item>
    </el-form>

    <el-table v-loading="listLoading" :data="tableData" border fit highlight-current-row style="width: 100%">
      <el-table-column prop="tagId" label="Id" />
      <el-table-column prop="tagName" label="知识点"/>
      <el-table-column prop="subjectName" label="所属学科" />
      <el-table-column width="220px" label="操作" align="center">
        <template slot-scope="{row}">
          <router-link :to="{path:'/education/tag/edit', query:{id:row.id}}" class="link-left">
            <el-button size="mini">编辑</el-button>
          </router-link>
          <el-button   size="mini" type="danger" @click="delSubject(row)" class="link-left">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="queryParam.pageIndex" :limit.sync="queryParam.pageSize"
                @pagination="search"/>
  </div>
</template>

<script>
import { mapGetters, mapState } from 'vuex'
import Pagination from '@/components/Pagination'
import tagApi from '@/api/tag'

export default {
  components: { Pagination },
  data () {
    return {
      queryParam: {
        subjectId: null,
        subjectName: null,
        tagId : null,
        tagName : null,
        pageIndex: 1,
        pageSize: 10
      },
      listLoading: true,
      tableData: [],
      total: 0
    }
  },
  created () {
    this.search()
  },
  methods: {
    search () {
      this.listLoading = true
      tagApi.pageList(this.queryParam).then(data => {
        const re = data.data
        this.tableData = re.list
        this.total = re.total
        this.queryParam.pageIndex = re.pageNum
        this.listLoading = false
      })
    },
    submitForm () {
      this.queryParam.pageIndex = 1
      this.search()
    },
    delSubject (row) {
      let _this = this
      tagApi.deleteSubject(row.id).then(re => {
        if (re.code === 1) {
          _this.search()
          _this.$message.success(re.message)
        } else {
          _this.$message.error(re.message)
        }
      })
    }
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
    ...mapState('enumItem', {
      levelEnum: state => state.user.levelEnum
    })
  }
}
</script>
