// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import App from './App.vue'
import router from './router'
import resource from 'vue-resource'
import 'babel-polyfill'

Vue.config.productionTip = false;
Vue.use(ElementUI);
Vue.use(resource);
// Vue.http.options.emulateJSON = true;
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
});

Vue.http.interceptors.push((request, next) => {
  next((response) => {//在响应之后传给then之前对response进行修改和逻辑判断。对于token时候已过期的判断，就添加在此处，页面中任何一次http请求都会先调用此处方法
    if(response.url.indexOf("/manager") != -1){
      if(response.data.hasOwnProperty('errCode') ){
        window.sessionStorage.removeItem("loginName");
        router.replace({
          path: '/mblrLogin'
        });
      }
    }
    return response;
  });
});
