<template>
  <div style="width: 100%;margin-top: 20px;padding: 0 20px">
    <a-row :gutter="10">
      <a-col :xs="24" :sm="24" :md="19" :lg="19" :xl="19">
        <Blog
            v-for="blog in blogList"
            :key="blog.id"
            :blog="blog"
        />
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
  </div>
</template>

<script lang="ts">
import {defineComponent, onMounted, reactive, ref} from 'vue'
import Blog from "@/components/forum/Blog.vue";
import {apiGetBlogList} from "@/api/forum/blog";
import {BlogModel} from "@/types/model";

export default defineComponent({
  components:{
    Blog,
  },
  setup() {
    let blogList = reactive<BlogModel[]>([])

    function getBlogData(){
      apiGetBlogList().then(res => {
        blogList = Object.assign(blogList,res.data.list)
        console.log('blogList',blogList);
      })
    }

    onMounted(() => {
      getBlogData()
    })

    return {
      blogList
    }
  }
})
</script>

<style scoped lang="scss">
</style>