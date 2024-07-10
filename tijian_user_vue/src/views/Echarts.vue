<template>
  <div>
    <MealCharts :sex="sex" :hp-id="hpId"></MealCharts>
  </div>
</template>  
  
<script setup>
import MealCharts from "@/components/MealCharts.vue";

import { ref } from "vue";

// 假设 user 是从其他地方（如 API 调用、父组件的 props 等）获取的
const user = ref({ name: "Alice", sex: 0, age: 30 });
const sex = user.value.sex;
// hpId 可以是任何来源，这里直接定义为一个示例值
const hpId = 1;
</script>