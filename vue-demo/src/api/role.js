import request from '@/utils/request'

export function fetchAllRoles() {
  return request({
    url: '/api/user/getAllRoles',
    method: 'get'
  })
}