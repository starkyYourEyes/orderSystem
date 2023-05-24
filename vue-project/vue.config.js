const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  devServer:{
    proxy: {
      '/': {
        ws: false,
        target: 'http://localhost',
        changeOrigin: true
      },
    },
    port: 81
  }
})
