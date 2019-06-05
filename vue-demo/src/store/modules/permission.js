import { asyncRouterMap, constantRouterMap } from '@/router'

/**
 * 通过meta.perm判断是否与当前用户权限匹配
 * @param permissionList
 * @param route
 */
function hasPermission(permissionList, route) {
  if (route.meta && route.meta.perm) {
    return permissionList.includes(route.meta.perm)
  } else {
    return true
  }
}

/**
 * 递归过滤异步路由表，返回符合用户角色权限的路由表
 * @param routes asyncRouterMap
 * @param permissionList
 */
function filterAsyncRouter(routes, permissionList) {
  const res = []

  routes.forEach(route => {
    const tmp = { ...route }
    if (hasPermission(permissionList, tmp)) {
      if (tmp.children) {
        tmp.children = filterAsyncRouter(tmp.children, permissionList)
      }
      res.push(tmp)
    }
  })

  return res
}

const permission = {
  state: {
    routers: [],
    addRouters: []
  },
  mutations: {
    SET_ROUTERS: (state, routers) => {
      state.addRouters = routers
      state.routers = constantRouterMap.concat(routers)
    }
  },
  actions: {
    GenerateRoutes({ commit }, data) {
      return new Promise(resolve => {
        const { roleName, permissionList } = data
        let accessedRouters
        if (roleName === '管理员') {
          accessedRouters = asyncRouterMap
        } else {
          accessedRouters = filterAsyncRouter(asyncRouterMap, permissionList)
        }
        commit('SET_ROUTERS', accessedRouters)
        resolve()
      })
    }
  }
}

export default permission
