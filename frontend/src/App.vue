<script>
import axios from './axios';
import {UserOutlined} from '@ant-design/icons-vue'
export default {
  name: 'App',
  components: {
    UserOutlined
  },
  data() {
    return {
      selectedKeys: ['Home'],
      userInfo: this.$store.state.user,
      ifLogin: this.$store.state.ifLogin
    }
  },
  methods: {
    handleMenuClick(e) {
      const path = e.key
      this.$router.push({ name: path })
    },
    goToLogin () {
      if (this.ifLogin) {
        this.$router.push({ name: 'MyPage' })
        this.selectedKeys = ['MyPage']
        return
      }
      this.$router.push({ name: 'Login' })
    }
  },
  beforeMount() {
    this.selectedKeys = [this.$route.name]
  }
}
</script>

<template>
  <a-layout class="main">
    <a-layout-header :style="{ position: 'fixed', zIndex: 1, width: '100%' }" class="header">
      <a-menu
        v-model:selectedKeys="selectedKeys"
        theme="dark"
        mode="horizontal"
        :style="{ lineHeight: '64px' }"
        @click="handleMenuClick"
      >
        <a-menu-item key="Home">主页</a-menu-item>
        <a-menu-item key="Plan">规划</a-menu-item>
        <a-menu-item key="Community">社区</a-menu-item>
        <a-menu-item key="MyPage">我的</a-menu-item>
      </a-menu>
      <div class="avatar" @click="goToLogin">
        <span style="color: #e3e3e3;" v-if="ifLogin">{{ userInfo.username }}</span>
        <span style="color: #e3e3e3;" v-else>登录</span>
        <a-avatar shape="square">
          <UserOutlined/>
        </a-avatar>
      </div>
    </a-layout-header>
    <a-layout-content :style="{ padding: '0 50px', marginTop: '64px', minHeight: '100vh' }">
      <router-view />
    </a-layout-content>
    <a-layout-footer :style="{ textAlign: 'center', backgroundColor: '#3e3e3e' }">
      <span style="color: #fff;">费子芸</span>
    </a-layout-footer>
  </a-layout>
</template>

<style scoped>
.main {
  position: relative;
  top: 0;
  left: 0;
}

.header {
  display: flex;
  justify-content: space-between;
}
.avatar {
  display: flex;
  align-items: center;
  cursor: pointer;
}
</style>
