<template>
  <div class="blog">
    <div class="blog-title" @click="handleBlogDetail(blog.id)"> {{ blog.title }} </div>
    <div class="blog-content"> {{ blog.summary || '' }} </div>
    <div class="blog-bottom">
      <div class="blog-bottom-left">
        <a-space>
          <div class="blog-preview">
            <eye-outlined /> {{ blog.preview || 0 }}
          </div>
          <div class="blog-like">
            <heart-outlined /> {{ blog.likes || 0 }}
          </div>
          <div class="blog-comment">
            <message-outlined /> {{ blog.dislikes || 0 }}
          </div>
        </a-space>
      </div>
      <div class="blog-bottom-right">
        <a-space>
          <div class="blog-owner"> {{ blog.userId }} </div>
          <div class="blog-publish-time"> publishTime </div>
        </a-space>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import {defineComponent} from 'vue'
import {EyeOutlined, HeartOutlined, MessageOutlined} from "@ant-design/icons-vue";
import router from "@/router";

export default defineComponent({
  components:{
    HeartOutlined,
    EyeOutlined,
    MessageOutlined
  },
  props:['blog'],
  setup() {
    const handleBlogDetail = (blogId:string) => {
      router.push('/forum/blogDetail/'+blogId)
    }
    return {
      handleBlogDetail
    }
  }
})
</script>

<style scoped lang="scss">
.blog{
  width: 100%;
  padding: 16px 24px;
  background-color: #FFF;
  border-bottom: 1px solid rgb(233, 233, 233);
  & .blog-title{
    font-size: 25px;
    font-weight: bold;
    cursor: pointer;
  }
  & .blog-content{
    font-size: 20px;
  }
  & .blog-bottom{
    display: flex;
    & .blog-bottom-left{
      flex: 1;
      display: flex;
    }
    & blog-bottom-right{
      flex: 1;
      display: flex;
      margin-left: auto;
    }
  }
}
</style>