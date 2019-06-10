import Vue from 'vue'
import App from './App.vue'
import './plugins/iview.js'
import router from './router'
import axios from 'axios'

Vue.config.productionTip = false;
axios.defaults.timeout = 60000;

new Vue({
  router,
  render: h => h(App)
}).$mount('#app');
