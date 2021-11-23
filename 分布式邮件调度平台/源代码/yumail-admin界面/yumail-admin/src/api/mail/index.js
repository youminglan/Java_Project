import request from '@/plugin/axios'

export function SendHtmlMail (data) {
  return request({
    url: '/mails',
    method: 'post',
    data
  })
}
