import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],

  server: {
    proxy: {
      '/studycow/api': {
        target: 'https://i11c202.p.ssafy.io',
        changeOrigin: true,
      }
    }
  }
})
