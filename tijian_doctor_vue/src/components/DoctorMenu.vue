<template>
  <div>
    <div class="sidebar">
      <div class="sidebar-header">
        <el-avatar :size="100" :src="circleUrl" />
      </div>
      <div class="sidebar-info">
        <h4>姓名: {{ doctor.realname }}</h4>
        <p>所属科室: {{ deptname }}</p>
      </div>
      <div class="sidebar-menu">
        <ul>
          <li><a href="#">个人中心</a></li>
          <li><a href="#" @click="changePW">修改密码</a></li>
          <li><a href="#" @click="AIhelper">AI助手</a></li>
          <li><a href="#" @click="openJK">健康小助手</a></li>
          <li><a href="#" @click="onEmail">邮件发送</a></li>
          <li><a href="#" @click="back">退出登陆</a></li>
          <!-- 添加更多菜单项 -->
        </ul>
      </div>
    </div>
  </div>
  <el-dialog v-model="dialogVisible" title="修改密码" width="40%">
    <span>
      <el-form :model="CPW" label-width="120px">
        <el-form-item label="新密码">
          <el-input v-model="CPW.password" style="width: 300px"></el-input>
        </el-form-item>
        <el-form-item label="确认密码">
          <el-input v-model="CPW.repassword" style="width: 300px"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="rePW">确定</el-button>
          <el-button @click="dialogVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </span>
  </el-dialog>

  <el-dialog v-model="AIVisible" width="50%">
    <div class="flex flex-col h-screen bg-gray-50 dark:bg-gray-900">
      <header class="bg-[#e0f2fe] dark:bg-gray-800 shadow-sm p-4 flex items-center justify-between">
        <div class="flex items-center space-x-2">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
            stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
            class="h-6 w-6 text-gray-500 dark:text-gray-400">
            <path d="M17 18a2 2 0 0 0-2-2H9a2 2 0 0 0-2 2"></path>
            <rect width="18" height="18" x="3" y="4" rx="2"></rect>
            <circle cx="12" cy="10" r="2"></circle>
            <line x1="8" x2="8" y1="2" y2="4"></line>
            <line x1="16" x2="16" y1="2" y2="4"></line>
          </svg>
          <h1 class="text-xl font-medium text-gray-900 dark:text-gray-50">医生小助手</h1>
        </div>
      </header>
      <div class="flex-1 overflow-auto p-4">
        <div style="margin-top: 10px;" class="max-w-md mx-auto space-y-4" v-for="(it, index) in dialog" :key="index">
          <div class="bg-white dark:bg-gray-800 rounded-lg shadow-sm p-4">
            <p class="text-gray-700 dark:text-gray-300">
              {{ it }}
            </p>
          </div>
        </div>
      </div>
      <div class="bg-white dark:bg-gray-800 shadow-t p-4 flex items-center space-x-2">
        <input v-model="text"
          class="flex h-10 w-full rounded-md border border-input px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 flex-1 bg-gray-100 dark:bg-gray-700 border-none focus:ring-0 focus:outline-none"
          placeholder="输入你的问题" type="text" />
        <button
          class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 w-10">
          <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none"
            stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"
            class="h-5 w-5 text-gray-500 dark:text-gray-400">
            <circle cx="12" cy="12" r="10"></circle>
            <path d="M8 14s1.5 2 4 2 4-2 4-2"></path>
            <line x1="9" x2="9.01" y1="9" y2="9"></line>
            <line x1="15" x2="15.01" y1="9" y2="9"></line>
          </svg>
        </button>
        <button @click="submit"
          class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 w-10">发送</button>
      </div>
    </div>
  </el-dialog>

  <el-dialog v-model="JKVisible" title="健康小助手" width="70%">
    <span>
      <el-form :model="emailform" label-width="120px">
        <el-form-item label="问题">
          <el-input v-model="question.ask"></el-input>
        </el-form-item>
        <el-form-item label="回答">
          <el-input v-model="question.answer" disabled type="textarea" :rows="6"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="healthskill">提问</el-button>
          <el-button type="primary" @click="clearans">清空</el-button>
          <el-button @click="JKVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </span>
  </el-dialog>

  <el-dialog v-model="emailVisible" title="邮件发送" width="70%">
    <span>
      <el-form :model="emailform" label-width="120px">
        <el-form-item label="收件人">
          <el-input v-model="emailform.address"></el-input>
        </el-form-item>
        <el-form-item label="标题">
          <el-input v-model="emailform.title"></el-input>
        </el-form-item>
        <el-form-item label="正文">
          <el-input v-model="emailform.content" type="textarea" :rows="6"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="sendEmail">发送</el-button>
          <el-button @click="emailVisible = false">取消</el-button>
        </el-form-item>
      </el-form>
    </span>
  </el-dialog>

</template>

<script setup>

