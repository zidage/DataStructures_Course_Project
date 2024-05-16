<script>
import axios from '@/axios';
import {SettingOutlined, EditOutlined, EllipsisOutlined} from '@ant-design/icons-vue'

export default {
  name: "CommunPage",
  components: {
    SettingOutlined,
    EditOutlined,
    EllipsisOutlined
  },
  data () {
    return {
      plans: {
        total: 0,
        plans: []
      },
      thisIndex: 0,
      modalInfo: {
        
      }
    }
  },
  methods: {
  },
  beforeCreate() {
    axios.get('/community/plans').then(res => {
      this.plans = res.data
    })
  }
}
</script>

<template>
  <div class="bg">
    <div style="width: 100%;align-items: center;text-align: center;margin-top: 30px;display: flex;flex-direction: column;">
      <span style="font-size: 2em;">导览社区</span>
      <div class="line"></div>
    </div>
    <div class="main">
      <template v-for="(item, index) in plans.plans" :key="index">

        <div>
          <a-card hoverable style="width: 300px">
            <template #cover>
              <img
                alt="example"
                src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
              />
            </template>
            <template #actions>
              <setting-outlined key="setting" />
              <edit-outlined key="edit" />
              <ellipsis-outlined key="ellipsis" />
            </template>
            <a-card-meta :title="item.title" :description="'@'+item.name">
              <template #avatar>
                <a-avatar src="https://joeschmoe.io/api/v1/random" />
              </template>
            </a-card-meta>
          </a-card>
        </div>
      </template>
    </div>
  </div>
  
</template>

<style scoped>
.bg {
  width: 90%;
  margin: auto;
  background-color: #fff;
  padding: 20px;
  padding-top: 10px;
}
.line {
  width: 80%;
  height: 1px;
  background-color: #e3e3e3;
  margin-top: 20px;
  margin-bottom: 20px;
}
.main {
  margin-top: 30px;
  display: flex;
  align-items: center;
  width: 100%;
  gap: 40px;
  flex-wrap: wrap;
  justify-content: center;
  margin-bottom: 30px;
}
</style>