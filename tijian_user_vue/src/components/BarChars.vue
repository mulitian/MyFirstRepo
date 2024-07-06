<template>  
  <div ref="barChart" style="width: 600px;height:400px;"></div>  
</template>  
  
<script>  
import * as echarts from 'echarts';  
  
export default {  
  mounted() {  
    this.initBarChart();  
  },  
  methods: {  
    initBarChart() {  
      const myChart = echarts.init(this.$refs.barChart);  
      const option = {  
        xAxis: {  
          type: 'category',  
          data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']  
        },  
        yAxis: {  
          type: 'value'  
        },  
        series: [{  
          data: [120, 200, 150, 80, 70, 110, 130],  
          type: 'bar'  
        }]  
      };  
      myChart.setOption(option);  
    }  
  }  
};  
</script>