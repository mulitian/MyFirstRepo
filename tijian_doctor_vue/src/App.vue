<script setup>
import { RouterLink, RouterView } from 'vue-router'

// 在App.vue中
import { provide,nextTick, ref } from 'vue'
const isRouterActive = ref(true)
provide('reload', () => {
  isRouterActive.value = false
  nextTick(() => {
    isRouterActive.value = true
  })
})

</script>

<template>

<router-view v-if="isRouterActive"></router-view>
</template>

<style scoped>

</style>
