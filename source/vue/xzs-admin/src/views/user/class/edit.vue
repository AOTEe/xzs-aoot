<template>
  <div class="app-container">
    <el-form :model="form" ref="form" label-width="100px" v-loading="formLoading" >
      <el-form-item>
        <el-radio v-model="form.orgType" label="0">年级</el-radio>
        <el-radio v-model="form.orgType" label="1">班级</el-radio>
      </el-form-item>
      <el-form-item label="名称：">
        <el-input v-model="form.orgName"></el-input>
      </el-form-item>
      <el-form-item v-if="form.orgType=='1'"  label="所属年级：">
        <el-select v-model="form.belongTo" @change="changeBelongTo">
          <el-option
            v-for="item in parents" :key="item.uuid" :label="item.orgName" :value="item.uuid"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item  style="display: none">
        <el-input v-model="form.belongToName" ></el-input>
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
import userApi from '@/api/user'

export default {
  data () {
    return {
      form: {
        uuid: null,
        orgType: '',
        orgName: '',
        belongToName: '',
        belongTo:''
      },
      parents:[],
      formLoading: false
    }
  },
  created () {
    let uuid = this.$route.query.id
    let _this = this
    userApi.getAllOrgParents().then(re => {
      _this.parents = re.data
      _this.formLoading = false
    })
    if (uuid && parseInt(uuid) !== 0) {
      _this.formLoading = true
      userApi.selectOrg(uuid).then(re => {
        _this.form = re.data
        _this.formLoading = false
      })
      console.log(_this.form)
      console.log(_this.parents)
    }
  },
  methods: {
    submitForm () {
      let _this = this
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.formLoading = true
          if (this.form.uuid==null){
            userApi.createOrg(this.form).then(data => {
              if (data.code === 200) {
                _this.$message.success(data.message)
                _this.delCurrentView(_this).then(() => {
                  _this.$router.push('/user/class/list')
                })
              } else {
                _this.$message.error(data.message)
                _this.formLoading = false
              }
            }).catch(e => {
              _this.formLoading = false
            })
          }else {
            userApi.updateOrg(this.form).then(data => {
              if (data.code === 200) {
                _this.$message.success(data.message)
                _this.delCurrentView(_this).then(() => {
                  _this.$router.push('/user/class/list')
                })
              } else {
                _this.$message.error(data.message)
                _this.formLoading = false
              }
            }).catch(e => {
              _this.formLoading = false
            })
          }

        } else {
          return false
        }
      })
    },
    changeBelongTo(e){
      //无参时默认时 下拉框的value
      let obj = {}
      obj = this.parents.find(item => {
        return item.uuid === e
      })

      // 获取对应id的lable值
      this.form.belongToName=obj.orgName

    },
    resetForm () {
      let lastId = this.form.id
      this.$refs['form'].resetFields()
      this.form = {
        uuid: null
      }
      this.form.id = lastId
    },

    ...mapActions('tagsView', { delCurrentView: 'delCurrentView' })
  },
  computed: {
    ...mapGetters('enumItem', [
      'enumFormat'
    ]),
    ...mapState('enumItem', {
      sexEnum: state => state.user.sexEnum,
      roleEnum: state => state.user.roleEnum,
      statusEnum: state => state.user.statusEnum
    })
  }
}
</script>
