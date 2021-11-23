import request from '@/plugin/axios'

export function ListAllJobs () {
    return request({
        url: '/jobs',
        method: 'get'
    })
}

export function ListJobsByAppId (appId) {
    return request({
        url: '/jobs/' + appId,
        method: 'get'
    })
}

export function PauseScheduler () {
    return request({
        url: '/jobs/scheduler/pause',
        method: 'put'
    })
}

export function StartScheduler () {
    return request({
        url: '/jobs/scheduler/start',
        method: 'put'
    })
}

export function GetSchedulerStatus () {
    return request({
        url: '/jobs/scheduler/status',
        method: 'get'
    })
}

export function PauseJob (jobName, jobGroupName) {
    return request({
        url: `/jobs/pause/${jobName}/${jobGroupName}`,
        method: 'put'
    })
}
export function ResumeJob (jobName, jobGroupName) {
    return request({
        url: `/jobs/resume/${jobName}/${jobGroupName}`,
        method: 'put'
    })
}
export function DelJob (jobName, jobGroupName) {
    return request({
        url: `/jobs/${jobName}/${jobGroupName}`,
        method: 'delete'
    })
}