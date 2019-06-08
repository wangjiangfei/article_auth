import request from '@/utils/request'

export function fetchUserList(query) {
  return request({
    url: '/api/user/list',
    method: 'get',
    params: {
      pageNum: query.page,
      pageRow: query.limit
    }
  })
}

export function fetchUserDetail(userId) {
  return request({
    url: '/api/user/detail/' + userId,
    method: 'get'
  })
}

export function addUser(data) {
  return request({
    url: '/api/user/addUser',
    method: 'post',
    data
  })
}

export function updateUser(data) {
  return request({
    url: '/api/user/updateUser',
    method: 'put',
    data
  })
}
