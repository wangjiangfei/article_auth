import axios from 'axios'
import { Message, MessageBox } from 'element-ui'
import store from '@/store'
import { getToken } from '@/utils/auth'

// create an axios instance
const service = axios.create({
  baseURL: process.env.BASE_API, // api 的 base_url
  timeout: 5000 // request timeout
})

// request interceptor
service.interceptors.request.use(
  config => {
    // Do something before request is sent
    if (store.getters.token) {
      // 让每个请求携带token-- ['token']为自定义key 请根据实际情况自行修改
      config.headers['token'] = getToken()
    }
    return config
  },
  error => {
    // Do something with request error
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(
  /**
   * 下面的注释为通过在response里，自定义code来标示请求状态
   * 当code返回如下情况则说明权限有问题，登出并返回到登录页
   * 如想通过 xmlhttprequest 来状态码标识 逻辑可写在下面error中
   * 以下代码均为样例，请结合自身需求加以修改，若不需要，则可删除
   */
  response => {
    const res = response.data
    if (res.code !== 100) {
      // 300:用户名或密码错误
      if (res.code === 300) {
        Message({
          message: res.msg,
          type: 'error',
          duration: 5 * 1000
        })
      }
      // 20010:无token 20011:登陆已过期
      if (res.code === 20010 || res.code === 20011) {
        MessageBox.confirm('你已被登出，可以取消继续留在该页面，或者重新登录', '确定登出', {
          confirmButtonText: '重新登录',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          store.dispatch('FedLogOut').then(() => {
            location.reload() // 为了重新实例化vue-router对象 避免bug
          })
        })
      }
      // 权限不足
      if (res.code === 250) {
        Message({
          message: res.msg,
          type: 'error',
          duration: 3 * 1000
        })
      }
      // 账户已存在
      if (res.code === 10009) {
        Message({
          message: res.msg,
          type: 'error',
          duration: 2 * 1000
        })
      }
      // 参数类型不合法
      if (res.code === 113) {
        Message({
          message: res.msg,
          type: 'error',
          duration: 2 * 1000
        })
      }
      // 角色删除失败,尚有用户属于此角色
      if (res.code === 10008) {
        Message({
          message: res.msg,
          type: 'error',
          duration: 2 * 1000
        })
      }
      return Promise.reject('error')
    } else {
      return response
    }
  },
  error => {
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service
