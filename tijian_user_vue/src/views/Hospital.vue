<template>
  <!-- 总容器 -->
  <div class="wrapper">
    <header>
      <!-- <i class="fa fa-angle-left" onclick="history.go(-1)"></i> -->
      <LastPage />
      <p>请您选择体检机构</p>
      <div></div>
    </header>
    <div class="top-ban"></div>

    <ul class="hospital">
      <!-- 医院例图 -->
      <li @click="toggleChart">
        <el-icon style="position: relative; transform: translateY(3px)">
          <PieChart />
        </el-icon>
        <div style="display: inline-block; margin-left: 10px; color: #555">
          查看热门医院
        </div>
        <el-icon
          :class="{ 'transform-up': isChartVisible }"
          style="margin-left: 175px"
        >
          <!-- 使用v-if和v-else-if根据条件渲染不同的图标 -->
          <ArrowRight v-if="!isChartVisible" />
          <ArrowDown v-else />
        </el-icon>

        <!-- 使用v-show根据isChartVisible的值来控制Hpcharts的显示 -->
        <Hpcharts v-show="isChartVisible"></Hpcharts>
      </li>
      <!-- 医院列表 -->
      <li v-for="hospital in hospitals" :key="hospital.hpId">
        <h3 @click="toSetmeal(hospital)">
          {{ hospital.name }}
          <!-- <i class="fa fa-angle-right"></i> -->
        </h3>
        <div class="hospita-info">
          <img :src="hospital.picture" />
          <table>
            <tr>
              <td>营业时间</td>
              <td>{{ hospital.businesshours }}</td>
            </tr>
            <tr>
              <td>采血截止</td>
              <td>{{ hospital.deadline }}</td>
            </tr>
            <tr>
              <td>电话</td>
              <td>{{ hospital.telephone }}</td>
            </tr>
            <tr>
              <td>地址</td>
              <td>{{ hospital.address }}</td>
            </tr>
          </table>
        </div>
        <div class="about">
          <p>
            <i class="fa fa-phone"></i>
            联系我们
          </p>
          <p>
            <i class="fa fa-map-marker"></i>
            查找位置
          </p>
        </div>
      </li>
    </ul>

    <div class="bottom-ban"></div>
    <Footer></Footer>
  </div>
</template>


<script setup>
import Footer from "../components/Footer.vue";
import LastPage from "../components/LastPage.vue";
import Hpcharts from "@/components/Hpcharts.vue";
import { ref, reactive, toRefs, onMounted } from "vue";
import { useRoute, useRouter } from "vue-router";
import { setSessionStorage, getSessionStorage } from "../common.js";
import axios from "axios";

const router = useRouter();
const route = useRoute();

const hospitals = ref([]); // 使用 ref 创建一个响应式引用
const isChartVisible = ref(false);

const init = () => {
  console.log("-----------医院展示---------");

  const token = getSessionStorage("token");
  // console.log(token);
  // axios.get('http://10.25.161.174:8080/getHospitals').then(res => {
  axios
    .get("http://localhost:8080/getHospitals", {
      headers: {
        Authorization: `${token}`,
      },
    })
    .then((res) => {
      hospitals.value = res.data.result;
      console.log(hospitals.value);
    });
};

function toggleChart() {
  isChartVisible.value = !isChartVisible.value;
}

function toSetmeal(hospital) {
  // console.log(hospital);
  // console.log(hospital.hpid);
  router.push({ path: "/setmeal", query: { hpId: hospital.hpid } });
}

// function toSetmeal() {
//     router.push('/setmeal');
// }

onMounted(init);
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
  font-size: 7vw;
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

/*********************** hospital ***********************/
.hospital {
  width: 100%;
  margin-top: 3.6vw;
}

.hospital li {
  width: 92.8vw;
  margin: 0 auto;
  border: solid 1px #eee;
  border-radius: 1vw;
  box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.08);
  background-color: #fff;
  margin-bottom: 3.6vw;

  box-sizing: border-box;
  padding: 4vw;
}

.hospital li h3 {
  box-sizing: border-box;
  padding-left: 2.4vw;
  border-left: solid 3px #157999;
  font-size: 4.3vw;
  display: flex;
  justify-content: space-between;

  user-select: none;
  cursor: pointer;
}

.hospital li h3 i {
  font-size: 5vw;
}

.hospital li .hospita-info {
  width: 100%;
  margin-top: 3vw;
  display: flex;
  justify-content: space-between;
}

.hospital li .hospita-info img {
  width: 22vw;
  height: 22vw;
}

.hospital li .hospita-info table {
  width: 59vw;
  font-size: 3.5vw;
  color: #666;
}

.hospital li .hospita-info table tr {
  height: 6vw;
}

.hospital li .hospita-info table tr td {
  vertical-align: top;
}

.hospital li .hospita-info table tr td:first-child {
  width: 15vw;
}

.hospital li .about {
  display: flex;
  justify-content: flex-end;
  margin-top: 2vw;
}

.hospital li .about p {
  width: 30vw;
  height: 8vw;
  border: solid 1px #157999;
  border-radius: 2vw;

  text-align: center;
  line-height: 8vw;
  margin-left: 2vw;

  font-size: 4vw;
  color: #157999;

  user-select: none;
  cursor: pointer;
}

.hospital li .about p i {
  color: #555;
  margin-right: 1vw;
}
</style>