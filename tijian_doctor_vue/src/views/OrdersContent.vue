<template>
  <el-container style="height: 100%">
    <el-header>
      <TopBar></TopBar>
    </el-header>
    <el-container>
      <el-aside width="260px">
        <el-descriptions class="margin-top" title="预约客户信息" :column="1" border>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">预约编号</div>
            </template>
            {{ order.orderid }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">手机号码</div>
            </template>
            {{ order.userid }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">姓名</div>
            </template>
            {{ order.realname }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">性别</div>
            </template>
            {{ order.sex == 1 ? "男" : "女" }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">套餐类型</div>
            </template>
            {{ order.sname }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template #label>
              <div class="cell-item">体检日期</div>
            </template>
            {{ order.orderdate }}
          </el-descriptions-item>
        </el-descriptions>
        <el-button type="primary" style="margin-top: 20px" @click="toOrdersList">查询体检用户</el-button>
      </el-aside>
      <el-main>
        <div class="main-box">
          <el-collapse>
            <el-collapse-item :title="ci.ciName" v-for="(ci, ciIndex) in ciReportArr" :key="ci.ciid">
              <el-row style="color: #888">
                <el-col :span="12" style="box-sizing: border-box; padding: 6px 10px"
                  v-for="(cidr, cidrIndex) in ci.cidetailedreport" :key="cidr.ciid">
                  <span style="
                      background-color: #ba634e;
                      color: #fff;
                      box-sizing: border-box;
                      padding: 1px 3px;
                      border-radius: 3px;
                      margin-right: 5px;
                    " v-if="cidr.iserror == 1">异</span>

                  <span style="margin-right: 5px; vertical-align: top">{{
                    cidr.name
                  }}</span>

                  <el-input style="width: 26%; margin-right: 2px" size="small" :placeholder="cidr.name"
                    :disabled="isEdit" v-if="cidr.type != 4"
                    v-model="ciReportArr[ciIndex].cidetailedreport[cidrIndex].value"
                    @blur="cidrCheckByBlur(ciIndex, cidrIndex)" />
                  <el-input style="width: 80%" type="textarea" :disabled="isEdit" :rows="2" :placeholder="cidr.name"
                    v-model="ciReportArr[ciIndex].cidetailedreport[cidrIndex].value" v-if="cidr.type == 4" />

                  <span style="margin-right: 15px">{{ cidr.unit }}</span>

                  <span v-if="cidr.normalvaluestring">正常值范围: {{ cidr.normalvaluestring }}</span>
                </el-col>
              </el-row>
              <el-button type="primary" style="margin-top: 8px" @click="updateCiDetailedReport(ciIndex)"
                v-if="order.state != 3">{{ ci.ciName }}项保存</el-button>
            </el-collapse-item>
          </el-collapse>

          <el-card class="box-card" style="margin-top: 20px">
            <template #header>
              <div class="card-header">
                <span>总检结论</span>
                <el-button class="button" type="danger" @click="updateOrdersState"
                  v-if="order.state != 3">体检套餐总检结果报告归档</el-button>
              </div>
            </template>
            <div>
              <el-table :data="overallResultArr.value" :key="overallResultArr.value.orid" style="width: 100%">
                <el-table-column prop="code" label="编号" width="60" />
                <el-table-column prop="title" label="总检结论项标题" width="180" />
                <el-table-column prop="content" label="总检结论项内容" />
                <el-table-column label="操作" width="120">
                  <template #default="scope">
                    <div style="display: flex;">
                      <el-button type="text" size="small" @click="toUpdateOverallResult(scope.row)"
                        v-if="order.state != 3">更新</el-button>
                      <el-button type="text" size="small" @click="removeOverallResult(scope.row)"
                        v-if="order.state != 3">删除</el-button>
                    </div>
                  </template>
                </el-table-column>
              </el-table>

              <el-form ref="formRef" :model="overallResultForm" style="margin-top: 20px" label-width="120px"
                v-if="order.state != 3">
                <el-form-item label="总检结论标题">
                  <el-input v-model="overallResultForm.title" placeholder="总检结论标题"></el-input>
                </el-form-item>
                <el-form-item label="总检结论内容">
                  <el-input v-model="overallResultForm.content" :rows="2" type="textarea"
                    placeholder="总检结论内容"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="addOverallResult">添加</el-button>
                  <el-button type="warning" @click="resetOverallResult">清空</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-card>

          <!-- 总检结论更新用对话框 -->
          <el-dialog v-model="dialogVisible" title="总检结论项更新" width="60%">
            <span>
              <el-form :model="updateOverallResultForm" label-width="120px">
                <el-form-item label="总检结论标题">
                  <el-input v-model="updateOverallResultForm.title"></el-input>
                </el-form-item>
                <el-form-item label="总检结论内容">
                  <el-input v-model="updateOverallResultForm.content" type="textarea" :rows="3"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="updateOverallResult">更新</el-button>
                  <el-button @click="dialogVisible = false">取消</el-button>
                </el-form-item>
              </el-form>
            </span>
          </el-dialog>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>

import { reactive, toRefs, ref } from "vue";
import { useRouter, useRoute } from "vue-router";
import { getSessionStorage } from "../common.js";
import { inject } from 'vue';
import axios from "axios";

import TopBar from "../components/TopBar.vue"


const router = useRouter();
const route = useRoute();
const doctor = ref();
const order = ref();
let isEdit = ref(false);

const overallResultArr = reactive({
  value: [],
});

const overallResultForm = reactive({
  title: "",
  content: "",
});

const updateOverallResultForm = reactive({
  orid: "",
  title: "",
  content: ""
});

const orders = reactive({
  users: {},
  setmeal: {},
});

const ciReportArr = ref();

const dialogVisible = ref(false);

const getReporter = () => {
  axios.get("http://localhost:8079/cireport/getCiDetail", {
    // axios.get("http://10.25.161.184:8079/cireport/getCiDetail",{
    params: {
      orderid: order.value.orderid,
    }
  }).then((response) => {
    console.log(response.data);
    ciReportArr.value = response.data.result;
  })
    .catch((error) => {
      console.error(error);
    });
}

const number = () => {
  console.log(overallResultArr.value);
  if (overallResultArr.value != null) {
    for (let i = 0; i < overallResultArr.value.length; i++) {
      overallResultArr.value[i].code = i + 1;
      console.log(overallResultArr.value[i].code);
    }
  }
}

const reload = inject("reload");
const updateFun = () => {
  reload();
};

const getOverResult = () => {
  // axios.get("http://10.25.161.184:8079/overallresult/getOverallResult",{
  axios.get("http://localhost:8079/overallresult/getOverallResult", {
    params: {
      orderid: order.value.orderid,
    }
  }).then((response) => {
    console.log(response.data);
    overallResultArr.value = response.data.result;
    number();
  })
    .catch((error) => {
      console.error(error);
    });
  //updateFun();
}

const init = () => {
  doctor.value = getSessionStorage("doctor");
  console.log(route.query);
  order.value = route.query;
  if (order.value.state == 3) {
    isEdit.value = true;
  }
  //alert(order.value.state)
  getReporter();
  getOverResult();
}

init()

const toOrdersList = () => {
  router.push("/ordersList")
}

const evaluateError = (ciIndex) => {
  let cidr = ciReportArr.value[ciIndex];
  console.log(cidr);
  console.log(cidr.cidetailedreport);
  console.log(cidr.cidetailedreport.length);
  for (let i = 0; i < cidr.cidetailedreport.length; i++) {
    let item = cidr.cidetailedreport[i];
    console.log(cidr.cidetailedreport[i].type);
    if (item.type == 1) {
      if (item.value < item.minrange || item.value > item.maxrange) {
        ciReportArr.value[ciIndex].cidetailedreport[i].iserror = 1;
      }
      else {
        ciReportArr.value[ciIndex].cidetailedreport[i].iserror = 0;
      }
    }
    else if (item.type == 2) {
      if (item.value != item.normalvaluestring) {
        ciReportArr.value[ciIndex].cidetailedreport[i].iserror = 1;
      }
      else {
        ciReportArr.value[ciIndex].cidetailedreport[i].iserror = 0;
      }
    }
  }
}

const updateCiDetailedReport = (ciIndex) => {
  evaluateError(ciIndex);
  // axios.post("http://10.25.161.184:8079/cireport/saveCiDetail",ciReportArr.value[ciIndex].cidetailedreport).then((response) => {
  axios.post("http://localhost:8079/cireport/saveCiDetail", ciReportArr.value[ciIndex].cidetailedreport).then((response) => {
    console.log(response.data);
    getReporter();
  })
    .catch((error) => {
      console.error(error);
    });
}

const resetOverallResult = () => {
  overallResultForm.title = '';
  overallResultForm.content = '';
}

const addOverallResult = () => {
  // axios.post("http://10.25.161.184:8079/overallresult/addOverallResult",{
  axios.post("http://localhost:8079/overallresult/addOverallResult", {
    title: overallResultForm.title,
    content: overallResultForm.content,
    orderid: order.value.orderid,
  }).then((response) => {
    console.log(response.data);
    getOverResult();
  })
    .catch((error) => {
      console.error(error);
    });
}

const removeOverallResult = (row) => {
  // axios.delete("http://10.25.161.184:8079/overallresult/deleteOverallResult",{
  axios.delete("http://localhost:8079/overallresult/deleteOverallResult", {
    params: {
      orid: row.orid,
    }
  }).then((response) => {
    console.log(response.data);
    getOverResult();
  })
    .catch((error) => {
      console.error(error);
    });
}

const toUpdateOverallResult = (row) => {
  console.log(row);
  dialogVisible.value = true;
  //这里不能直接赋值为row，必须使用深拷贝。否则更新数据与原数据是绑定的
  updateOverallResultForm.orid = row.orid;
  updateOverallResultForm.title = row.title;
  updateOverallResultForm.content = row.content;
}

const updateOverallResult = () => {
  // axios.post("http://10.25.161.184:8079/overallresult/saveOverallResult",updateOverallResultForm).then((response) => {
  axios.post("http://localhost:8079/overallresult/saveOverallResult", updateOverallResultForm).then((response) => {
    console.log(response.data);
    getOverResult();
    dialogVisible.value = false;
  })
    .catch((error) => {
      console.error(error);
    });
}

const updateOrdersState = () => {
  console.log(order.value.orderid)
  // axios.get("http://10.25.161.184:8079/order/updateOrderState",{
  axios.get("http://localhost:8079/order/updateOrderState", {
    params: {
      orderid: order.value.orderid
    }
  }).then((response) => {
    console.log(response.data);
    order.value.state = 3;
    isEdit = true;
  })
    .catch((error) => {
      console.error(error);
    });
}


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

.el-main {
  background-color: #e9eef3;
  padding: 0;
}

.el-main .main-box {
  width: 100%;
  height: 680px;
  overflow: auto;
  background-color: #fff;
  box-sizing: border-box;
  padding: 20px;
}

/*********** 描述列表 ***********/

.el-descriptions {
  margin-top: 20px;
}

.cell-item {
  display: flex;
  align-items: center;
}

.margin-top {
  margin-top: 20px;
}

/*********** 总检结论 ***********/
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.box-card {
  width: 100%;
}
</style>