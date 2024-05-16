<script>
import { CloseOutlined } from '@ant-design/icons-vue';
export default {
  name: "Plan",
  components: {
    CloseOutlined
  },
  data() {
    return {
      map: ["教学楼","食堂","宿舍","操场","图书馆"]
    }
  },
}
</script>

<template>
  <div class="main">
    <div class="map">

    </div>
    <div class="divider"></div>
    <div class="place">
      <a-timeline>
        <template v-for="(item, index) in map" :key="index">
          <a-timeline-item>
            <div style="display: flex;">
              <span>{{item}}</span>
              <div style="display: flex;flex-direction: column;justify-content: center;align-items: center;margin-left: 5px;">
                <a-button shape="circle" size="small">
                  <CloseOutlined />
                </a-button>
              </div>
            </div>
          </a-timeline-item>
        </template>
      </a-timeline>
    </div>
  </div>
</template>

<style scoped>
 
 .main {
  margin:auto;
  display: flex;
  width: 80%;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #fff;
  height: 100vh;
}
.map {
  flex: 7;
  display: flex;
  align-items: center;
  justify-content: center;
}
.map img {
  max-width: 100%;
  max-height: 100%;
}
.divider {
  width: 1px;
  background: #ccc;
}
.place {
  flex: 3;
  padding: 20px;
}
.place ul {
  list-style-type: none;
  padding: 0;
}
.place li {
  background: #f0f0f0;
  margin: 10px 0;
  padding: 10px;
  border-radius: 5px;
}
</style>
