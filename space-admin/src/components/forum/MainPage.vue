<template>
  <div style="width: 100%;">
    <div>
      <div class="blog">
        <div class="blog-title"> title </div>
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
      <a-divider />
      <div>文章2</div>
      <a-divider />
    </div>
  </div>
</template>

<style lang="scss">
.blog{
  width: 80%;
  padding: 16px 24px;
  & .blog-title{
    font-size: 25px;
    font-weight: bold;
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

<script lang="ts">
import {defineComponent} from 'vue'
import {HeartOutlined,EyeOutlined,MessageOutlined} from '@ant-design/icons-vue';

export default defineComponent({
  components:{
    HeartOutlined,
    EyeOutlined,
    MessageOutlined
  },
  setup() {
    return {}
  }
})
</script>

<style scoped lang="scss">
</style>