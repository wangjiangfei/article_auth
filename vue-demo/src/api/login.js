import request from '@/utils/request'

export function loginByUsername(username, password) {
  const data = {
    userName: username,
    password
  }
  return request({
    url: '/api/login/auth',
    method: 'post',
    data
  })
}

export function logout() {
  return request({
    url: '/login/logout',
    method: 'post'
  })
}

export function getUserInfo() {
  return request({
    url: '/api/login/getInfo',
    method: 'post'
  })
}

