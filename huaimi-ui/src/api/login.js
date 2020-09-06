import service from '@/config/service'

export async function login(data){
    return await service.post('/login', data)
}

export async function logout(){
    return await service.get('/logout')
}
