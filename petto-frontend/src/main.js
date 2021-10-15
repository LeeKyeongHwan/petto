import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import cookies from 'vue-cookies'

Vue.use(cookies)
Vue.$cookies.config("1h") 

import 'materialize-css/dist/css/materialize.min.css'
import 'material-design-icons/iconfont/material-icons.css'

window.Kakao.init("7a62c696242b86d3ca340fa4be88e45c")
console.log('Kakao 초기화 여부 : ' + window.Kakao.isInitialized())

Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')