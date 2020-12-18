import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },
  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    meta: { title: '首页', icon: 'example' },
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },
  {
    path: '/example',
    component: Layout,
    redirect: '/example/table',
    name: 'Example',
    meta: { title: '基本信息管理', icon: 'example' },
    children: [
      { path: 'classes', name: 'classes', component: () => import('@/views/table/classes'), meta: { title: '班级管理', icon: 'table' }},
      { path: 'course', name: 'course', component: () => import('@/views/table/course'), meta: { title: '课程管理', icon: 'table' }},
      { path: 'grade', name: 'grade', component: () => import('@/views/table/grade'), meta: { title: '年级管理', icon: 'table' }},
      { path: 'score', name: 'score', component: () => import('@/views/table/score'), meta: { title: '成绩管理', icon: 'table' }},
      { path: 'student', name: 'student', component: () => import('@/views/table/student'), meta: { title: '学生管理', icon: 'table' }},
      { path: 'teacher', name: 'teacher', component: () => import('@/views/table/teacher'), meta: { title: '教师管理', icon: 'table' }}

    ]
  },
  {
    path: '/user',
    component: Layout,
    redirect: '/example/table',
    name: 'Example',
    meta: { title: '用户管理', icon: 'example' },
    children: [
      { path: 'user', name: '用户管理', component: () => import('@/views/table/user'), meta: { title: '用户管理', icon: 'table' }}
    ]
  },
  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

