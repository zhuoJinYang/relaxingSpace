<template>
  <div style="width: 100%;">
    <div class="sort">
      <div class="sort-publish">按最后发布时间排序<swap-outlined :rotate="90"/></div>
      <div class="sort-preview">按访问量排序<swap-outlined :rotate="90"/></div>
      <div class="sort-like">按点赞量排序<swap-outlined :rotate="90"/></div>
    </div>
    <div class="myBlog">
      <Blog
          v-for="blog in blogList"
          :key="blog.id"
          :blog="blog"
      />
    </div>
  </div>
</template>

<style lang="scss">
.myBlog{
  width: 100%;
}
.sort{
  margin-top: 5px;
  display: flex;
  background-color: #FFF;
  border-bottom: 1px solid rgb(233, 233, 233);
  & .sort-publish,.sort-preview,.sort-like{
    margin-left: 10px;
    margin-right: 20px;
    height: 40px;
    line-height: 40px;
  }
}

</style>

<script lang="ts">
import {defineComponent, onMounted, ref} from 'vue'
import Blog from "@/components/forum/Blog.vue";
import {SwapOutlined} from "@ant-design/icons-vue";
import {BlogModel} from "@/types/model";
import {apiGetBlogList} from "@/api/forum/blog";

export default defineComponent({
  components:{
    Blog,
    SwapOutlined
  },
  setup() {
    const searchKeyContent = ref<string>('')
    let blogList = ref<BlogModel[]>([])

    function getBlogData(){
      apiGetBlogList().then(res => {
        blogList.value = res.data.list as BlogModel[]
        console.log('blogList',blogList);
      })
    }

    onMounted(() => {
      getBlogData()
    })
    return {
      blogList,
      searchKeyContent
    }
  }
})
</script>

<style scoped lang="scss">

</style>