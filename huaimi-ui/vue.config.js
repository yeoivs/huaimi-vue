module.exports = {
    publicPath: process.env.NODE_ENV === 'production' ? '' : '/',
    outputDir: 'dist',
    assetsDir: 'static',
    lintOnSave: true,
    productionSourceMap: false,
    devServer: {
        host: '0.0.0.0',
        port: 80,
        open: false,
        https: false,
        hotOnly: true,
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:9011/',
                //ws: true,
                changeOrigin: false,
                pathRewrite: {
                    '^/api': ''
                }
            }
        }
    },
    parallel: require('os').cpus().length > 1
}
