/*import Vue from 'vue'*/
import axios from 'axios'
/*import VueAxios from 'vue-axios'

Vue.use(VueAxios, axios)*/

import {MessageBox} from 'element-ui'

import storage from "@/utils/storage";

const service = axios.create()

service.defaults.baseURL = '/api'
service.defaults.headers.post['Content-Type'] = 'application/json'


service.interceptors.request.use(config => {
    const token = storage.getToken()
    if(token){
        config.headers['Authorization'] = token
    }
    return config;
}, error => {
    return Promise.reject(error)
})

service.interceptors.response.use(response => {
    console.log(response)
    const status = response.data['code'] || response.status
    if(status === 200 || status === 0){
        return response.data
    }else{
        if(status === 401){
            MessageBox.confirm('登录信息失效，请重新登录。', "系统提示").then(r => {
                storage.clear()
                location.reload()
            })
        } else {
            return Promise.reject(response)
        }
    }
}, error => {
    return Promise.reject(error.response ? error.response : error)
})

export default service
