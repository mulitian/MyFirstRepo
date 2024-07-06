<template>
  <div class="bg-container">
  <div class="card-container">
  <el-card class="mycard" style="max-width: 480px;">
    <template #header>
      <div style="text-align: center;" class="card-header">
        <span>登陆</span>
      </div>
    </template>
    <el-form :model="form" label-width="auto" style="max-width: 600px">
        <el-form-item label="用户名：">
        <el-input v-model="loginForm.doccode" />
        </el-form-item>

      <el-form-item label="密码：">
        <el-input v-model="loginForm.password" type="password" />
       </el-form-item>

       <el-form-item > 
           <el-button style="margin-left: 130px;" type="primary" @click="login">登陆</el-button>
       </el-form-item>
    </el-form>
    <template #footer>
      <span style="margin-left: 100px;">东软睿道版权所有</span>
    </template>
  </el-card>
  </div>
</div>
</template>

<script setup>
import { reactive, toRefs, ref } from "vue";
import { useRouter } from "vue-router";
import { setSessionStorage } from "../common.js";
import axios from "axios";
// axios.defaults.baseURL = "http://10.25.161.184:8079/doctor/";
axios.defaults.baseURL = "http://localhost:8079/doctor/";
const router = useRouter()

const loginForm = reactive({
    doccode: "",
    password: "",
});

const login = () => {
      if(loginForm.doccode==''){
        alert('医生编码不能为空！');
        return;
      }
      if(loginForm.password==''){
        alert('密码不能为空！');
        return;
      }

      axios
        .post("login", loginForm)
        .then((response) => {
          console.log(response.data);
          if(response.data.code == 200){
            let doctor = response.data.result;
            setSessionStorage("doctor", doctor);
            router.push("/ordersList");
          }
          else if(response.data.code == 401){
            alert("账号或密码错误！");
          }
        })
        .catch((error) => {
          console.error(error);
        });
    };

</script>

<style scoped>
.mycard{
  margin:0 auto; /* 希望它水平居中 */
  width:350px; /*  可以调整宽和高   */
  align-content: center;
}

.bg-container {
  position: absolute; /* 绝对定位 */  
  top: 0; /* 顶部对齐 */  
  left: 0; /* 左侧对齐 */  
  right: 0; /* 右侧对齐 */  
  bottom: 0; /* 底部对齐 */  
  box-sizing: border-box;
  background-image: url('src/assets/loginBackground.jpeg');
  background-size: cover;
  background-position:100;
}

.card-container {  
  display: flex;  
  justify-content: center; /* 水平居中 */  
  align-items: center; /* 垂直居中 */  
  height: 100vh; /* 如果你想让卡片占据整个视口的高度 */  
}  
</style>