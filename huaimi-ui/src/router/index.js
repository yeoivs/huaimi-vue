import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

  const _childPath_ = [{
    path: '/welcome',
    component: () => import('@/views/Welcome')
  }]

  const routes = [
  {
    path: '/',
  },
    {
      path: '/index',
      component: () => import('@/layout/LAYOUT'),
      redirect: '/welcome',
      children: _childPath_
    }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
