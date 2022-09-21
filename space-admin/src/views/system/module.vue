<template>
  <a-layout>
    <a-layout-header class="header">
      <div>
        欢迎来到爷的世界（请选择你想玩的模块吧，训练家）
      </div>
    </a-layout-header>
    <a-layout-content class="content">
      <div style="width: 25%;height: 150px;" @click="gotoBlog">
        <div class="module-item">
          博客
        </div>
      </div>
      <div style="width: 25%;height: 150px;" @click="gotoPokemon">
        <div class="module-item">
          宝可梦图鉴
        </div>
      </div>

    </a-layout-content>
    <a-layout-footer>下面放一些说明</a-layout-footer>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent} from 'vue'
import ModuleItem from "@/components/ModuleItem.vue";
import {useRouter} from "vue-router";

export default defineComponent({
  components:{
    ModuleItem
  },
  setup() {
    const router = useRouter()
    const gotoBlog = () => {
      router.push('/forum')
    }
    const gotoPokemon = () => {
      router.push('/pokemon')
    }
    return {
      gotoBlog,
      gotoPokemon
    }
  }
})
</script>

<style scoped lang="scss">
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