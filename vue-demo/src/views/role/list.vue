<template>
  <div class="app-container">
    <el-button v-if="hasPerm('role:add')" type="primary" @click="showCreate" style="margin:10px;">添加角色</el-button>

    <el-table v-loading="listLoading" :data="list" border fit highlight-current-row style="width: 100%">

      <el-table-column type="index" width="50"></el-table-column>

      <el-table-column align="center" label="ID" width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.roleId }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="角色">
        <template slot-scope="scope">
          <span>{{ scope.row.roleName }}</span>
        </template>
      </el-table-column>

      <el-table-column width="120px" align="center" label="用户">
        <template slot-scope="scope">
            <div v-for="user in scope.row.users" :key="user.userId">
                <div v-text="user.nickname"
                    style="display: inline-block;vertical-align: middle;"></div>
            </div>
        </template>
      </el-table-column>

      <el-table-column align="center" label="菜单&权限" width="420">
        <template slot-scope="scope">
          <el-tag type="success" v-text="scope.row.roleName" v-if="scope.row.roleId === 1"></el-tag>
          <el-tag type="primary" v-text="scope.row.roleName" v-else></el-tag>
        </template>
        <template slot-scope="scope">
          <el-tag v-if="scope.row.roleName == '管理员'" type="success">全部</el-tag>
          <div v-else>
            <div v-for="menu in scope.row.menus" :key="menu.menuCode" style="text-align: left">
              <span style="width: 100px;display: inline-block;text-align: right ">{{menu.menuName}}</span>
              <el-tag v-for="perm in menu.permissions" :key="perm.permissionId" v-text="perm.permissionName"
                      style="margin-right: 3px;"
                      type="primary"></el-tag>
            </div>
          </div>
        </template>
      </el-table-column>

      <el-table-column align="center" label="操作" width="220">
        <template slot-scope="scope">
          <el-button v-if="hasPerm('role:update')" type="primary" size="mini" icon="el-icon-edit" @click="showUpdate(scope.$index)">编辑</el-button>
          <el-button v-if="hasPerm('role:delete')" type="danger" size="mini" icon="el-icon-delete" @click="deleteRole(scope.row.roleId)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- Dialog对话框，创建角色 -->
    <el-dialog title="新建角色" :visible.sync="createFormVisible">
      <el-form :model="tempRole">
        <el-form-item label="角色名称" required>
          <el-input type="text" v-model="tempRole.roleName" style="width: 250px;" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="菜单&权限" required>
          <br>
          <div v-for=" (menu,_index) in allPermission" :key="menu.menuName">
            <span style="width: 100px;display: inline-block;">
              <el-button :type="isMenuNone(_index)?'':(isMenuAll(_index)?'success':'primary')" size="mini"
                         style="width:80px;"
                         @click="checkAll(_index)">{{menu.menuName}}</el-button>
            </span>
            <div style="display: inline-block;margin-left:20px;">
              <el-checkbox-group v-model="tempRole.permissions">
                <el-checkbox v-for="perm in menu.permissions" :label="perm.permissionId" @change="checkRequired(perm,_index)"
                             :key="perm.permissionId">
                  <span :class="{ requiredPerm : perm.requiredPermission == 1}">{{ perm.permissionName }}</span>
                </el-checkbox>
              </el-checkbox-group>
            </div>
          </div>
          <p style="color:#848484;">说明:红色权限为对应菜单内的必选权限</p>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addRole">新建</el-button>
        <el-button @click="createFormVisible = false">关闭</el-button>
      </div>
    </el-dialog>

    <!-- Dialog对话框，修改角色的权限 -->
    <el-dialog title="修改角色" :visible.sync="editFormVisible">
      <el-form :model="tempRole">
        <el-form-item label="角色名称" required>
          <el-input type="text" v-model="tempRole.roleName" style="width: 250px;" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="菜单&权限" required>
          <div v-for=" (menu,_index) in allPermission" :key="menu.menuName">
            <span style="width: 100px;display: inline-block;">
              <el-button :type="isMenuNone(_index)?'':(isMenuAll(_index)?'success':'primary')" size="mini"
                         style="width:80px;"
                         @click="checkAll(_index)">{{menu.menuName}}</el-button>
            </span>
            <div style="display: inline-block;margin-left:20px;">
              <el-checkbox-group v-model="tempRole.permissions">
                <el-checkbox v-for="perm in menu.permissions" :label="perm.permissionId" @change="checkRequired(perm,_index)"
                             :key="perm.permissionId">
                  <span :class="{ requiredPerm : perm.requiredPermission == 1}">{{ perm.permissionName }}</span>
                </el-checkbox>
              </el-checkbox-group>
            </div>
          </div>
          <p style="color:#848484;">说明:红色权限为对应菜单内的必选权限</p>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="modifyRole">修改</el-button>
        <el-button @click="editFormVisible = false">关闭</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { fetchRoleList, fetchAllPermissions, createRole, updateRole, deleteRoleById } from "@/api/role";

