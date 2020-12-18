import request from '@/utils/request'
const group_name = 'demo'
export function login(username, password) {
  return request({
    url: `/${group_name}/user/login`,
    method: 'post',
    data: {
      username,
      password
    }
  })
}

export function getInfo(token) {
  return request({
    url: `/${group_name}/user/info`,
    method: 'post',
    params: { token }
  })
}

export function logout() {
  return request({
    url: `/${group_name}/user/logout`,
    method: 'get'
  })
}
