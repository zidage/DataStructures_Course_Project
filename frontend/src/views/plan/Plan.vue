<template>
  <el-row style="height: 100vh;">
    <!-- 左侧可滑动部分 -->
    <el-col :span="12" class="left-column">
      <el-scrollbar class="scrollbar">
        <!-- 第一个卡片 -->
        <el-card class="card">
          <template #header>
            <div class="card-header">
              <el-button :icon="ArrowLeft" @click="goBack" class="back-button">返回</el-button>
              <span v-if="locationStore.location" class="university-name">{{ locationStore.location.name }}</span>
              <el-button class="next-button">
                生成计划
                <el-icon class="el-icon--right">
                  <ArrowRight />
                </el-icon>
              </el-button>
            </div>
          </template>
          <div class="card1-content">
            <div class="form-container">
              <div class="form-item title-item">
                <div class="form-label">标题</div>
                <el-input v-model="title" class="input-title" placeholder="请输入标题" />
              </div>
              <div class="form-item select-item">
                <div class="form-label">交通工具</div>
                <el-select v-model="transport" placeholder="选择交通工具" clearable class="select-inline">
                  <el-option label="走路" value="WALK" />
                  <el-option label="骑行" value="BIKE" />
                </el-select>
              </div>
              <div class="form-item select-item">
                <div class="form-label">策略</div>
                <el-select v-model="strategy" placeholder="选择策略" clearable class="select-inline">
                  <el-option label="时间优先" value="TIME" />
                  <el-option label="距离优先" value="DIST" />
                </el-select>
              </div>
            </div>
          </div>
        </el-card>
        <!-- 第二个卡片 -->
        <el-card class="card">
          <template #header>
            <div class="card-header">
              <span class="left-text">已选场所</span>
              <div class="right-container">
                <span class="departure-label">出发地</span>
                <el-select v-model="departure" placeholder="请选择出发地" clearable class="select-inline"
                  style="width: 200px;">
                  <el-option v-for="item in selectedItems" :key="item.id" :label="item.name" :value="item" />
                </el-select>
              </div>
            </div>
          </template>
          <div class="button-container">
            <el-button v-for="item in selectedItems" :key="item.id" type="primary" plain @click.stop="removeItem(item)">
              {{ item.name }}
              <el-icon class="el-icon--right">
                <Delete />
              </el-icon>
            </el-button>
          </div>
        </el-card>
        <!-- 第三个卡片 -->
        <el-card class="card">
          <template #header>
            <div class="card-header">
              <span class="left-text">全部场所</span>
              <div class="right-container">
                <span class="departure-label">类别</span>
                <el-select v-model="category" placeholder="选择类别" clearable class="select-inline" style="width: 100px;">
                  <el-option v-for="option in placeTypes" :key="option.value" :label="option.label"
                    :value="option.value" />
                </el-select>
              </div>
            </div>
          </template>
          <div class="button-container">
            <el-button v-for="item in filteredItems" :key="item.id" type="primary" plain @click="selectItem(item)">
              {{ item.name }}
            </el-button>
          </div>
        </el-card>
        <!-- 第四个卡片 -->
        <el-card class="card">
          <template #header>
            <div class="card-header">
              <span class="left-text">筛选周边场所</span>
              <div class="right-container">
                <el-button :icon="Search" @click="fetchSurroundingPlaces">查询</el-button>
              </div>
              <!-- 搜索内容 -->
            </div>
          </template>
          <div class="button-container">
            <div>
              <span class="departure-label">场所</span>
              <el-select v-model="selectedPlace" placeholder="选定场所" clearable class="select-inline"
                style="width: 200px;">
                <el-option v-for="item in selectedItems" :key="item.id" :label="item.name" :value="item" />
              </el-select>
              <span class="departure-label" style="padding-left: 10px;">距离</span>
              <el-select v-model="selectedRadius" placeholder="距离" clearable class="select-inline" style="width: 90px;">
                <el-option label="50m" value="50" />
                <el-option label="150m" value="150" />
                <el-option label="250m" value="250" />
                <el-option label="500m" value="500" />
                <el-option label="1000m" value="1000" />
                <el-option label="2000m" value="2000" />
              </el-select>
              <span class="departure-label" style="padding-left: 10px;">类别</span>
              <el-select v-model="type" placeholder="选择场所类别" clearable class="select-inline" style="width: 100px;">
                <el-option v-for="option in placeTypes" :key="option.value" :label="option.label"
                  :value="option.value" />
              </el-select>
            </div>
            <el-button v-for="item in surroundingItems" :key="item.id" type="primary" plain @click="selectItem(item)">
              {{ item.name }}
            </el-button>
          </div>
        </el-card>
      </el-scrollbar>
    </el-col>
    <!-- 右侧固定部分 -->
    <el-col :span="12" class="right-column">
      <img src="https://via.placeholder.com/600x400" alt="Placeholder Image" class="placeholder-image">
    </el-col>
  </el-row>
