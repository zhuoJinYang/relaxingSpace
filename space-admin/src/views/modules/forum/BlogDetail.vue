<template>
  <div>
    <div>{{ $route.params.blogId }}</div>
    <div>{{ blogDetail.title }}</div>
    <div>extra-info</div>
    <div>{{ blogDetail.content }}</div>
  </div>
</template>

<script lang="ts">
import {defineComponent, onMounted, reactive, ref} from 'vue'
import router from "@/router";
import {apiGetBlogDetail} from "@/api/forum/blog";
import {BlogModel} from "@/types/model";

export default defineComponent({
  setup() {
    const blogId = ref<string>()

    let blogDetail = reactive<BlogModel>({
      id:'',
      title:'',
      label:[]
    })

    function getBlogDetail(id:string){
      apiGetBlogDetail(id).then(res => {
        blogDetail = Object.assign(blogDetail,res.data)
      })
    }

    onMounted(() => {
      blogId.value = router.currentRoute.value.params.blogId as string
      getBlogDetail(blogId.value)
    })

    return {
      blogDetail
    }
  }
})
</script>

<style scoped lang="scss"></style>