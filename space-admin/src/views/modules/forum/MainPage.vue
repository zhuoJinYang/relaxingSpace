<template>
  <div style="width: 100%;margin-top: 20px;padding: 0 20px">
    <a-row :gutter="10">
      <a-col :xs="24" :sm="24" :md="19" :lg="19" :xl="19">
        <Blog v-for="i in 20"/>
      </a-col>
      <a-col :xs="0" :sm="0" :md="5" :lg="5" :xl="5">
        <a-affix>
          <div class="expand-content">
            <a-card title="推荐">
              <p>推荐1</p>
              <p>推荐2</p>
              <p>推荐3</p>
            </a-card>
          </div>
        </a-affix>
      </a-col>
    </a-row>
<!--    <div class="page-left">-->
<!--      <Blog v-for="i in 20"/>-->
<!--    </div>-->
<!--    <div class="page-right">-->
<!--      <a-affix>-->
<!--        <div class="expand-content">-->
<!--          <a-card title="推荐" style="width: 300px">-->
<!--            <p>推荐1</p>-->
<!--            <p>推荐2</p>-->
<!--            <p>推荐3</p>-->
<!--          </a-card>-->
<!--        </div>-->
<!--      </a-affix>-->
<!--    </div>-->
  </div>
</template>

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