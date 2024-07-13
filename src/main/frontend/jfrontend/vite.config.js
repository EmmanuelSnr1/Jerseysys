import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8080', // Replace with your backend server URL
        changeOrigin: true,
        // Optionally, rewrite paths if needed:
        // rewrite: (path) => path.replace(/^\/api/, ''),
      },
    },
  },
})
