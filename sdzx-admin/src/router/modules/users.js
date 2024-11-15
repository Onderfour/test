const Layout = () => import('@/layout/index.vue')
const userInfo = () => import('@/views/users/userInfo.vue')

export default [
    {
      path: '/users',
      component: Layout,
      name: 'users',
      meta: {
        title: '会员管理',
      },
      icon: 'Operation',
      children: [
            {
            path: '/userInfo',
            name: 'userInfo',
            component: userInfo,
            meta: {
                title: '会员列表',
            },
            }
        ],
    },
  ]