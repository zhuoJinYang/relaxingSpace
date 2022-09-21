<template>
  <a-layout>
    <a-layout-header class="header">
      <div>
        欢迎来到爷的世界（请选择你想玩的模块吧，训练家）
      </div>
    </a-layout-header>
    <a-layout-content class="content">
      <div style="width: 25%;height: 150px;">
        <div class="module-item">
          博客
        </div>
      </div>
      <div style="width: 25%;height: 150px;">
        <div class="module-item">
          宝可梦图鉴
        </div>
      </div>

    </a-layout-content>
    <a-layout-footer>下面放一些说明</a-layout-footer>
  </a-layout>
</template>

<style>
.header{
  background-color: #fff;
  padding: 0 24px;
  font-size: 22px;
}
.content{
  display: flex;
}
.module-item{
  width: calc(100% - 40px);
  height: calc(100% - 40px);
  margin: 20px 20px;
  border-radius: 8px;
  background-color: #0be8f7;
  font-size: 18px;
  text-align: center;
  padding-top: 30px;
}
</style>

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