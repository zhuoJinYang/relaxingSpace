<template>
  <a-config-provider :locale="locale">
    <router-view/>
  </a-config-provider>
</template>

<script setup lang="ts">
import zhCN from 'ant-design-vue/es/locale/zh_CN';
import 'moment/dist/locale/zh-cn'
const locale = zhCN
</script>


<style scoped>
</style>
