import service from "@/config/service";

export async function list(data) {
    return await service.post('/system/user/list', data)
}

export async function get(id){
    return await service.get('/system/user/'+id)
}

export async function add(data){
    return service.post('/system/user', data)
}

export async function edit(data){
    return service.put('/system/user', data)
}

export async function del(params){
    return service.delete('/system/user', params)
}

const user = {get, list, edit, del, add};

export default user
