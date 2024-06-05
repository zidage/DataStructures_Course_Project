<script setup>
import { ref, onMounted } from 'vue';
import { ElMessage } from 'element-plus';
import { getDiaryByIdService, updateDiaryService } from '@/api/diary.js';
import { rateDiaryService } from '@/api/diary.js';
import { getPlanByIdService } from '@/api/plan.js';
import { useRouter } from 'vue-router';
import { usePlanStore } from '@/stores/plan.js';
import { QuillEditor } from '@vueup/vue-quill'
import '@vueup/vue-quill/dist/vue-quill.snow.css'
const value2 = ref(0)
const colors = ref(['#99A9BF', '#F7BA2A', '#FF9900'])
const currentRating = ref(0)

let diaryResponse = ref([])
let diary = ref(null);
let plan = ref(null);
let place = ref(null);
const myRating = ref(0);
let route = useRouter();

let planStore = usePlanStore();


const fetchPlan = async () => {
    const planId = planStore.currentPlanId;
    if (planId) {
        // console.log(planId)
        const response = await getPlanByIdService(planId);
        plan.value = response.data;
        // console.log(plan.value.plan.mapView)
    }
};

fetchPlan()
onMounted(fetchPlan);


</script>



<template>
    <div class="map-section" v-if="plan">
        <h2>相关计划: {{ plan.plan.title }}</h2>
        <!-- 这里可以放置一个地图组件，例如使用高德地图、百度地图等提供的Vue组件 -->
        <div id="mapContainer" style="width: 100%; height: 400px;">
            <iframe width="100%" height="100%" :src=plan.plan.mapView frameborder="0" allowfullscreen>
            </iframe>
        </div>
        <el-table :data="plan.venues" style="width: 100%">
            <el-table-column prop="name" label="途径场所" />
        </el-table>
        <el-descriptions :column="2" size="default" class="mt-4">
            <el-descriptions-item label="浏览策略">
                {{ plan.plan.strategy == 'DIST' ? '距离优先' : '时间优先' }}
            </el-descriptions-item>

            <el-descriptions-item label="交通工具">
                {{ plan.plan.transport == 'WALK' ? '步行' : '骑行' }}
            </el-descriptions-item>

            <el-descriptions-item label="路线距离">
                {{ plan.plan.distance }}m
            </el-descriptions-item>

            <el-descriptions-item label="路线花费时间">
                {{ Math.floor(plan.plan.requiredTime / 60) }}min
                {{ Math.floor(plan.plan.requiredTime % 60) }}s
            </el-descriptions-item>


        </el-descriptions>
    </div>
</template>



<style scoped>
#app {
    display: flex;
    flex-direction: column;
    height: 100vh;
}

.content {
    display: flex;
    flex: 1;
    /* 占据剩余空间 */
}

.diary-section,
.map-section {
    flex: 1;
    /* 等分内容区域 */
    padding: 20px;
}

.diary-section {
    border-right: 1px solid #ebeef5;
    /* 为日记区域添加右边框 */
}

.demo-rate-block {
    padding: 5px;
    text-align: center;
    border-right: solid 1px var(--el-border-color);
    display: inline-block;
    width: 49%;
    box-sizing: border-box;
}

.demo-rate-block:last-child {
    border-right: none;
}

.demo-rate-block .demonstration {
    display: block;
    color: var(--el-text-color-secondary);
    font-size: 14px;
    margin-bottom: 0px;
}

.flex.items-center {
    display: flex;
    align-items: center;
    /* 添加这一行来确保在交叉轴上居中对齐 */
    /* 其他样式 */
}

.editor {
    width: 100%;

    :deep(.ql-editor) {
        min-height: 200px;
    }
}
</style>