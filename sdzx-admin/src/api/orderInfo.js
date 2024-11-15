import request from '@/utils/request'

const api_name = '/admin/order/orderInfo'

// 订单统计
export const GetOrderStatisticsData = searchObj => {
  return request({
    url: `${api_name}/getOrderStatisticsData`,
    method: 'get',
    params: searchObj,
  })
}

// 分页列表
export const GetOrderPageList = (page, limit,queryDto) => {
  return request({
    url: `${api_name}/${page}/${limit}`,
    method: 'get',
    params: queryDto
  })
}
// 查询所有的品牌数据
export const FindAllOrder = () => {
  return request({
    url: `${api_name}/findAll`,
    method: 'get',
  })
}
export const FindAllOrderdetails = (orderNo,orderId) => {
  return request({
    url: `${api_name}/details`,
    method: 'get',
    params: {orderNo,orderId}
  })
}

export const updateOrderInfo = orderInfo=> {
  return request({
    url: `${api_name}/updateOrderInfo`,
    method: 'put',
    data: orderInfo
  })
}