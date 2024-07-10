<template>
  <div class="wrapper">
    <div class="inner-container">
      <div class="box">
        <h1>梁山泊医疗系统</h1>
        <input
          type="text"
          name=""
          required=""
          v-model="loginForm.doccode"
          placeholder="Username"
        />
        <input
          type="password"
          name=""
          required=""
          v-model="loginForm.password"
          placeholder="Password"
        />
        <button @click="login()">Login</button>
        <p>Not a member? <span>Sign Up</span></p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { reactive, toRefs, ref } from "vue";
import { useRouter } from "vue-router";
import { setSessionStorage } from "../common.js";
import { ElMessage } from "element-plus";

import axios from "axios";
// axios.defaults.baseURL = "http://10.25.161.184:8079/doctor/";
axios.defaults.baseURL = "http://localhost:8079/doctor/";
const router = useRouter();

const loginForm = reactive({
  doccode: "",
  password: "",
});

const open2 = () => {
  ElMessage({
    message: "登陆成功",
    type: "success",
  });
};

const open4 = () => {
  ElMessage.error("账号或密码错误！");
};

const login = () => {
  if (loginForm.doccode == "") {
    alert("医生编码不能为空！");
    return;
  }
  if (loginForm.password == "") {
    alert("密码不能为空！");
    return;
  }

  axios
    .post("login", loginForm)
    .then((response) => {
      console.log(response.data);
      if (response.data.code == 200) {
        let doctor = response.data.result;
        setSessionStorage("doctor", doctor);
        open2();
        router.push("/ordersList");
      } else if (response.data.code == 401) {
        open4();
        //alert("账号或密码错误！");
      }
    })
    .catch((error) => {
      console.error(error);
    });
};
</script>

<style scoped>
body {
  padding: 0;
  margin: 0;
}

.wrapper {
  display: block !important;
  background-image: url("../img/boat-5889919_1280.png");
  background-size: cover;

  width: auto;
  height: 100vh;
  background-color: #f8f9fa;
}

.inner-container {
  width: 400px;
  height: 400px;
  position: absolute;
  top: calc(50vh - 200px);
  left: calc(50vw - 200px);
  overflow: hidden;
}
.box {
  position: absolute;
  height: 100%;
  width: 100%;
  font-family: Helvetica;
  color: #fff;
  background: rgba(0, 0, 0, 0.5);
  padding: 30px 0px;
}
.box h1 {
  text-align: center;
  margin: 10px 0;

  font-family: "黑体", SimHei, sans-serif;
  font-weight: bolder;
  font-size: 45px;

  /* color: #1c51a3; */
}
.box input {
  display: block;
  width: 300px;
  margin: 20px auto;
  padding: 15px;
  background: rgba(0, 0, 0, 0.2);
  color: #fff;
  border: 0;
}
.box input:focus,
.box input:active,
.box button:focus,
.box button:active {
  outline: none;
}
.box button {
  background: #1c51a3;
  border: 0;
  color: #fff;
  padding: 10px;
  font-size: 20px;
  width: 330px;
  margin: 20px auto;
  display: block;
  cursor: pointer;
}
.box button:active {
  background: #1c51a3;
}
.box p {
  font-size: 14px;
  text-align: center;
}
.box p span {
  cursor: pointer;
  color: #c9c9c9;
}
</style>
