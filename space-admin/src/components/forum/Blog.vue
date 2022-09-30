<template>
  <div class="blog">
    <div class="blog-title" @click="handleBlogDetail"> title </div>
    <div class="blog-content"> content </div>
    <div class="blog-bottom">
      <div class="blog-bottom-left">
        <a-space>
          <div class="blog-preview">
            <eye-outlined /> num
          </div>
          <div class="blog-like">
            <heart-outlined /> num
          </div>
          <div class="blog-comment">
            <message-outlined /> num
          </div>
        </a-space>
      </div>
      <div class="blog-bottom-right">
        <a-space>
          <div class="blog-owner"> userName </div>
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
  setup() {
    const handleBlogDetail = () => {
      router.push('/forum/blogDetail')
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