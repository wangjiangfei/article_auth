import request from '@/utils/request'

export function fetchAllRoles() {
  return request({
    url: '/api/user/getAllRoles',
    method: 'get'
  })
}

export function fetchRoleList() {
  return request({
    url: '/api/role/listRole',
    method: 'get'
  })
}

export function fetchAllPermissions() {
  return request({
    url: '/api/role/listAllPermission',
    method: 'get'
  })
}

export function createRole(data) {
  return request({
    url: '/api/role/addRole',
    method: 'post',
    data
  })
}

export function updateRole(data) {
  return request({
    url: '/api/role/updateRole',
    method: 'put',
    data
  })
}

export function deleteRoleById(roleId) {
  return request({
    url: '/api/role/deleteRole/' + roleId,
    method: 'delete'
  })
}
