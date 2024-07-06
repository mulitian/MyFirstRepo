<template>
  <h1>Neusoft&nbsp;&nbsp;智慧体检医生端管理系统</h1>
  <div style="display: flex;align-items: center;">
    <p>医生：{{ doctor.realname }}</p>
    <div class="user-avatar">
      <img :src="avatar" alt="用户头像" @click="drawer = true" style="cursor: pointer;">
    </div>
  </div>
  <el-drawer size="300" v-model="drawer" title="I am the title" :with-header="false">
    <doctorMenu></doctorMenu>
  </el-drawer>
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

.user-avatar {
  margin-left: 10px;
  display: flex;
  width: 30px;
  height: 30px;
}
</style>