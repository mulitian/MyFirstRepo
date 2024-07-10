<template>
  <div style="width: 260px; padding: 20px">
    <el-form ref="formRef" :model="selectForm" label-width="auto">
      <el-form-item label="手机号码">
        <el-input v-model="selectForm.userid" placeholder="手机号码"></el-input>
      </el-form-item>
      <el-form-item label="姓名">
        <el-input v-model="selectForm.realname" placeholder="姓名"></el-input>
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
  </div>
</template>

<script setup>
import { reactive, toRefs, ref, inject } from "vue";
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

const selectForm = reactive({
  userid: "",
  realname: "",
  sex: "",
  smid: "",
  orderdate: "",
  state: "2",
});

const selectPage = inject("selectPage");

const ordersPageResponseDto = inject("ordersPageResponseDto");
const setmealArr = ref();

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


const reset = () => {
  selectForm.userid = "";
  selectForm.realname = "";
  selectForm.orderdate = "";
  selectForm.sex = "";
  selectForm.smid = "";
  selectForm.state = "";
  query();
};
</script>