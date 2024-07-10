<template>
  <el-dialog v-model="props.visible" top="75px">
    <div class="flex flex-col h-screen" style="height: 450px">
      <header
        class="bg-[#e0f2fe] dark:bg-gray-800 shadow-sm p-4 flex items-center justify-between"
      >
        <div class="flex items-center space-x-2">
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
            class="h-6 w-6 text-gray-500 dark:text-gray-400"
          >
            <path d="M17 18a2 2 0 0 0-2-2H9a2 2 0 0 0-2 2"></path>
            <rect width="18" height="18" x="3" y="4" rx="2"></rect>
            <circle cx="12" cy="10" r="2"></circle>
            <line x1="8" x2="8" y1="2" y2="4"></line>
            <line x1="16" x2="16" y1="2" y2="4"></line>
          </svg>
          <h1 class="text-xl font-medium text-gray-900 dark:text-gray-50">
            AI问诊
          </h1>
        </div>
      </header>
      <div class="flex-1 overflow-auto p-4">
        <div
          style="display: flex; flex-wrap: wrap; margin-top: 10px"
          class="max-w-md mx-auto space-y-4 flex-wrap"
          v-for="(it, index) in dialog"
          :key="index"
        >
          <div
            class="bg-white dark:bg-gray-800 rounded-lg shadow-sm p-4 mr-4"
            :style="{ maxWidth: 'calc(50% - 8px)' }"
          >
            <p class="text-gray-700 dark:text-gray-300">
              {{ it }}
            </p>
          </div>
        </div>
      </div>
      <div
        class="bg-white dark:bg-gray-800 shadow-t p-4 flex items-center space-x-2"
      >
        <input
          v-model="text"
          class="flex h-10 w-full rounded-md border border-input px-3 py-2 text-sm ring-offset-background file:border-0 file:bg-transparent file:text-sm file:font-medium placeholder:text-muted-foreground focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:cursor-not-allowed disabled:opacity-50 flex-1 bg-gray-100 dark:bg-gray-700 border-none focus:ring-0 focus:outline-none"
          placeholder="输入你的问题"
          type="text"
        />
        <button
          class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 w-10"
        >
          <svg
            xmlns="http://www.w3.org/2000/svg"
            width="24"
            height="24"
            viewBox="0 0 24 24"
            fill="none"
            stroke="currentColor"
            stroke-width="2"
            stroke-linecap="round"
            stroke-linejoin="round"
            class="h-5 w-5 text-gray-500 dark:text-gray-400"
          >
            <circle cx="12" cy="12" r="10"></circle>
            <path d="M8 14s1.5 2 4 2 4-2 4-2"></path>
            <line x1="9" x2="9.01" y1="9" y2="9"></line>
            <line x1="15" x2="15.01" y1="9" y2="9"></line>
          </svg>
        </button>
        <button
          @click="submit"
          class="inline-flex items-center justify-center whitespace-nowrap rounded-md text-sm font-medium ring-offset-background transition-colors focus-visible:outline-none focus-visible:ring-2 focus-visible:ring-ring focus-visible:ring-offset-2 disabled:pointer-events-none disabled:opacity-50 border border-input bg-background hover:bg-accent hover:text-accent-foreground h-10 w-10"
        >
          发送
        </button>
      </div>
    </div>
  </el-dialog>
</template>

<script setup>
import { reactive, toRefs, ref, defineProps } from "vue";
import { getSessionStorage } from "../common.js";
import axios from "axios";
import { ElMessage } from "element-plus";
import Qs from "qs";
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

const props = defineProps({
  visible: {
    type: Boolean,
    required: true,
  },
});

const CPW = reactive({
  password: "",
  repassword: "",
});

const emailform = reactive({
  address: "",
  title: "",
  content: "",
});

const question = reactive({
  ask: "",
  answer: "",
  list: [],
  key: "f77d9661b915363ed677729cc0ac871e",
});

const init = () => {
  doctor.value = getSessionStorage("doctor");
  if (doctor.value.deptno == 1) {
    deptname.value = "检验科";
  } else if (doctor.value.deptno == 2) {
    deptname.value = "内科";
  } else if (doctor.value.deptno == 3) {
    deptname.value = "外科";
  }
};

init();

//AI
//用来输入
const text = ref("");

//用来存记录
const wholetext = ref("");

// 用于遍历 来展示
const dialog = reactive([
  "AI: 您好,我是您的AI问诊助手,请问有什么可以帮到你的吗?",
]);

const submit = () => {
  dialog.push("我: " + text.value);
  wholetext.value += "用户问: " + text.value;
  // axios.post('http://10.25.161.184:8079/ai/chat',`question=${wholetext.value}`).then(res => {
  axios
    .post("http://localhost:8079/ai/chat", `question=${wholetext.value}`)
    .then((res) => {
      console.log(res.data);
      dialog.push("AI: " + res.data.result);
      wholetext.value += "你回答: " + res.data.result;
      text.value = "";
    });
};
</script>


<style scoped>
@import url("https://unpkg.com/tailwindcss@^2.0/dist/tailwind.min.css");
/* 响应式样式，小屏幕设备隐藏侧边栏 */
@media (max-width: 767.98px) {
  .sidebar {
    display: none;
  }

  .content {
    margin-left: 0;
  }
}

el-dialog input {
  height: 500px;
}
</style>
