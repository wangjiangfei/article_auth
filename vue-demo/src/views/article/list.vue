<template>
  <div class="app-container">

    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">
      <el-table-column align="center" label="ID" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.articleId }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="内容">
        <template slot-scope="scope">
          <span>{{ scope.row.content }}</span>
        </template>
      </el-table-column>

      <el-table-column width="180px" align="center" label="创建时间">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="创建者">
        <template slot-scope="scope">
          <span>{{ scope.row.createUserName }}</span>
        </template>
      </el-table-column>

      <el-table-column width="180px" align="center" label="更新时间">
        <template slot-scope="scope">
          <span>{{ scope.row.updateTime | parseTime('{y}-{m}-{d} {h}:{i}') }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="更新者">
        <template slot-scope="scope">
          <span>{{ scope.row.updateUserName }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center" label="查看" width="120">
        <template slot-scope="scope">
          <el-button type="success" size="small" icon="el-icon-info" @click="showDetailById(scope.row.articleId)">查看详情</el-button>
        </template>  
      </el-table-column>

      <el-table-column align="center" label="操作" width="120">
        <template slot-scope="scope">
          <router-link :to="'/article/edit/' + scope.row.articleId">
            <el-button type="primary" size="mini" icon="el-icon-edit">编辑</el-button>
          </router-link>
          <el-button v-if="scope.row.createUserId === curUserId" type="danger" size="small" icon="el-icon-delete" @click="deleteArticleById(scope.row.articleId)">删除</el-button>
        </template>
      </el-table-column>

    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />
    
    <!-- Dialog对话框，查看文章详情 -->
    <el-dialog title="文章详情" :visible.sync="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="文章id" :label-width="formLabelWidth">
          <el-input v-model="form.articleId" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="文章内容" :label-width="formLabelWidth">
          <el-input v-model="form.content" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="创建人" :label-width="formLabelWidth">
          <el-input v-model="form.createUserName" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="创建时间" :label-width="formLabelWidth">
          <el-date-picker v-model="form.createTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" />
        </el-form-item>
        <el-form-item label="修改人" :label-width="formLabelWidth">
          <el-input v-model="form.updateUserName" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="修改时间" :label-width="formLabelWidth">
          <el-date-picker v-model="form.updateTime" type="datetime" format="yyyy-MM-dd HH:mm:ss" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogFormVisible = false">关闭</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import store from '@/store/index'
import { fetchList, deleteArticle, fetchArticle } from '@/api/article'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'ArticleList',
  components: { Pagination },
  data() {
    return {
      list: null,
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20
      },
      curUserId: store.getters.userId,
      dialogFormVisible: false,
      form: {
          articleId: 0,
          content: '',
          createTime: '',
          updateTime: '',
          createUserName: '',
          updateUserName: ''
        },
        formLabelWidth: '120px'
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      fetchList(this.listQuery).then(response => {
        this.list = response.data.info.articleInfoList
        this.total = response.data.info.count
        this.listLoading = false
      })
    },
    handleSizeChange(val) {
      this.listQuery.limit = val
      this.getList()
    },
    handleCurrentChange(val) {
      this.listQuery.page = val
      this.getList()
    },
    deleteArticleById(articleId) {
      deleteArticle(articleId).then(response => {
        if (response.data.code === 100) {
          this.$message({
            showClose: true,
            message: '删除成功',
            type: 'success'
          });
          this.getList()
        }
      }).catch(err => {

      })
    },
    showDetailById(articleId) {
      fetchArticle(articleId).then(response => {
        if (response.data.code === 100) {
          this.form = response.data.info
        }
      })
      this.dialogFormVisible = true
    }
  }
}
</script>

<style scoped>
.edit-input {
  padding-right: 100px;
}
.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}
</style>
