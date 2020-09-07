/*import Vue from 'vue'*/
import axios from 'axios'
/*import VueAxios from 'vue-axios'

Vue.use(VueAxios, axios)*/

import router from "@/router";

import {MessageBox} from 'element-ui'

import storage from "@/utils/storage";

const service = axios.create({
    baseURL: '/api'
})

service.defaults.headers['Content-Type'] = 'application/json'

service.interceptors.request.use(config => {
    const token = storage.getToken()
    if(token){
        config.headers['Authorization'] = token
    }
    console.log('request => ', config)
    return config;
}, error => {
    return Promise.reject(error)
})

service.interceptors.response.use(response => {
    console.log('response => ', response)
    const status = response.data['code'] || response.status
    if(status === 200 || status === 0){
        return Promise.resolve(response.data)
    }else{
        if(status === 401){
            loginMessage()
        } else {
            return Promise.reject(response)
        }
    }
}, error => {
    return Promise.reject(error.response ? error.response : error)
})

function loginMessage(){
    MessageBox.confirm('登录信息失效，请重新登录。', "系统提示").then(r => {
        storage.clear()
        location.reload()
    })
}

export default service
