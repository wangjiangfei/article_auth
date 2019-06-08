<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">

      <div class="createPost-main-container">
        <el-row>
          <el-col :span="8">
            <div class="postInfo-container">
              <el-row>
                <el-form-item label="用户名" prop="userName" v-if="isEdit" required>
                    <el-input type="text" v-model="postForm.userName" :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="用户名" prop="userName" v-else required>
                    <el-input type="text" v-model="postForm.userName"></el-input>
                </el-form-item>
                <el-form-item label="新密码" v-if="isEdit">
                    <el-input type="password" v-model="postForm.password" placeholder="不填则表示不修改"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password" v-else required>
                    <el-input type="password" v-model="postForm.password"></el-input>
                </el-form-item>
                <el-form-item label="角色" prop="roleId" required>
                    <el-select v-model="postForm.roleId" placeholder="请选择">
                        <el-option
                        v-for="item in roles"
                        :key="item.roleId"
                        :label="item.roleName"
                        :value="item.roleId">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="昵称" required prop="nickname">
                    <el-input type="text" v-model="postForm.nickname"></el-input>
                </el-form-item>
              </el-row>
            </div>
          </el-col>
        </el-row>

        <el-button v-if="isEdit" type="primary" @click="updateUser">更新</el-button>
        <el-button v-else type="primary" @click="createUser">添加</el-button>

      </div>
    </el-form>

  </div>
</template>

<script>
import Tinymce from '@/components/Tinymce'
import Upload from '@/components/Upload/singleImage3'
import MDinput from '@/components/MDinput'
import Sticky from '@/components/Sticky' // 粘性header组件
import { validURL } from '@/utils/validate'
import { fetchArticle, createArticle, updateArticle } from '@/api/article'
import { fetchAllRoles } from '@/api/role'
import { addUser, fetchUserDetail, updateUser } from '@/api/user'
import { userSearch } from '@/api/remoteSearch'

const defaultForm = {
  userName: '',
  password: '',
  nickname: '',
  roleId: ''
}

export default {
  name: 'ArticleDetail',
  components: { Tinymce, MDinput, Upload, Sticky },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === '' || value === null) {
        callback(new Error('该项不能为空'))
      } else {
        callback()
      }
    }
    return {
      postForm: Object.assign({}, defaultForm),
      rules: {
        userName: [{ validator: validateRequire }],
        password: [{ validator: validateRequire, trigger: 'blur' }],
        nickname: [{ validator: validateRequire }],
        roleId: [{ validator: validateRequire }]
      },
      tempRoute: {},
      roles: [],//角色列表
    }
  },
  computed: {
    lang() {
      return this.$store.getters.language
    }
  },
  created() {
    this.getAllRoles();
    if (this.isEdit) {
      const id = this.$route.params && this.$route.params.id
      this.fetchData(id)
    } else {
      this.postForm = Object.assign({}, defaultForm)
    }

    // Why need to make a copy of this.$route here?
    // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
    // https://github.com/PanJiaChen/vue-element-admin/issues/1221
    this.tempRoute = Object.assign({}, this.$route)
  },
  methods: {
    fetchData(id) {
      fetchUserDetail(id).then(response => {
        this.postForm = response.data.info
        
        // Set tagsview title
        this.setTagsViewTitle()
      }).catch(err => {
        console.log(err)
      })
    },
    setTagsViewTitle() {
      const title = this.lang === 'zh' ? '编辑用户' : 'Edit User'
      const route = Object.assign({}, this.tempRoute, { title: `${title}-${this.postForm.userId}` })
      this.$store.dispatch('updateVisitedView', route)
    },
    getAllRoles() {
        fetchAllRoles().then(response => {
            this.roles = response.data.info
        })
    },
    createUser() {
      this.$refs['postForm'].validate((valid) => {
          if (valid) {
            addUser(this.postForm).then(response => {
              if (response.data.code === 100) {
                this.$router.push({ path: '/user/list' })
              }
            }).catch(err => {
              console.log(err)
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
    },
    updateUser() {
      this.$refs['postForm'].validate((valid) => {
          if (valid) {
            updateUser(this.postForm).then(response => {
              if (response.data.code === 100) {
                this.$router.push({ path: '/user/list' })
              }
            }).catch(err => {
              console.log(err)
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
@import "~@/styles/mixin.scss";
.createPost-container {
  position: relative;
  .createPost-main-container {
    padding: 40px 45px 20px 50px;
    .postInfo-container {
      position: relative;
      @include clearfix;
      margin-bottom: 10px;
      .postInfo-container-item {
        float: left;
      }
    }
  }
  .word-counter {
    width: 40px;
    position: absolute;
    right: -10px;
    top: 0px;
  }
}
</style>
