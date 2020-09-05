import instance from '@/config/interceptors'

export function get(url, params){
    return instance.get(url, params)
}

export function post(url, data){
    return instance.post(url, data);
}

export function put(url, data){
    return instance.put(url, data);
}

export function del(url, params){
    return instance.delete(url, params);
}

export function request(config){
    return instance.request(config);
}

const service = {get, post, put, del, request}

export default service
