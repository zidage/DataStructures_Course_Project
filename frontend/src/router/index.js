import { createRouter, createWebHashHistory } from 'vue-router'
import store from '@/store'

const routes = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'Home',
    component: () => import('../views/Home.vue')
  },
  {
    path: '/plan',
    name: 'Plan',
    component: () => import('../views/Plan.vue'),
    meta: {
      needLogin: true
    }
  },
  {
    path: '/community',
    name: 'Community',
    component: () => import('../views/CommunPage.vue'),
    meta: {
      needLogin: true
    }
  },
  {
    path: '/my',
    name: 'MyPage',
    component: () => import('../views/MyPage.vue'),
    meta: {
      needLogin: true
    }
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  }
]


const router = createRouter({
  history: createWebHashHistory(),
  routes,
})

// 路由守卫
router.beforeEach((to, from, next) => {
  if (to.path === '/login' && store.state.ifLogin) {
    next('/my')
    return
  }
  if (to.meta.needLogin && !store.state.ifLogin) {
    next('/login')
    return
  }
  next()
})

export default router


