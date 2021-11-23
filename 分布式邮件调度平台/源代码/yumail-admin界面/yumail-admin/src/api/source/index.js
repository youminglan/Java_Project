import request from '@/plugin/axios'

export function ListAllSources (data) {
    return request({
        url: '/sources',
        method: 'get',
        data
    })
}

export function ListAppIds (data) {
    return request({
        url: '/sources/app-ids',
        method: 'get',
        data
    })
}
