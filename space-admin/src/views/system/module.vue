<template>
  <a-layout>
    <a-layout-header class="header">
      <div>
        欢迎来到爷的世界（请选择你想玩的模块吧，训练家）
      </div>
    </a-layout-header>
    <a-layout-content class="content">
      <a-row :wrap="true" :gutter="[24,8]">
        <a-col :span="6" v-for="module in modules">
          <div class="module-item" @click="gotoModule(module.path)">
            {{module.name}}
          </div>
        </a-col>
      </a-row>
    </a-layout-content>
    <a-layout-footer>下面放一些说明</a-layout-footer>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent, reactive} from 'vue'
import {useRouter} from "vue-router";
import {ModuleRecord} from "@/types/public";

const moduleList: ModuleRecord[] = [
  {
    id: '1',
    name: '博客',
    path: '/forum'
  },
  {
    id: '2',
    name: '宝可梦图鉴',
    path: '/pokemon'
  }
]

export default defineComponent({
  setup() {
    const router = useRouter()
    const modules = reactive(moduleList)
    const gotoModule = (path: string) => {
      router.push(path)
    }
    return {
      modules,
      gotoModule
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
  padding: 20px 10px;
}
.module-item{
  width: 100% ;
  height: 120px;
  border-radius: 8px;
  background-color: rgba(172, 224, 229, 0.51);
  font-size: 18px;
  text-align: center;
  line-height: 120px;
  cursor: pointer;
}
.module-item:hover{
  background-color: rgba(172, 224, 229, 1);
}
</style>