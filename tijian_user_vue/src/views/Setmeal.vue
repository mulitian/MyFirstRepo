<template>
  <!-- 总容器 -->
  <div class="wrapper">
    <header>
      <!-- <i class="fa fa-angle-left" onclick="history.go(-1)"></i> -->
      <LastPage></LastPage>
      <p>选择体检套餐</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <ul class="setmeal">
      <!-- 套餐例图 -->
      <li @click="toggleChart" style="padding: 15px">
        <el-icon style="position: relative; transform: translateY(3px)">
          <PieChart />
        </el-icon>
        <div style="display: inline-block; margin-left: 10px; color: #555">
          查看热门套餐
        </div>
        <el-icon
          :class="{ 'transform-up': isChartVisible }"
          style="margin-left: 175px"
        >
          <ArrowRight v-if="!isChartVisible" />
          <ArrowDown v-else />
        </el-icon>

        <MealCharts v-show="isChartVisible" :sex="sex" :hp-id="hpId"></MealCharts>
      </li>
      <li v-for="setmeal in setmeals" :key="setmeal.smid">
        <div class="item">
          <!-- <i class="fa fa-shopping-cart"/> -->
          <div class="item-left" @click="toSelectdate(setmeal)">
            <h3>{{ setmeal.type === 1 ? "男士套餐" : "女士套餐" }}</h3>
            <p>{{ setmeal.name }}</p>
          </div>
          <div class="item-right" @click="getCheckitems(setmeal, setmeal.smid)">
            <p>详情</p>
            <i class="fa fa-angle-down"></i>
          </div>
        </div>

        <div class="item-content" v-if="setmeal.showCheckitems">
          <table>
            <tr>
              <th>检查项目</th>
              <th>检查内容</th>
              <th>检查意义</th>
            </tr>
            <tr v-for="(item, index) in setmeal.checkitems" :key="index">
              <td>{{ item.ciname }}</td>
              <td>{{ item.cicontent }}</td>
              <td>{{ item.meaning }}</td>
            </tr>
          </table>
        </div>
      </li>
    </ul>

    <div class="bottom-ban"></div>

    <Footer></Footer>
  </div>
</template>

<script setup>
import Footer from "../components/Footer.vue";
import LastPage from "@/components/LastPage.vue";
import MealCharts from "@/components/MealCharts.vue";
import { ref, reactive, toRefs, onMounted, onBeforeMount } from "vue";
import { useRoute, useRouter } from "vue-router";
import { setSessionStorage, getSessionStorage } from "../common.js";
import axios from "axios";

const router = useRouter();

const route = useRoute();
const hpId = route.query.hpId;

const setmeals = ref([]); // 使用 ref 创建一个响应式引用
const checkitems = ref([]);
const user = ref();
const sex = ref();

const isChartVisible = ref(false);
function toggleChart() {
  isChartVisible.value = !isChartVisible.value;
}

function init() {
  const token = getSessionStorage("token");
  console.log("-----------套餐列表---------");
  // console.log(token);
  user.value = getSessionStorage("user");
  sex.value = user.value.sex;
  console.log(user.value);
  console.log(sex.value);
  // axios.get(`http://10.25.161.174:8080/getSetmeals?type=${user.sex}`).then(res => {
  axios
    .get(`http://localhost:8080/getSetmeals?type=${user.value.sex}`, {
      headers: {
        Authorization: `${token}`,
      },
    })
    .then((res) => {
      setmeals.value = res.data.result.map((setmeal) => ({
        ...setmeal, // 保留原始属性
        showCheckitems: false, // 默认不显示检查项目
        checkitems: [], // 初始化检查项目数组
      }));
      // console.log(setmeals.value)
    });
}

