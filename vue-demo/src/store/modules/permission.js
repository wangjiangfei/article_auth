import { asyncRouterMap, constantRouterMap } from '@/router'

/**
 * 通过meta.perm判断是否与当前用户权限匹配
 * @param permissionList
 * @param route
 */
function hasPermission(permissionList, route) {
  if (route.meta && route.meta.perm) {
    return permissionList.indexOf(route.meta.perm) > -1
  } else {
    return true
  }
}

// /**
//  * 递归过滤异步路由表，返回符合用户角色权限的路由表
//  * @param routes asyncRouterMap
//  * @param permissionList
//  */
// function filterAsyncRouter(routes, permissionList) {
//   const res = []

//   routes.forEach(route => {
//     const tmp = { ...route }
//     if (hasPermission(permissionList, tmp)) {
//       if (tmp.children) {
//         tmp.children = filterAsyncRouter(tmp.children, permissionList)
//       }
//       res.push(tmp)
//     }
//   })
//   return res
// }

/**
 * 递归过滤异步路由表，返回符合用户菜单权限的路由表
 * @param asyncRouterMap
 * @param permissionList
 */
function filterAsyncRouter(asyncRouterMap, permissionList) {
  const accessedRouters = asyncRouterMap.filter(route => {
    //filter,js语法里数组的过滤筛选方法
    if (hasPermission(permissionList, route)) {
      if (route.children && route.children.length) {
        //如果这个路由下面还有下一级的话,就递归调用
        route.children = filterAsyncRouter(route.children, permissionList)
        //如果过滤一圈后,没有子元素了,这个父级菜单就也不显示了
        return (route.children && route.children.length)
      }
      return true
    }
    return false
  })
  return accessedRouters
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
