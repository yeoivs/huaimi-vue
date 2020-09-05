module.exports = {
    publicPath: process.env.NODE_ENV === 'production' ? '/path/' : '/',
    outputDir: 'dist',
    assetsDir: 'assets',
    lintOnSave: process.env.NODE_ENV !== 'production',
    devServer: {
        open: false,
        host: '0.0.0.0',
        port: 8080,
        https: false,
        hotOnly: false,
        proxy: {
            '/api': {
                target: 'http://localhost:9101/huaimi-api',
                ws: true,
                changeOrigin: true,
                pathRewrite: {
                    '^/api': '/'
                }
            }
        },
        overlay: {
            warnings: true,
            errors: true
        }
    },
    parallel: require("os").cpus().length > 1,
}
