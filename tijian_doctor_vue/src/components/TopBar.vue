<template>
  <div class="wrapper">
    <h1>梁山泊体检中心&nbsp;&nbsp;后台管理系统</h1>
    <div class="right-part">
      <p>医生：{{ doctor.realname }}</p>
      <div class="user-avatar">
        <img :src="avatar" alt="用户头像" @click="drawer = true" style="cursor: pointer;">
      </div>
    </div>
    <el-drawer size="300" v-model="drawer" title="I am the title" :with-header="false">
      <doctorMenu></doctorMenu>
    </el-drawer>
  </div>
</template>

<script setup>
import { reactive, toRefs, ref } from "vue";
import { useRouter } from "vue-router";
import { getSessionStorage } from "../common.js";

import doctorMenu from "./DoctorMenu.vue"

const doctor = ref();
const avatar = ref("src/img/yishengtouxiang.png");
const drawer = ref(false);


const init = () => {
  doctor.value = getSessionStorage("doctor");
}

init();

</script>


<style scoped>
h1 {
  font-size: 22px;
  font-weight: 700;
}

p {
  font-size: 16px;
}

.wrapper {  
  display: flex;  
  justify-content: space-between; /* 使得子元素之间等间距分布，从容器的一端到另一端 */  
  align-items: center; /* 确保子元素在交叉轴（这里是垂直方向）上居中对齐 */  
  width: 100%; /* 根据需要设置宽度，这里设为100%以占满容器宽度 */  
}  

.wrapper .right-part {
  display: flex;
  align-items: center;
}

.user-avatar {
  margin-left: 10px;
  display: flex;
  width: 30px;
  height: 30px;
}
</style>