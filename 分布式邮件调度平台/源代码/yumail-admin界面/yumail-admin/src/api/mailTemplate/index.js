import request from '@/plugin/axios'

export function ListAllMailTemplates (data) {
    return request({
        url: '/mail-templates',
        method: 'get',
        data
    })
}

export function ListMailTemplatesByAppId (appId) {
    return request({
        url: '/mail-templates/' + appId,
        method: 'get'
    })
}

export function AddMailTemplate(data) {
    return request({
        url: '/mail-templates',
        method: 'post',
        data
    })
}
