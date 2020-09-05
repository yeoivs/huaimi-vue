/*import Vue from 'vue'*/
import axios from 'axios'
/*import VueAxios from 'vue-axios'

Vue.use(VueAxios, axios)*/

import storage from "@/utils/storage";

const instance = axios.create()

instance.defaults.baseURL = '/api'
instance.defaults.headers.post['Content-Type'] = 'application/json'

instance.interceptors.request.use(config => {
    const token = storage.getToken()
    if(token){
        config.headers['Authorization'] = token
    }
    return config;
}, error => {
    return Promise.reject(error)
})

instance.interceptors.response.use(response => {
    console.log(response)
    const status = response.data['code'] || response.status
    if(status === 200 || status === 0){
        return response.data
    }else{
        return response
    }
}, error => {
    return Promise.reject(error)
})

export default instance
