import Vue from 'vue'
import Router from 'vue-router'


Vue.use(Router)

/* Layout */
import Layout from '@/views/layout/Layout'

/** note: sub-menu only appear when children.length>=1
 *  detail see  https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 **/

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    roles: ['admin','editor']    will control the page roles (you can set multiple roles)
    title: 'title'               the name show in sub-menu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar
    noCache: true                if true, the page will no be cached(default is false)
    breadcrumb: false            if false, the item will hidden in breadcrumb(default is true)
    affix: true                  if true, the tag will affix in the tags-view
  }
**/
export const constantRouterMap = [
  {
    path: '/login',
    component: () => import('@/views/login/index'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'dashboard',
    children: [
      {
        path: 'dashboard',
        component: () => import('@/views/dashboard/index'),
        name: 'Dashboard',
        meta: { title: 'dashboard', icon: 'dashboard', noCache: true, affix: true }
      }
    ]
  },
  {
    path: '/404',
    component: () => import('@/views/errorPage/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/errorPage/401'),
    hidden: true
  },
]

export default new Router({
  // mode: 'history', // require service support
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

export const asyncRouterMap = [
  {
    path: '/article',
    component: Layout,
    redirect: '/article/list',
    name: '文章管理',
    meta: { title: 'articleManage', icon: 'example' },
    children: [
      {
        path: 'list',
        component: () => import('@/views/article/list'),
        name: 'ArticleList',
        meta: { title: 'articleList', icon: 'list', perm: 'article:list' }
      },
      {
        path: 'create',
        component: () => import('@/views/article/create'),
        name: 'CreateArticle',
        meta: { title: 'createArticle', icon: 'edit', perm: 'article:add' }
      },
      {
        path: 'edit/:id(\\d+)',
        component: () => import('@/views/article/edit'),
        name: 'EditArticle',
        meta: { title: 'editArticle', noCache: true, perm: 'article:update' },
        hidden: true
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/user/list',
    name: '用户管理',
    meta: {title: 'userManage', icon: 'user'},
    children: [
      {
        path: 'list', 
        name: '用户列表', 
        component: () => import('@/views/user/list'),
        meta: {title: 'userList', icon: 'user', perm: 'user:list'}
      },
      {
        path: 'create',
        name: '新增用户',
        component: () => import('@/views/user/create'),
        meta: {title: 'userCreate', icon: 'edit', perm: 'user:add'}
      },
      {
        path: 'edit/:id(\\d+)',
        component: () => import('@/views/user/edit'),
        name: '修改用户',
        meta: { title: 'editUser', noCache: true, perm: 'user:update' },
        hidden: true
      },
    ]
  },
  {
    path: '/role',
    name: '权限管理',
    component: Layout,
    redirect: '/role/list',
    meta: { title: 'permissionManage', icon: 'password' },
    children: [
      {
        path: 'list', 
        name: '权限列表', 
        component: () => import('@/views/role/list'),
        meta: {title: 'permissionList', icon: 'password', perm: 'user:list'}
      }
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]