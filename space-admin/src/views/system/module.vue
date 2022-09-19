<template>
  <a-layout>
    <a-layout-header>
      <div>
        欢迎来到爷的世界
      </div>
    </a-layout-header>
    <a-layout-content>
      <div style="width: 25vh;height: 25vh;">
        <module-item></module-item>
      </div>
    </a-layout-content>
    <a-layout-footer>下面放一些说明</a-layout-footer>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent} from 'vue'
import ModuleItem from "@/components/ModuleItem.vue";

export default defineComponent({
  components:{
    ModuleItem
  },
  setup() {
    return {}
  }
})
</script>

<style scoped lang="scss"></style>