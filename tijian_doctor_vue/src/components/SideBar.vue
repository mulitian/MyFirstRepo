<template>
  <aside class="sidebar">
    <ul>
      <li
        @click="selectItem('用户查询')"
        :class="{ active: selectedItem === '用户查询' }"
      >
        用户查询
      </li>
      <FilterBar
        v-if="isFilterBarVisible"
        :selectForm="selectForm"
        @update-orders="emitUpdateOrders"
      ></FilterBar>
      <div class="divider"></div>

      <li
        @click="selectItem('AI问诊')"
        :class="{ active: selectedItem === 'AI问诊' }"
      >
        AI问诊
      </li>
      <AIDialog :visible="AIVisible" @close="AIVisible = false"></AIDialog>
      
      <div class="divider"></div>
      
      <li @click="selectItem('数据分析')" :class="{ active: selectedItem === '数据分析' }">
        数据分析
      </li>
      <ChartDialog :visible="ChartVisible" @update:visible="ChartVisible = $event"></ChartDialog>
      <div class="divider"></div>
      <li @click="handleLogout">退出登录</li>
    </ul>
  </aside>
</template>  
  
<script setup>
import { ref, inject } from "vue";
import { useRouter } from "vue-router";

import FilterBar from "./FilterBar.vue";
import AIDialog from "./AIDialog.vue";
import ChartDialog from './ChartsDialog.vue';

const router = useRouter();

const isFilterBarVisible = ref(true);
const AIVisible = ref(false);
const ChartVisible = ref(false);
const selectedItem = ref("用户查询");
function selectItem(item) {
  selectedItem.value = item;
  // 这里可以添加其他逻辑，比如发送请求或调用方法等
  if (item == "用户查询") {
    isFilterBarVisible.value = !isFilterBarVisible.value;
    if (!isFilterBarVisible.value) {
      selectedItem.value = "";
    }
  } else {
    isFilterBarVisible.value = false;
  }
  if (item == "AI问诊") {
    AIVisible.value = !AIVisible.value;
  }
  if (item == "数据分析") {
    ChartVisible.value = !ChartVisible.value;
  }
}

const ordersPageResponseDto = inject("ordersPageResponseDto");
const selectPage = inject("selectPage");

const emit = defineEmits(["update-orders"]);

const selectForm = ref({
  userid: "",
  realname: "",
  sex: "",
  smid: "",
  orderdate: "",
  state: "",
});

function emitUpdateOrders(newOrders, total) {
  emit("update-orders", newOrders, total);
}

function handleLogout() {
  alert("您已成功退出登录！");
  // 清除选中项（如果需要的话）
  selectedItem.value = "";
  // 这里可以添加其他逻辑，如清除本地存储的token等
  router.push("/");
}
</script>  
  
<style scoped>
.sidebar {
  width: auto;
  background-color: #d3dce6;
  /* padding: 20px;   */
  /* box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);  
  border-radius: 8px;   */
}

.sidebar ul {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.sidebar li {
  cursor: pointer;

  width: 100%;
  padding: 20px 10px;
  /* border-radius: 4px;   */
  transition: background-color 0.3s ease;

  color: #333;
  font-size: 20px;
  text-align: center;
}

.sidebar li:hover {
  background-color: #c5ceda; /* 稍微深一点的颜色，用于鼠标悬停 */
}

.sidebar li.active {
  background-color: #9aa6b6; /* 深色高亮，表示当前选中 */
  color: #f5f5f5; /* 文字颜色改为白色，以增加对比度 */
}

.sidebar .divider {
  height: 1px;
  background-color: #ccc; /* 或其他你想要的分隔线颜色 */
}
</style>