<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">

      <div class="createPost-main-container">
        <el-row v-if="isEdit">

          <el-col :span="24">

            <div class="postInfo-container">
              <el-row>
                <el-col :span="8"> 
                  <el-form-item label-width="60px" label="创建者:" class="postInfo-container-item">
                    <el-input
                      v-model="postForm.createUserName"
                      :disabled="true">
                    </el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="10">
                  <el-form-item label-width="80px" label="发布时间:" class="postInfo-container-item">
                    <el-date-picker v-model="postForm.createTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" :disabled="true"/>
                  </el-form-item>
                </el-col>

                <el-col :span="8"> 
                  <el-form-item label-width="60px" label="更新者:" class="postInfo-container-item">
                    <el-input
                      v-model="postForm.createUserName"
                      :disabled="true">
                    </el-input>
                  </el-form-item>
                </el-col>

                <el-col :span="10">
                  <el-form-item label-width="80px" label="更新时间:" class="postInfo-container-item">
                    <el-date-picker v-model="postForm.updateTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" :disabled="true"/>
                  </el-form-item>
                </el-col>

              </el-row>
            </div>
          </el-col>
        </el-row>

        <el-form-item prop="content" style="margin-bottom: 30px;">
          <Tinymce ref="editor" :height="400" v-model="postForm.content" />
        </el-form-item>

        <el-button v-if="isEdit" type="primary" @click="updateEssay">更新</el-button>
        <el-button v-else type="primary" @click="createEssay">创建</el-button>

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
import { userSearch } from '@/api/remoteSearch'
import { CommentDropdown, PlatformDropdown, SourceUrlDropdown } from './Dropdown'

const defaultForm = {
  content: '' // 文章内容
}

export default {
  name: 'ArticleDetail',
  components: { Tinymce, MDinput, Upload, Sticky, CommentDropdown, PlatformDropdown, SourceUrlDropdown },
  props: {
    isEdit: {
      type: Boolean,
      default: false
    }
  },
  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === '') {
        this.$message({
          message: rule.field + '为必传项',
          type: 'error'
        })
        callback(new Error(rule.field + '为必传项'))
      } else {
        callback()
      }
    }
    return {
      postForm: Object.assign({}, defaultForm),
      rules: {
        content: [{ validator: validateRequire }]
      },
      tempRoute: {}
    }
  },
  computed: {
    lang() {
      return this.$store.getters.language
    }
  },
  created() {
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
      fetchArticle(id).then(response => {
        this.postForm = response.data.info
        
        // Set tagsview title
        this.setTagsViewTitle()
      }).catch(err => {
        console.log(err)
      })
    },
    setTagsViewTitle() {
      const title = this.lang === 'zh' ? '编辑文章' : 'Edit Article'
      const route = Object.assign({}, this.tempRoute, { title: `${title}-${this.postForm.articleId}` })
      this.$store.dispatch('updateVisitedView', route)
    },
    createEssay() {
      if (!this.postForm.content) {
        this.$message.error('文章内容不能为空');
        return;
      }
      createArticle(this.postForm).then(response => {
        if (response.data.code === 100) {
          this.$router.push({ path: '/article/list' })
        }
      }).catch(err => {
        console.log(err)
      })
    },
    updateEssay() {
      if (!this.postForm.content) {
        this.$message.error('文章内容不能为空');
        return;
      }
      updateArticle(this.postForm).then(response => {
        if (response.data.code === 100) {
          this.$router.push({ path: '/article/list' })
        }
      }).catch(err => {
        console.log(err)
      })
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
