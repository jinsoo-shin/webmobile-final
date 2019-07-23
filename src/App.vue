<template>
  <v-app>
	<Header></Header>
    <v-content>
      <router-view/>
    </v-content>
	<Footer></Footer>
	<TopButton></TopButton>
	<Notification></Notification>
  </v-app>
</template>
<script>
import store from './store'
import Vue from 'vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import axios from 'axios'
import VueSwal from 'vue-swal'
import './css/style.css'
import AOS from 'aos';
import 'aos/dist/aos.css';
import Header from './components/Header'
import Footer from './components/Footer'
import TopButton from './components/TopButton'
import Notification from './components/Notification.vue'

Vue.prototype.$axios = axios
Vue.use(Vuetify)
Vue.use(VueSwal)
AOS.init()

export default {
	name: 'App',
	components: {
		Header,
		Footer,
		TopButton,
		Notification
	},
	store
}
</script>
