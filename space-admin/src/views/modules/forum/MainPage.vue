<template>
  <div style="width: 100%;margin-top: 20px;display: flex;padding: 0 20px">
    <div class="page-left">
      <Blog/>
      <Blog/>
      <Blog/>
      <Blog/>
      <Blog/>
      <Blog/>
      <Blog/>
      <Blog/>
      <Blog/>
      <Blog/>
      <Blog/>
      <Blog/>
      <Blog/>
      <Blog/>
      <Blog/>
    </div>
    <div class="page-right">
      <a-affix>
        <div class="expand-content">
          <a-card title="推荐" style="width: 300px">
            <p>推荐1</p>
            <p>推荐2</p>
            <p>推荐3</p>
          </a-card>
        </div>
      </a-affix>
    </div>
  </div>
</template>

<style lang="scss">
.page-left{
  width: 80%;
}
.expand-content{
  padding-left: 20px;
}
</style>

<script lang="ts">
import {defineComponent} from 'vue'
import Blog from "@/components/forum/Blog.vue";

export default defineComponent({
  components:{
    Blog,
  },
  setup() {
    return {}
  }
})
</script>

<style scoped lang="scss">
</style>