function getCheckitems(setmeal, smId) {
  console.log("-----------套餐详情---------");
  const token = getSessionStorage("token");
  setmeal.showCheckitems = !setmeal.showCheckitems;
  // axios.get(`http://10.25.161.174:8080/getCheckitems?smId=${smId}`).then(res => {
  axios
    .get(`http://localhost:8080/getCheckitems?smId=${smId}`, {
      headers: {
        Authorization: `${token}`,
      },
    })
    .then((res) => {
      // console.log(res.data)
      setmeal.checkitems = res.data.result;
      console.log(setmeal.checkitems);
      // setmeal.showCheckitems = true;
    });
}
// function toSelectdate() {
//     router.push('/selectdate');
// }
function toSelectdate(setmeal) {
  // console.log(hpId);
  // console.log(setmeal.smid);
  router.push({
    path: "/selectdate",
    query: { smId: setmeal.smid, hpId: hpId },
  });
}

onBeforeMount(init);
</script>


<style scoped>
/*********************** 总容器 ***********************/
.wrapper {
  width: 100%;
  height: 100%;
  background-color: #f9f9f9;
}

/*********************** header ***********************/
header {
  z-index: 9999;

  width: 100%;
  height: 15.7vw;
  background-color: #fff;

  position: fixed;
  left: 0;
  top: 0;

  display: flex;
  align-items: center;
  justify-content: space-between;

  box-sizing: border-box;
  padding: 0 3.6vw;
}

header .fa {
  font-size: 8vw;
}

/*********************** footer ***********************/
footer {
  width: 100%;
  height: 14.2vw;
  box-sizing: border-box;
  border-top: solid 1px #e9e9e9;
  background-color: #fff;

  position: fixed;
  left: 0;
  bottom: 0;
}

footer ul {
  width: 100%;
  height: 14.2vw;
  display: flex;
  align-items: center;
  justify-content: space-around;
}

footer ul li {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;

  font-size: 3vw;
  color: #999;

  user-select: none;
  cursor: pointer;
}

footer ul li .fa {
  font-size: 5vw;
}

/*********************** common样式 ***********************/
.top-ban {
  width: 100%;
  height: 15.7vw;
}

.bottom-ban {
  width: 100%;
  height: 14.2vw;
}

/*********************** setmeal ***********************/
.setmeal {
  width: 100%;
  margin-top: 10vw;
}

.setmeal li {
  width: 92.8vw;
  margin: 0 auto;
  border: solid 1px #eee;
  border-radius: 1vw;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.08);
  background-color: #fff;
  margin-bottom: 3.6vw;
}

.setmeal li .item {
  width: 100%;
  height: 20vw;
  color: #555;

  display: flex;
  align-items: center;

  padding-left: 3px;
}

.setmeal li .item .item-left {
  flex: 0 0 72vw;
  height: 20vw;
  border-right: solid 1px #eee;
  box-sizing: border-box;
  padding-left: 6vw;

  display: flex;
  flex-direction: column;
  justify-content: center;

  user-select: none;
  cursor: pointer;
}

.setmeal li .item .item-left h3 {
  font-size: 4.3vw;
  font-weight: 600;
}

.setmeal li .item .item-left p {
  font-size: 4vw;
  margin-top: 1vw;
}

.setmeal li .item .item-right {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;

  user-select: none;
  cursor: pointer;
}

.setmeal li .item .item-right p {
  font-size: 4vw;
  margin-right: 2vw;
}

.setmeal li .item-content {
  /*display: none;*/
  width: 100%;
  background-color: #fff;
}

.setmeal li .item-content table {
  width: 100%;
  border-collapse: collapse;
  font-size: 4vw;
  color: #555;
}

.setmeal li .item-content table tr {
  display: flex;
}

.setmeal li .item-content table tr td,
.setmeal li .item-content table tr th {
  flex: 1;
}

.setmeal li .item-content table tr th {
  text-align: center;
  background-color: #eee;
  height: 10vw;
  line-height: 10vw;
}

.setmeal li .item-content table tr td {
  border: solid 1px #efefef;
  box-sizing: border-box;
  padding: 2vw;
}
</style>
