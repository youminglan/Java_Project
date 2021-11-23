import request from '@/plugin/axios'

export function ListAllLogs () {
    return request({
        url: '/logs',
        method: 'get'
    })
}

export function ListLogsByAppId (appId) {
    return request({
        url: '/logs/' + appId,
        method: 'get'
    })
}

export function DoPersist(appId) {
    return request({
        url: '/logs/persist/' + appId,
        method: 'post'
    })
}
