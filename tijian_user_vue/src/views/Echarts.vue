<template>  
  <div>  
    <BarChart />  
    <PieChart />  
  </div>  
</template>  
  
<script>  
import BarChart from '@/components/BarChars.vue';  
import PieChart from '@/components/PieCharts.vue';  
  
export default {  
  components: {  
    BarChart,  
    PieChart  
  }  
};  
</script>