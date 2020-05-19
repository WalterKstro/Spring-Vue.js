import Vue from 'vue'
import App from './App.vue'
import './registerServiceWorker'
import router from './router'
import store from './store'
// Import components VuePrimefaces
import DataTable from 'primevue/datatable'
import Column from 'primevue/column'
import Panel from 'primevue/panel'
import Menubar from 'primevue/menubar'
import Dialog from 'primevue/dialog'
import Calendar from 'primevue/calendar'
import InputNumber from 'primevue/inputnumber'
import Button from 'primevue/button'
import InputText from 'primevue/inputtext'
import Chart from 'primevue/chart'
// Import style css
import 'primevue/resources/themes/nova-vue/theme.css'
import 'primevue/resources/primevue.min.css'
import 'primeicons/primeicons.css'
import 'primeflex/primeflex.css'

// Habilitacion de los componentes
Vue.component('DataTable', DataTable)
Vue.component('Column', Column)
Vue.component('Panel', Panel)
Vue.component('Menubar', Menubar)
Vue.component('Dialog', Dialog)
Vue.component('Calendar', Calendar)
Vue.component('InputNumber', InputNumber)
Vue.component('Button', Button)
Vue.component('InputText', InputText)
Vue.component('Chart', Chart)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
