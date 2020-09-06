import Vue from 'vue'
import VueRouter from 'vue-router'

import storage from "@/utils/storage";

Vue.use(VueRouter)

const childRoutes = [
  {
    path: '/control',
    component: () => import('@/views/control'),
    meta: {
      nav: [
        { name: '控制台', url: '/control' },
      ],
      title: '控制台'
    }
  },
  {
    path: '/system/permission',
    component: () => import('@/views/permission'),//resolve => require(['@/views/permission'], resolve),
    meta: {
      nav: [
        { name: '系统管理'},
        { name: '权限管理', url: '/system/permission' },
      ],
      title: '权限管理'
    }
  },
  {
    path: '/system/role',
    component: () => import('@/views/role'),
    meta: {
      nav: [
        { name: '系统管理'},
        { name: '角色管理', url: '/system/role' },
      ],
      title: '角色管理'
    }
  },
  {
    path: '/system/user',
    component: () => import('@/views/user'),
    meta: {
      nav: [
        { name: '系统管理'},
        { name: '用户管理', url: '/system/user' },
      ],
      title: '用户管理'
    }
  },
  {
    path: '/profile',
    component: () => import('@/views/profile'),
    meta: {
      nav: [
        { name: '用户中心'},
        { name: '个人资料', url: '/profile' },
      ],
      title: '个人资料'
    }
  },
  {
    path: '/passwd',
    component: () => import('@/views/profile/passwd'),
    meta: {
      nav: [
        { name: '用户中心'},
        { name: '修改密码', url: '/passwd'},
      ],
      title: '修改密码'
    }
  }
]

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('@/views/Home')
  },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/login')
    },
    {
      path: '/index',
      component: () => import('@/components/layout/index'),
      redirect: '/control',
      children: childRoutes
    },
    {
      path: '*',
      component: () => import('@/views/notFound')
    }
]


const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach(((to, from, next) => {
  if(to.meta['title']){
    document.title = to.meta['title']
  }
  if(to.name === 'login') {
    next()
  } else {
    if(storage.getToken()){
      storage.set('activeMenu', to.path)
      next()
    }else {
      next({path: '/login'})
    }
  }
}))


export default router
