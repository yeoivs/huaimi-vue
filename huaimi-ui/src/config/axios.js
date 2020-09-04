import Axios from 'axios'

const service = Axios.create({
    baseURL: '/api',
    timeout: 5000
})

service.defaults.headers.post['Content-Type'] = 'application/json'

service.interceptors.request.use(config => {
    //'Authorization'
    return config
}, error => {
    Promise.reject(error)
})

service.interceptors.response.use(response => {

    return response
}, error => {
    Promise.reject(error)
})

export default service