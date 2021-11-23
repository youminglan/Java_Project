import request from '@/plugin/axios'

export function AccountLogin (data) {
  return request({
    url: '/users/login',
    method: 'post',
    data
  })
}
