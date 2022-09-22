<template>
  <component :is="type"></component>
</template>

<script lang="ts">
import {defineComponent} from 'vue'
import * as antIcons from '@ant-design/icons-vue'

export default defineComponent({
  name:'AntIcon',
  components: antIcons,
  props:['type']
})
</script>

<style scoped lang="scss"></style>