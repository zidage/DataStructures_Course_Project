<script>
import { UserOutlined } from '@ant-design/icons-vue';
import MarkdownViewer from '@/components/MarkdownViewer.vue'
import axios from '@/axios';

export default {
  name: "MyPage",
  components: {
    UserOutlined,
    MarkdownViewer
  },
  data () {
    return {
      userName: this.$store.state.user.username,
      plans: {
        total: 0,
        plans: [],
        id: 0
      },
      visible: false,
      thisIndex: 0,
      modalInfo: {
        
      }
    }
  },
  methods: {
    // 登出
    handleLogout () {
      this.$store.commit('userLogout')
      this.$router.push({ name: 'Home' })
    },
    // 打开弹窗
    openModal (index) {
      this.visible = true
      this.thisIndex = index
    },
    // 删除计划
    handleDelete () {
      axios.delete('/my/plans', {
        id: this.thisIndex
      }).then(res => {
        this.$message.success('删除成功')
      })
    },
    // 修改计划
    handleOk () {
      axios.put('/my/plans', {
        id: this.thisIndex,
        content: this.plans.plans[this.thisIndex]
      }).then(res => {
        this.$message.success('修改成功')
      })
    }
  },
  beforeCreate() {
    axios.get('/my/plans').then(res => {
      this.plans = res.data
    })
  }
}
</script>

<template>
  <div>
    <div class="left-bar">
      <!-- 头像 -->
      <a-avatar size="large" style="margin: 20px 0;">
        <template #icon>
          <user-outlined />
        </template>
      </a-avatar>
      <!-- 用户名 -->
      <span class="username">{{ userName }}</span>
      <!-- 计划总数 -->
      <span class="plans">计划总数：{{ plans.total }}</span>
      <!-- 登出按钮 -->
      <a-button type="primary" @click="handleLogout" class="logout">登出</a-button>
    </div>
    <div class="content">
      <h1 class="title">我的计划</h1>
      <div class="line"></div>
      <div class="main">
        <template v-for="(item, index) in plans.plans" :key="index">
          <div>
            <span style="font-weight: bold;font-size: 2em;">Plan{{ index + 1 }} :</span>
            <a-button type="primary" style="margin-left: 30px;" @click="openModal(index)">编辑</a-button>
            <MarkdownViewer :markdown="item" />
          </div>
        </template>
        <a-modal title="编辑计划" v-model:open="visible">
          <template #footer>
            <a-button key="back" @click="handleDelete">删除</a-button>
            <a-button key="submit" type="primary" @click="handleOk">确定</a-button>
          </template>
          {{ thisIndex }}
        </a-modal>
      </div>
    </div>
  </div>
</template>

<style scoped>
.line {
  width: 80%;
  height: 1px;
  background-color: #e3e3e3;
  margin-top: 10px;
  margin-bottom: 20px;
}
.left-bar {
  width: 200px;
  min-height: 60vh;
  background-color: #e3e3e3;
  border: solid 1px #3e3e3e;
  border-radius: 10px;
  float: left;
  position: fixed;
  top: 100px;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.username {
  margin-top: 10px;
  font-size: 1.4em;
  font-family: 'KaiTi';
}

.plans {
  margin-top: 20px;
  font-size: 1.2em;
  font-family: 'WenQuanYi Micro Hei';
}

.logout {
  position: relative;
  margin-top: 100px;
  width: 60%;
}

.content {
  margin-top: 20px;
  margin-bottom: 20px;
  margin-left: 300px;
  width: 80%;
  min-height: 120vh;
  background-color: #fff;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.title {  
  margin-top: 40px;
  font-size: 2em;
  font-family: 'KaiTi';
}

.main {
  display: flex;
  flex-direction: column;
  width: 80%;
  gap: 40px;
}
</style>