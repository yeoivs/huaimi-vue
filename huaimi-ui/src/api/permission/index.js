import service from "@/config/service";

export async function list() {
    return await service.get('/system/permission/list')
}

export async function get(id){
    return await service.get('/system/permission/'+id)
}

export async function add(data){
    return service.post('/system/permission', data)
}

export async function edit(data){
    return service.put('/system/permission', data)
}

export async function del(params){
    return service.delete('/system/permission', params)
}

const permission = {get, list, edit, del, add};

export default permission
