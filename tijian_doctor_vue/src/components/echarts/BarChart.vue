<template>
  <div class="wrapper">
    <div class="bar-chart" ref="barChart"></div>
    <div class="pie-chart" ref="pieChart"></div>
  </div>
</template>  
  
<script>
import axios from "axios";
import * as echarts from "echarts";

export default {
  data() {
    return {
      barChart: null,
      pieChart: null,
    };
  },
  mounted() {
    this.fetchData();
    this.addResizeListener();
  },
  beforeDestroy() {
    // 组件销毁前移除监听器
    window.removeEventListener("resize", this.handleResize);
  },
  methods: {
    fetchData() {
      axios
        .get("http://localhost:8080/echarts/getHpsData")
        .then((response) => {
          if (response.data.code === 200) {
            this.drawBarChart(response.data.result);
            this.drawPieChart(response.data.result);
          }
        })
        .catch((error) => {
          console.error("Error fetching data:", error);
        });
    },
    drawBarChart(data) {
      const barChart = echarts.init(this.$refs.barChart);
      const option = {
        xAxis: {
          type: "category",
          data: Object.keys(data),
        },
        yAxis: {
          type: "value",
        },
        series: [
          {
            data: Object.values(data),
            type: "bar",
          },
        ],
      };
      barChart.setOption(option);
      this.barChart = echarts.init(this.$refs.barChart);
    },
    drawPieChart(data) {
      const pieChart = echarts.init(this.$refs.pieChart);
      const option = {
        series: [
          {
            name: "访问来源",
            type: "pie",
            radius: "55%",
            data: Object.entries(data).map(([name, value]) => ({
              name,
              value,
            })),
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: "rgba(0, 0, 0, 0.5)",
              },
            },
          },
        ],
      };
      pieChart.setOption(option);
      this.pieChart = echarts.init(this.$refs.pieChart);
    },
    handleResize() {
      // 当窗口大小变化时，调用每个图表的resize方法
      if (this.barChart) {
        this.barChart.resize();
      }
      if (this.pieChart) {
        this.pieChart.resize();
      }
    },
    addResizeListener() {
      // 添加resize事件监听器
      window.addEventListener("resize", this.handleResize);
    },
  },
};
</script>  
  
<style scoped>
.wrapper {
  display: flex;
}

.bar-chart {
  width: 40vw;
  height: 400px;
}

.pie-chart {
  width: 40vw;
  height: 400px;
  /* margin-top: 20px; */
}
</style>