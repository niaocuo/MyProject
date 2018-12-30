import Vue from 'vue'
import Router from 'vue-router'
import index from '../components/index/index.vue'
import login from '../components/manager/login.vue'
import manager from '../components/manager/manager.vue'
import pictureHome from '../components/manager/pictureHome.vue'
import productDisplay from '../components/manager/productDisplay.vue'
import contactInformation from '../components/manager/contactInformation.vue'
import NotFoundComponent from '../components/NotFoundComponent.vue'
import home from  '../components/index/home.vue'
import aboutUs from '../components/index/aboutUs.vue'
import products from '../components/index/products.vue'
import information from '../components/index/information.vue'
import contactUs from '../components/index/contactUs.vue'
import showProduct from '../components/index/showProduct.vue'
import showInformation from '../components/index/showInformation.vue'
import aboutCompany from'../components/manager/aboutCompany.vue'
import editorProduct from '../components/manager/editorProduct.vue'
import industryInformation from '../components/manager/industryInformation.vue'
import editorInformation from '../components/manager/editorInformation.vue'
import message from '../components/manager/message.vue'
Vue.use(Router);

const router = new Router({
  // mode: 'history',
  // base: '/',
  // base: '/web',
  routes: [
    {
      path: '*',
      component: NotFoundComponent
    },
    {
      path: '/',
      redirect:'/index/home'
    },
    {
      path: '/index',
      redirect:'/index/home',
      component: index,
      children:[
        {
          path:'/index/home',
          component: home,
        },{
          path: '/index/aboutUs',
          component: aboutUs
        },
        {
          path: '/index/products',
          component: products
        },
        {
          path: '/index/information',
          component: information
        },
        {
          path: '/index/contactUs',
          component: contactUs
        },
        {
          path: '/index/showProduct',
          component: showProduct
        },
        {
          path: '/index/showInformation',
          component: showInformation
        },
      ]
    },
    {
      path: '/mblrLogin',
      component: login
    },
    {
      path: '/manager',
      redirect: '/manager/aboutCompany',
      component: manager,
      children:[
        {
          path:'/manager/aboutCompany',
          component:aboutCompany
        },
        {
          path:'/manager/pictureHome',
          component:pictureHome
        },
        {
          path:'/manager/productDisplay',
          component:productDisplay
        },
        {
          path:'/manager/industryInformation',
          component:industryInformation
        },
        {
          path:'/manager/contactInformation',
          component:contactInformation
        },
        {
          path:'/manager/editorProduct',
          component:editorProduct
        },
        {
          path:'/manager/editorInformation',
          component:editorInformation
        },
        {
          path:'/manager/message',
          component:message
        }

      ]
    }
  ]
});

router.beforeEach((to, from, next) => {
  if (to.path ==="/mblrLogin" && window.sessionStorage.getItem('loginName')) {
    next('/manager')
  } else if (to.path.indexOf('/manager')!=-1 && window.sessionStorage.getItem('loginName')==null ){
    next('/mblrLogin')
  }else{
    next()
  }
});

export default router;
