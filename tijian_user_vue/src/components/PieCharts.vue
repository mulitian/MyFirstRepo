<template>  
  <div ref="pieChart" style="width: 600px;height:400px;"></div>  
</template>  
  
<script>  
import * as echarts from 'echarts';  
  
export default {  
  name: 'PieChart',  
  mounted() {  
    this.initPieChart();  
  },  
  methods: {  
    initPieChart() {  
      const myChart = echarts.init(this.$refs.pieChart);  
      const option = {  
        series: [  
          {  
            name: 'Access From',  
            type: 'pie',  
            radius: '55%',  
            data: [  
              {value: 235, name: 'Video Ads'},  
              {value: 274, name: 'Alliance Ads'},  
              {value: 310, name: 'Email Marketing'},  
              {value: 335, name: 'Direct'},  
              {value: 400, name: 'Search Engine'}  
            ],  
            emphasis: {  
              itemStyle: {  
                shadowBlur: 10,  
                shadowOffsetX: 0,  
                shadowColor: 'rgba(0, 0, 0, 0.5)'  
              }  
            }  
          }  
        ]  
      };  
      myChart.setOption(option);  
    }  
  }  
};  
</script>