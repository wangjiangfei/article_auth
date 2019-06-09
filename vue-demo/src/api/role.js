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