import { reactive, toRefs, ref } from "vue";
import { getSessionStorage } from "../common.js";
import axios from "axios";
import { ElMessage } from 'element-plus';
import Qs from 'qs'
import { useRouter } from "vue-router";

const router = useRouter();

const doctor = ref();
const avatar = ref("src/img/morentouxiang.png");
const deptname = ref("");
const circleUrl = ref("../src/img/yishengtouxiang.png");

const dialogVisible = ref(false);
const AIVisible = ref(false);
const emailVisible = ref(false);
const JKVisible = ref(false);

const CPW = reactive({
  password: '',
  repassword: '',
})

const emailform = reactive({
  address: '',
  title: '',
  content: ''
})

const question = reactive({
  ask: '',
  answer: '',
  list: [],
  key: 'f77d9661b915363ed677729cc0ac871e'
})

const init = () => {
  doctor.value = getSessionStorage("doctor");
  if (doctor.value.deptno == 1) {
    deptname.value = "检验科";
  }
  else if (doctor.value.deptno == 2) {
    deptname.value = "内科";
  }
  else if (doctor.value.deptno == 3) {
    deptname.value = "外科";
  }
}

init();

const changePW = () => {
  dialogVisible.value = true;
}

const AIhelper = () => {
  AIVisible.value = true;
}

const onEmail = () => {
  emailVisible.value = true;
}

const openJK = () => {
  JKVisible.value = true;
}

const rePW = () => {
  if (CPW.password == CPW.repassword) {
    ElMessage({
      message: '修改成功',
      type: 'success',
    }),
      doctor.value = getSessionStorage("doctor");
    doctor.value.password = CPW.password;
    axios.post('http://localhost:8079/doctor/changePassword', doctor.value).then(res => {
      console.log(res.data)
    })
  }
  else {
    ElMessage.error('密码不一致')
  }
}

const back = () => {
  router.push('/')
}

//AI
//用来输入
const text = ref('');

//用来存记录
const wholetext = ref('');

// 用于遍历 来展示
const dialog = reactive([
  "AI: 您好好,我是您的AI见习小助手,请问有什么可以帮到你的吗?",
]);

const submit = () => {
  dialog.push("我: " + text.value);
  wholetext.value += "用户问: " + text.value;
  // axios.post('http://10.25.161.184:8079/ai/chat',`question=${wholetext.value}`).then(res => {
  axios.post('http://localhost:8079/ai/chat', `question=${wholetext.value}`).then(res => {
    console.log(res.data);
    dialog.push("AI: " + res.data.result);
    wholetext.value += "你回答: " + res.data.result;
    text.value = '';
  })
}

const healthskill = () => {
  console.log(question.key);
  console.log(question.ask);
  axios({
    method: 'post',
    url: 'https://apis.tianapi.com/healthskill/index',
    data: Qs.stringify({ key: question.key, word: question.ask }),
    headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
  }).then(res => {
    console.log(res.data);
    question.list = res.data.result.list;
    for (let i = 0; i < question.list.length; i++) {
      question.answer += question.list[i].content + '\n'
    }
  })
}

const clearans = () => {
  question.answer = '';
}

const sendEmail = () => {
  // axios.post('http://10.25.161.184:8079/email/sendEmail',emailform).then(res => {
  axios.post('http://localhost:8079/email/sendEmail', emailform).then(res => {
    if (res.data.code == 200) {
      ElMessage({
        message: '发送成功',
        type: 'success',
      })
    }
  })
}

</script>


<style scoped>
@import url('https://unpkg.com/tailwindcss@^2.0/dist/tailwind.min.css');

.body {
  margin: 0;
  padding: 0;
  display: flex;
  flex-direction: row;
  height: 100vh;
}

.sidebar {
  width: 300px;
  height: 100%;
  background-color: #343a40;
  position: fixed;
  right: 0;
  top: 0;
  overflow-y: auto;
}

.sidebar .sidebar-header {
  padding: 15px;
  text-align: center;
}

.sidebar img {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  margin-bottom: 15px;
}

.sidebar .sidebar-info {
  padding: 0 15px;
  color: #fff;
}

.sidebar .sidebar-info h4 {
  margin-bottom: 5px;
}

.sidebar .sidebar-menu {
  padding-top: 15px;
}

.sidebar .sidebar-menu ul {
  list-style-type: none;
  padding: 0;
}

.sidebar .sidebar-menu li {
  padding: 10px 15px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.1);
}

.sidebar .sidebar-menu li a {
  color: #fff;
  text-decoration: none;
}

.sidebar .sidebar-menu li a:hover {
  color: #6c757d;
}


/* 响应式样式，小屏幕设备隐藏侧边栏 */
@media (max-width: 767.98px) {
  .sidebar {
    display: none;
  }

  .content {
    margin-left: 0;
  }
}

.el-menu {
  padding: 0;
}
</style>