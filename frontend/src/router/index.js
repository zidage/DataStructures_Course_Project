import { createRouter, createWebHistory } from 'vue-router'
//导入组件
import LoginVue from '@/views/Login.vue'
import LayoutVue from '@/views/Layout.vue'
import UserAvatarvue from '@/views/user/UserAvatar.vue'
import UserInfoVue from '@/views/user/UserInfo.vue'
import UserDiaryVue from '@/views/user/UserDiary.vue'
import CommunityVue from '@/views/Community.vue'
import PlanVue from '@/views/plan/Plan.vue'
import SelectLocation from '@/views/plan/SelectLocation.vue'
import UserResetPasswordVue from '@/views/user/UserResetPassword.vue'
import ShowDiaryVue from '@/views/diary/ShowDiary.vue'
import { useTokenStore } from '@/stores/token.js';
//定义路由关系
const routes = [
  { path: '/login', component: LoginVue },
  { path: '/home', component: LayoutVue },

  {
    path: '/', component: LayoutVue, redirect: '/home', children: [
      { path: '/user/avatar', component: UserAvatarvue },
      { path: '/user/info', component: UserInfoVue },
      { path: '/user/resetPassword', component: UserResetPasswordVue },
      { path: '/community', component: CommunityVue },
      { path: '/user/diary', component: UserDiaryVue },
      { path: '/plan', component: PlanVue },
      { path: '/plan/selectLocation', component: SelectLocation },
      { path: '/diary/show', component: ShowDiaryVue }
    ]
  }
]
//创建路由实例
const router = createRouter({
  history: createWebHistory(),
  routes: routes
})

//路由守卫
router.beforeEach((to, from, next) => {
  //判断是否有token
  const tokenStore = useTokenStore()
  const token = tokenStore.token
  if (to.path !== '/login' && !token) {
    next('/login')
  } else {
    next()
  }
})


//导出路由实例
export default router