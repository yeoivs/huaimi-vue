export function toTreeData(data, config = {
    id: 'id', parentId: 'parentId', children: 'children'
}){
    let tree = data.filter(a => a[config.parentId] === 0)
    return treeFun(tree, data, config)
}

function treeFun(tree, row, config){
    let o = tree
    o.forEach((value, index) => {
        let filter = row.filter(a => a[config.parentId] === value[config.id]);
        if(filter != null && filter.length > 0){
            o[index][config.children] = treeFun(filter, row, config)
        }
    })
    return o
}

export function formatDate(row, column) {
    let data = row[column.property]
    if (!data) {
        return ''
    }
    let dt = new Date(data)
    return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate() + ' ' + dt.getHours() + ':' + dt.getMinutes() + ':' + dt.getSeconds()
}

const format = {toTreeData,formatDate }

export default format
