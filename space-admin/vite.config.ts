import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import path from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve:{
    alias:{
      // 如果报错__dirname找不到，需要安装node,执行yarn add @types/node --save-dev
      '@': path.resolve(__dirname, 'src'),
      'mods': path.resolve(__dirname, 'src/views/modules'),
    }
  },
  server:{
    host: '0.0.0.0',
    port: 12000,
    proxy:{
      '/space-admin': {
        target: 'http://localhost:11001',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/space-admin/, ''),
      }
    }
  }
})
