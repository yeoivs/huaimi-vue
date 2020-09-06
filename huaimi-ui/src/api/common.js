import service from "@/config/service";

export function getMenu(){
    return service.get('/comm/menu')
}
