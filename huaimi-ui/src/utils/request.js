import service from '@/config/axios'

export function get(url, params) {
    return service.get(url, {params})
}

export function post(url, data){
    return service.post(url, data)
}

export function put(url, data){
    return service.put(url, data)
}

export function del(url, params){
    return service.delete(url, {params})
}

export function request(config){
    return service.request(config)
}