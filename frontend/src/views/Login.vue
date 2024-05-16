<script>
import axios from '@/axios';

export default {
  name: "Login",
  data () {
    return {
      // 输入的数据
      loginForm: {
        username: '',
        password: ''
      },
      // 注册的数据
      registerForm: {
        username: '',
        password: '',
        passwordAgain: ''
      },
    }
  },
  methods: {
    // 处理登录事件
    handleLogin () {
      // 判断输入是否合法
      if (!this.loginForm.username || !this.loginForm.password) {
        this.$message.error('请输入用户名和密码')
        return
      }
      axios.post('/user/login', {
        username: this.loginForm.username,
        password: this.loginForm.password
      }).then(res => {
        this.$store.commit('setUser', res.data) 
        location.reload()
        this.$router.push({ name: 'Home' })
      }).catch(err => {
        console.log(err)
      })
    },
    // 处理注册事件
    handleRegister () {
      // 输入判断
      if (!this.registerForm.username || !this.registerForm.password || !this.registerForm.passwordAgain) {
        this.$message.error('请输入用户名和密码')
        return
      }
      // 两次密码不一致
      if (this.registerForm.password !== this.registerForm.passwordAgain) {
        this.$message.error('两次密码不一致')
        return
      }
      // 注册
      axios.post('/user/regist', {
        username: this.registerForm.username,
        password: this.registerForm.password
      }).then(res => {
        this.$store.commit('setUser', res.data) 
        location.reload()
        this.$router.push({ name: 'Home' })
      }).catch(err => {
        console.log(err)
        this.$message.error('注册失败')
      })
    }
  }
}
</script>
<template>
  <!-- 登陆界面 -->
  <div class="main">
    <!-- 左边登录，右边注册 -->
    <div class="login">
      <!-- 标题 -->
      <h1 class="title">登录</h1>
      <a-form class="form">
        <!-- 输入框 -->
        <a-form-item class="form-item">
          <a-input placeholder="用户名" v-model:value="loginForm.username" />
        </a-form-item>
        <a-form-item class="form-item">
          <a-input-password placeholder="密码" v-model:value="loginForm.password" />
        </a-form-item>
        <a-form-item class="form-item">
          <a-button type="primary" class="button" @click="handleLogin">登录</a-button>
        </a-form-item>
      </a-form>
    </div>
    <div class="register">
      <h1 class="title">注册</h1>
      <a-form class="form">
        <a-form-item class="form-item">
          <a-input placeholder="用户名" v-model:value="registerForm.username" />
        </a-form-item>
        <a-form-item class="form-item">
          <a-input-password placeholder="密码" v-model:value="registerForm.password" />
        </a-form-item>
        <a-form-item class="form-item">
          <a-input-password placeholder="再次输入密码" v-model:value="registerForm.passwordAgain"/>
        </a-form-item>
        <a-form-item class="form-item">
          <a-button type="primary" class="button" @click="handleRegister">注册</a-button>
        </a-form-item>
      </a-form>
    </div>
  </div>
</template>
<style scoped>
.main {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
.login {
  width: 50%;
}
.register {
  width: 50%;
}
/* 左右两边剧中 */
.form {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}
/* 放大输入框 */
.form-item {
  width: 70%;
  margin-bottom: 20px;
}
/* 标题居中 */
.title {
  text-align: center;
}
/* 按钮剧中 */
.button {
  width: 100%;
}
/* 左右中间加分割线 */
.login::after {
  content: '';
  display: block;
  width: 1px;
  height: 100%;
  background-color: #e3e3e3;
  position: absolute;
  right: 50%;
  top: 0;
}
</style>