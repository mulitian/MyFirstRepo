import { fileURLToPath, URL } from 'node:url'

import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  server: {  
    // 设置开发服务器监听的主机名  
    // 0.0.0.0 允许从任何IPv4地址访问  
    host: '0.0.0.0',  
    // 默认情况下，Vite 会尝试智能地推断端口，但你也可以显式设置它  
    port: 5174, // 例如，设置为3000端口  
    // 如果需要，还可以设置其他server选项...  
  }, 
  plugins: [
    vue(),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