export default {
  name: "RoleList",
  data() {
    return {
      list: null,// 角色列表
      listLoading: true,
      createFormVisible: false,
      editFormVisible: false,
      allPermission: [],// 所有权限
      tempRole: {
        roleId: '',
        roleName: '',
        permissions: []
      },
      formLabelWidth: "120px"
    }
  },
  created() {
    this.getList();
    this.getAllPermisson();
  },
  methods: {
    // 获取角色列表
    getList() {
        this.listLoading = true;
        fetchRoleList().then(response => {
            this.list = response.data.info;
            this.listLoading = false;
        });
    },
    // 获取所有权限
    getAllPermisson() {
        fetchAllPermissions().then(response => {
            this.allPermission = response.data.info;
        });
    },
    // 新增角色
    addRole() {
        if (this.tempRole.roleName === '') {
            this.$message({
                showClose: true,
                message: '角色名不能为空',
                type: 'error'
            });
            return;
        }
        if (this.tempRole.permissions.length == 0) {
            this.$message({
                showClose: true,
                message: '请选择权限',
                type: 'error'
            });
            return;
        }
        let data = {
            roleName: this.tempRole.roleName,
            permissions: this.tempRole.permissions
        };
        createRole(data).then(response => {
            if (response.data.code === 100) {
                this.$message({ showClose: true, message: '角色创建成功', type: 'success' });
                this.createFormVisible = false;
                this.getList();
            }
        }).catch(err => {})
    },
    // 根据角色id删除该角色
    deleteRole(roleId) {
        deleteRoleById(roleId).then(response => {
            if (response.data.code === 100) {
                this.$message({ showClose: true, message: '角色删除成功', type: 'success' });
                this.getList();
            }
        }).catch(err => {})
    },
    // 修改角色
    modifyRole() {
      if (this.tempRole.roleName === '') {
            this.$message({
                showClose: true,
                message: '角色名不能为空',
                type: 'error'
            });
            return;
        }
        if (this.tempRole.permissions.length == 0) {
            this.$message({
                showClose: true,
                message: '请选择权限',
                type: 'error'
            });
            return;
        }
        updateRole(this.tempRole).then(response => {
            if (response.data.code === 100) {
                this.$message({ showClose: true, message: '角色更新成功', type: 'success' });
                this.editFormVisible = false;
                this.getList();
            }
        }).catch(err => {})
    },
    // 显示新增角色对话框
    showCreate() {
        this.clearTempRole();
        this.createFormVisible = true;
    },
    // 显示编辑角色对话框
    showUpdate($index) {
        this.clearTempRole();
        let role = this.list[$index];
        this.tempRole.roleId = role.roleId;
        this.tempRole.roleName = role.roleName;
        this.tempRole.permissions = [];
        for (let i = 0; i < role.menus.length; i++) {
            let perms = role.menus[i].permissions;
            for (let j = 0; j < perms.length; j++) {
                this.tempRole.permissions.push(perms[j].permissionId);
            }
        }
        this.editFormVisible = true;
    },
    // 清空对话框中的数据
    clearTempRole() {
        this.tempRole.roleId = '';
        this.tempRole.roleName = '';
        this.tempRole.permissions = [];
    },
    // 判断本级菜单内的权限是否一个都没选
    isMenuNone(_index) {
        let menu = this.allPermission[_index].permissions;
        let result = true;
        for (let j = 0; j < menu.length; j++) {
          if (this.tempRole.permissions.indexOf(menu[j].permissionId) > -1) {
            result = false;
            break;
          }
        }
        return result;
    },
    // 判断本级菜单内的权限是否全选了
    isMenuAll(_index) {
        let menu = this.allPermission[_index].permissions;
        let result = true;
        for (let j = 0; j < menu.length; j++) {
          if (this.tempRole.permissions.indexOf(menu[j].permissionId) < 0) {
            result = false;
            break;
          }
        }
        return result;
    },
    checkAll(_index) {
        //点击菜单   相当于全选按钮
        let v = this;
        if (v.isMenuAll(_index)) {
          // 如果已经全选了,则全部取消
          v.noPerm(_index);
        } else {
          // 如果尚未全选,则全选
          v.allPerm(_index);
        }
    },
    // 本方法会在多选框勾选状态改变之后触发
    checkRequired(_perm, _index) {
        let permId = _perm.permissionId;

        if (this.tempRole.permissions.indexOf(permId) > -1) {
          // 选中事件
          // 如果之前未勾选本权限,现在勾选完之后,tempRole里就会包含本id
          // 那么就要将必选的权限勾上
          this.makeReuqiredPermissionChecked(_index);
        } else {
          // 取消选中事件
          if (_perm.requiredPermission == 1) {
            // 如果是必勾权限,就把本菜单的权限全部移出
            // (其实也可以提示用户本权限是菜单里的必选,请先取消勾选另外几个权限,交互太麻烦,此处就直接全部取消选中了)
            this.noPerm(_index);
          }
        }
    },
    // 将本菜单必选的权限勾上
    makeReuqiredPermissionChecked(_index) {
        let menu = this.allPermission[_index].permissions;
        for (let j = 0; j < menu.length; j++) {
          let perm = menu[j];
          if (perm.requiredPermission == 1) {
            // 找到本菜单的必选权限,将其勾上
            this.addUnique(perm.permissionId, this.tempRole.permissions)
          }
        }
    },
    // 数组内防重复地添加元素
    addUnique(val, arr) {
        let _index = arr.indexOf(val);
        if (_index < 0) {
          arr.push(val);
        }
    },
    // 全部取消选中
    noPerm(_index) {
        let menu = this.allPermission[_index].permissions;
        for (let j = 0; j < menu.length; j++) {
            let idIndex = this.tempRole.permissions.indexOf(menu[j].permissionId);
            if (idIndex > -1) {
                this.tempRole.permissions.splice(idIndex, 1);
            }
        }
    },
    // 全部选中
    allPerm(_index) {
        let menu = this.allPermission[_index].permissions;
        for (let j = 0; j < menu.length; j++) {
          this.addUnique(menu[j].permissionId, this.tempRole.permissions)
        }
    },
  }
};
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
.requiredPerm {
  color: #ff0e13;
}
</style>
