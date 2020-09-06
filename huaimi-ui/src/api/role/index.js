import service from "@/config/service";

export async function list() {
    return await service.get('/system/role/list')
}

export async function get(id){
    return await service.get('/system/role/'+id)
}

export async function add(data){
    return service.post('/system/role', data)
}

export async function edit(data){
    return service.put('/system/role', data)
}

export async function del(params){
    return service.delete('/system/role', params)
}

const role = {get, list, edit, del, add};

export default role
