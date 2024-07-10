<template>  
  <div v-if="visible" class="custom-dialog-overlay">  
    <div class="custom-dialog">  
      <div class="custom-dialog-header">  
        <slot name="header">各分院订单分布图</slot>  
        <button @click="closeDialog">关闭</button>  
      </div>  
      <div class="custom-dialog-body">  
        <slot>
          <BarChart></BarChart>
        </slot> <!-- 这里将插入 BarChart 组件 -->  
      </div>  
    </div>  
  </div>  
</template>  
  
<script setup>  
import { ref, defineProps, defineEmits  } from 'vue';  
import BarChart from './echarts/BarChart.vue';  

const emit = defineEmits(['update:visible']);  

const props = defineProps({  
  visible: {  
    type: Boolean,  
    required: true  
  }  
}) 
  
const openDialog = () => {  
  visible.value = true;  
};  
  
const closeDialog = () => {  
  emit('update:visible', false);  
};  
  
// 暴露 openDialog 和 closeDialog 方法，以便父组件可以控制对话框  
defineExpose({  
  openDialog,  
  closeDialog  
});  
</script>  
  
<style scoped>  
.custom-dialog-overlay {  
  z-index: 999;

  position: fixed;  
  top: 0;  
  left: 0;  
  width: 100%;  
  height: 100%;  
  background-color: rgba(0, 0, 0, 0.5);  
  display: flex;  
  align-items: center;  
  justify-content: center;  
}  
  
.custom-dialog {  
  background-color: #fff;  
  padding: 20px;  
  border-radius: 5px;  
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);  
}  
  
.custom-dialog-header {  
  display: flex;  
  justify-content: space-between;  
  align-items: center;  
}  
  
.custom-dialog-header button {  
  border: none;  
  background: none;  
  cursor: pointer;  
  font-size: 16px;  
}  
  
/* .custom-dialog-body {  
}   */
</style>