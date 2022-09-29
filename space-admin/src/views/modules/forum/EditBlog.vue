<template>
<!--  <div>-->
<!--    <div>标题</div>-->
<!--    <div>标签</div>-->
<!--    <div>概述</div>-->
<!--    <div>内容</div>-->
<!--  </div>-->
  <a-form
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
      <a-input v-model:value="blogData.title" />
    </a-form-item>
    <a-form-item
        label="标签"
        name="label"
        :rules="[{ required: true}]">
      <a-input v-model:value="blogData.label" />
    </a-form-item>
    <a-form-item
        label="概述"
        name="summary"
        :rules="[{ required: true}]">
      <a-input v-model:value="blogData.summary" />
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
</template>

<script lang="ts">
import {defineComponent, onBeforeUnmount, onMounted, reactive, ref, shallowRef} from 'vue'
import '@wangeditor/editor/dist/css/style.css' // 引入 富文本css
import { Editor, Toolbar } from '@wangeditor/editor-for-vue'
import {BlogModel} from "@/types/model";

export default defineComponent({
  components:{
    Editor,
    Toolbar
  },
  setup() {
    const blogData = reactive<BlogModel>({id:'',userId:'',title:'',label:''})

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
      }, 1500)
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
      handleCreated
    }
  }
})
</script>

<style scoped lang="scss"></style>