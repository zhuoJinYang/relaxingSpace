<template>
  <div style="width: 100%;height: 100vh;display: flex;flex-direction: column">
    <a-button style="margin: 10px 20px 10px auto;" type="primary" @click="publish">发布</a-button>
    <a-form
        style="margin: 0 30px;float: right"
        ref="BlogEdit"
        :model="blogData"
        name="blogEdit"
        :label-col="{ span: 2 }"
        :wrapper-col="{ span: 24 }"
        autocomplete="off">
      <a-form-item
          label="标题"
          name="title"
          :rules="[{ required: true}]">
        <a-input v-model:value="blogData.title" placeholder="请输入博客标题"/>
      </a-form-item>
      <a-form-item
          label="标签"
          name="label"
          :rules="[{ required: true}]">
        <a-select
            v-model:value="blogData.label"
            mode="tags"
            :tokenSeparators="[',']"
            placeholder="设置博客的标签,并按回车分割"
            :open="false"
        ></a-select>
      </a-form-item>
      <a-form-item
          label="概述"
          name="summary"
          :rules="[{ required: false}]">
        <a-input v-model:value="blogData.summary" placeholder="请输入博客概述"/>
      </a-form-item>
      <a-form-item
          label="内容"
          name="content">
        <!--   富文本   -->
        <div style="border: 1px solid #ccc">
          <Toolbar
              style="border-bottom: 1px solid #ccc"
              :editor="editorRef"
              :defaultConfig="toolbarConfig"
              :mode="mode"
          />
          <Editor
              style="height: 500px; overflow-y: hidden;"
              v-model="valueHtml"
              :defaultConfig="editorConfig"
              :mode="mode"
              @onCreated="handleCreated"
          />
        </div>
      </a-form-item>
    </a-form>
  </div>
</template>

<script lang="ts">
import {defineComponent, onBeforeUnmount, onMounted, reactive, ref, shallowRef} from 'vue'
import '@wangeditor/editor/dist/css/style.css' // 引入 富文本css
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import {BlogModel} from "@/types/model";
import {apiSaveBlogList} from "@/api/forum/blog";
import {message} from "ant-design-vue";

export default defineComponent({
  components:{
    Editor,
    Toolbar
  },
  setup() {
    const blogData = reactive<BlogModel>({id:'',userId:'1575724450385186817',title:'',label:[],content:''})

    const publish = () => {
      console.log(blogData)
      apiSaveBlogList({...blogData,content: valueHtml.value}).then(() => {
        message.success("发布成功！")
      })
    }

    // 编辑器实例，必须用 shallowRef
    const editorRef = shallowRef()

    // 内容 HTML
    const valueHtml = ref('<p>hello</p>')

    const toolbarConfig = {}

    const editorConfig = { placeholder: '请输入内容...' }

    // 模拟 ajax 异步获取内容
    onMounted(() => {
      setTimeout(() => {
        valueHtml.value = '<p>模拟 Ajax 异步设置内容</p>'
      }, 500)
    })

    // 组件销毁时，也及时销毁编辑器
    onBeforeUnmount(() => {
      const editor = editorRef.value
      if (editor == null) return
      editor.destroy()
    })

    const handleCreated = (editor:any) => {
      editorRef.value = editor // 记录 editor 实例，重要！
    }

    return {
      blogData,

      editorRef,
      valueHtml,
      mode: 'default', // 或 'simple'
      toolbarConfig,
      editorConfig,
      handleCreated,
      publish
    }
  }
})
</script>

<style scoped lang="scss"></style>