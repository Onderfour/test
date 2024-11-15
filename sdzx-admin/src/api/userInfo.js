import request from '@/utils/request'

const api_name = '/admin/users/userInfo'

// 分页列表
export const GetUserInfoPageList = (page, limit,queryDto) => {
  return request({
    url: `${api_name}/${page}/${limit}`,
    method: 'get',
    params: queryDto

  })
}
// 保存品牌
export const SaveUserInfo = userInfo => {
    return request({
        url: `${api_name}/save`,
        method: 'post',
        data: userInfo,
    })
}
// 修改信息
export const UpdateUserInfoById = userInfo => {
    return request({
        url: `${api_name}/updateById`,
        method: 'put',
        data: userInfo,
    })
}
// 根据id删除品牌
export const DeleteUserInfoById = id => {
  return request({
    url: `${api_name}/deleteById/${id}`,
    method: 'delete',
  })
}
// 查询所有的品牌数据
export const FindAllUserInfo = () => {
  return request({
    url: `${api_name}/findAll`,
    method: 'get',
  })
}