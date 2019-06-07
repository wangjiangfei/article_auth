import request from '@/utils/request'

export function fetchList(query) {
  return request({
    url: '/api/article/listArticle',
    method: 'get',
    params: {
      pageNum: query.page,
      pageRow: query.limit
    }
  })
}

export function fetchArticle(id) {
  return request({
    url: '/api/article/detail/' + id,
    method: 'get'
  })
}

export function fetchPv(pv) {
  return request({
    url: '/article/pv',
    method: 'get',
    params: { pv }
  })
}

export function createArticle(data) {
  return request({
    url: '/api/article/addArticle',
    method: 'post',
    data
  })
}

export function updateArticle(data) {
  return request({
    url: '/api/article/updateArticle',
    method: 'put',
    data
  })
}

export function deleteArticle(articleId) {
  return request({
    url: '/api/article/deleteArticle/' + articleId,
    method: 'delete'
  })
}
