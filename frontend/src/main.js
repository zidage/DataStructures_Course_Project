import { createApp } from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue';
import router from './router';
import store from './store';
import axios from '@/axios'

if (localStorage.getItem('token')) {
  await axios.post('/user/token').then(res => {
    store.commit('setUser', res.data)
  }).catch(() => {
    store.commit('userLogout')
  })
}

const app = createApp(App)
app.use(Antd).use(router).use(store).mount('#app')
