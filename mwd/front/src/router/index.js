import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

const routers = new Router({
    routes:[
        /*{
            path:'/',
            redirect: '/login',
        },*/
        {
            path:'/login',
            name:'login',
            component:()=>import('@/views/login')
        },
        {
          path:'/register',
          name:'register',
          component:()=>import('@/views/login/register')
        },
        {
            path:'/index',
            name:'index',
            component:()=> import('@/views/index'),
            redirect:'/homePage',
            children:[
                {
                    path:'/homePage',
                    name:'homePage',
                    component: () => import('@/views/homePage'),
                },
                {
                    path:'/userInfo',
                    name:'userInfo',
                    component:() =>import('@/views/personalCenter/userInfo'),
                },
                {
                    path:'/fileUpload',
                    name:'fileUpload',
                    component:() =>import('@/views/personalCenter/fileUpload'),
                },
                {
                    path:'/bookmark',
                    name:'bookmark',
                    component:() =>import('@/views/personalCenter/bookmark'),
                },
                {
                    path:'/order',
                    name:'order',
                    component:()=>import('@/views/order')
                }
            ]
        },
    ]
})

export default routers