</template>

<script setup>
  import { ref, computed } from 'vue';
  import { useRouter } from 'vue-router';
  import { ElMessage } from 'element-plus';
  import { useLocationStore } from '@/stores/location.js';
  import { ArrowLeft, ArrowRight, Delete, Search } from '@element-plus/icons-vue';
  import placeTypes from '@/assets/placeTypes.js';
  import testData from '@/assets/testData.js';
  import { getVenuesByPlaceIdService, getSurroundingPlacesService } from '@/api/plan.js';

  const router = useRouter();
  const locationStore = useLocationStore();

  // 返回上一页事件
  const goBack = () => {
    router.push('/plan/selectlocation');
  };

  // 表单数据
  const title = ref('');
  const transport = ref('');
  const strategy = ref('');
  const departure = ref(null);
  const selectedPlace = ref(null);
  const selectedRadius = ref('');
  const category = ref('');
  const type = ref('');

  // 原始数据
  const originalItems = ref(testData);

  // 已选地点
  const selectedItems = ref([]);

  // 周边场所
  const surroundingItems = ref([]);

  // 筛选符合类别的地点
  const filteredItems = computed(() => {
    return category.value
      ? originalItems.value.filter((item) => item.type === category.value)
      : originalItems.value;
  });

  // 选择地点
  const selectItem = (item) => {
    if (!selectedItems.value.some(selected => selected.id === item.id)) {
      selectedItems.value.push(item);
    } else {
      ElMessage.error('已添加');
    }
  };

  // 删除已选地点
  const removeItem = (item) => {
    selectedItems.value = selectedItems.value.filter(selected => selected.id !== item.id);
  };

  // 获取原始数据
  const fetchOriginalItems = async () => {
    const placeId = locationStore.location.id;
    console.log(placeId);
    const result = await getVenuesByPlaceIdService(placeId, {});
    originalItems.value = result.data.items;
  };

  // 获取周边场所数据
  const fetchSurroundingPlaces = async () => {
    const placeId = locationStore.location.id; // 获取 placeId
    const venueId = selectedPlace.value.id; // 获取选定场所的 id
    console.log(placeId);
    console.log(venueId);

    if (!selectedPlace.value) {
      ElMessage.error('未选择中心场所');
      return;
    }
    const query = {
      type: type.value,
      radius: selectedRadius.value,
    };

    const response = await getSurroundingPlacesService(placeId, venueId, query);
    surroundingItems.value = response.data;
  };


  fetchOriginalItems();
</script>



<style scoped>
  .left-column {
    height: 100%;
    padding-right: 10px;
  }

  .scrollbar {
    padding-right: 10px;
  }

  .right-column {
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: #f0f0f0;
    position: sticky;
    top: 0;
    height: 100vh;
  }

  .placeholder-image {
    width: 100%;
    height: auto;
    max-width: 600px;
    max-height: 400px;
  }

  .card {
    width: calc(100% - 10px);
    margin-bottom: 20px;
    margin-right: 10px;
  }

  .button-container {
    display: flex;
    flex-wrap: wrap;
    gap: 10px;
  }

  .button-container .el-button {
    margin-bottom: 5px;
    margin-left: 5px;
  }

  .card-header {
    padding: 0;
    display: flex;
    align-items: center;
    justify-content: space-between;
  }

  .card-header-content {
    display: flex;
    align-items: center;
    gap: 20px;
    margin-left: auto;
  }

  .university-name {
    display: inline-block;
    font-weight: bold;
    font-size: 18px;
  }

  .form-container {
    display: flex;
    align-items: center;
    gap: 10px;
    margin-top: 10px;
  }

  .form-item {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .title-item {
    flex: 2.5;
  }

  .select-item {
    flex: 1;
  }

  .form-label {
    margin-bottom: 5px;
  }

  .input-title {
    width: 100%;
  }

  .select-inline {
    width: 100%;
  }

  .back-button {
    margin-right: 30px;
  }

  .next-button {
    margin-left: auto;
  }

  .departure-label {
    display: inline-block;
    margin-right: 5px;
  }

  .left-text {
    display: inline-block;
    margin-right: auto;
  }

  .right-container {
    display: flex;
    align-items: center;
  }
</style>
