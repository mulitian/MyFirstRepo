<template>
  <el-container style="height: 750px">
    <el-header>
      <TopBar></TopBar>
    </el-header>
    <el-drawer v-model="drawer" title="I am the title" :with-header="false">
      <span>Hi there!</span>
    </el-drawer>
    <el-container>
      <!-- <el-aside width="260px">
        <h4>体检用户查询</h4>
        <el-form ref="formRef" :model="selectForm" label-width="auto">
          <el-form-item label="手机号码">
            <el-input
              v-model="selectForm.userid"
              placeholder="手机号码"
            ></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input
              v-model="selectForm.realname"
              placeholder="姓名"
            ></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="selectForm.sex">
              <el-radio label="1">男</el-radio>
              <el-radio label="0">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="套餐类型">
            <el-select v-model="selectForm.smid" placeholder="套餐类型">
              <el-option
                v-for="setmeal in setmealArr"
                :key="setmeal.smid"
                :label="setmeal.name"
                :value="setmeal.smid"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="体检日期">
            <el-date-picker
              v-model="selectForm.orderdate"
              type="date"
              placeholder="体检日期"
              style="width: 100%"
              format="YYYY/MM/DD"
              value-format="YYYY-MM-DD"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="是否归档">
            <el-radio-group v-model="selectForm.state">
              <el-radio border label="1">未归档</el-radio>
              <el-radio border label="3">已归档</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="query">查询</el-button>
            <el-button type="warning" @click="reset">重置</el-button>
          </el-form-item>
        </el-form>
      </el-aside> -->
      <el-aside width="260px" style="padding: 0px;">
        <SideBar :selectForm="selectForm" @update-orders="handleUpdateOrders" ></SideBar>
      </el-aside>
      <el-main>
        <el-table :data="ordersPageResponseDto.list" style="width: 100%">
          <el-table-column prop="orderid" label="预约编号" width="120" />
          <el-table-column prop="userid" label="手机号码" width="140" />
          <el-table-column prop="realname" label="真实姓名" width="120" />
          <el-table-column label="性别" width="60">
            <template #default="scope">
              <span>{{ scope.row.sex == 1 ? "男" : "女" }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="sname" label="套餐类型" />
          <el-table-column prop="hpname" label="体检医院" width="220" />
          <el-table-column prop="orderdate" label="体检日期" />
          <el-table-column label="操作" width="120">
            <template #default="scope">
              <el-button
                type="text"
                size="small"
                @click="ciReport(scope.row)"
                >{{
                  scope.row.state != 3 ? "编辑体检报告" : "查看体检报告"
                }}</el-button
              >
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          background
          layout="prev, pager, next, total"
          :total="selectPage.total"
          :page-size="selectPage.maxPageSize"
          style="margin-top: 20px"
          @current-change="currentChange"
        >
        </el-pagination>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { reactive, toRefs, ref, provide } from "vue";
import { useRouter } from "vue-router";
import { getSessionStorage } from "../common.js";
import axios from "axios";

import TopBar from "../components/TopBar.vue";
import SideBar from "@/components/SideBar.vue";

// axios.defaults.baseURL = "http://10.25.161.184:8079/order/";
axios.defaults.baseURL = "http://localhost:8079/order/";
const router = useRouter();

const doctor = ref();
const avatar = ref("src/img/morentouxiang.png");
const drawer = ref(false);

const setmealArr = ref();

const selectForm = reactive({
  userid: "",
  realname: "",
  sex: "",
  smid: "",
  orderdate: "",
  state: "1",
});

const selectPage = reactive({
  pageNum: 1,
  maxPageSize: 10,
  total: 20,
});

const ordersPageResponseDto = reactive({
  list: [],
});

// 提供给子组件需要的数据和方法  
provide('ordersPageResponseDto', ordersPageResponseDto);  
provide('selectPage', selectPage);  

function handleUpdateOrders(newOrders, total) {  
  ordersPageResponseDto.value.list = newOrders;  
  selectPage.value.total = total;  
}  


const formatDate = (dateStr) => {
  let date = new Date(dateStr);
  let year = date.getFullYear();
  let month = String(date.getMonth() + 1).padStart(2, "0"); // 月份是从0开始的，所以要+1
  let day = String(date.getDate()).padStart(2, "0");
  return `${year}-${month}-${day}`;
};

const times = () => {
  let list = ordersPageResponseDto.list;
  for (let i = 0; i < list.length; i++) {
    ordersPageResponseDto.list[i].orderdate = formatDate(list[i].orderdate);
  }
};

const getOrderList = () => {
  axios
    .get("getAllOrders", {
      params: {
        pageNum: selectPage.pageNum,
        pageSize: selectPage.maxPageSize,
      },
    })
    .then((response) => {
      console.log(response.data);
      console.log(response.data.result);
      ordersPageResponseDto.list = response.data.result.list;
      selectPage.total = response.data.result.total;
      times();
    })
    .catch((error) => {
      console.error(error);
    });
};

const init = () => {
  doctor.value = getSessionStorage("doctor");
  console.log(doctor.value);
  axios
    .get("querySmType")
    .then((response) => {
      console.log(response.data);
      setmealArr.value = response.data.result;
    })
    .catch((error) => {
      console.error(error);
    });
  getOrderList();
};

const currentChange = (pageNum) => {
  selectPage.pageNum = pageNum;
  query();
};

init();

const ciReport = (row) => {
  console.log(row);
  router.push({
    path: "/ordersContent",
    query: {
      orderid: row.orderid,
      userid: row.userid,
      realname: row.realname,
      sex: row.sex,
      sname: row.sname,
      state: row.state,
      orderdate: row.orderdate,
    },
  });
};

const reset = () => {
  selectForm.realname = "";
  selectForm.orderdate = "";
  selectForm.sex = "";
  selectForm.smid = "";
  selectForm.state = "";
};

const query = () => {
  console.log(selectForm);
  axios
    .post("queryOrderList", {
      userid: selectForm.userid,
      realname: selectForm.realname,
      sex: selectForm.sex,
      smid: selectForm.smid,
      orderdate: selectForm.orderdate,
      state: selectForm.state,
      pageNum: selectPage.pageNum,
      pageSize: selectPage.maxPageSize,
    })
    .then((response) => {
      console.log(response.data);
      console.log(response.data.result);
      ordersPageResponseDto.list = response.data.result.list;
      selectPage.total = response.data.result.total;
      times();
    })
    .catch((error) => {
      console.error(error);
    });
};
</script>

<style scoped>
.el-header {
  background-color: #b3c0d1;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 60px;

  display: flex;
  align-items: center;
  justify-content: space-between;
  color: #1c51a3;
}

.el-header h1 {
  font-size: 22px;
  font-weight: 700;
}
.el-header p {
  font-size: 16px;
}

.el-aside {
  background-color: #d3dce6;
  box-sizing: border-box;
  padding: 20px;
}
.el-aside h4 {
  color: #555;
  margin-bottom: 20px;
}

.el-main {
  background-color: #e9eef3;
}

.user-avatar {
  margin-left: 10px;
  display: flex;
  width: 30px;
  height: 30px;
}
</style>