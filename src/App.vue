<template>
  <v-app>
    <v-content>
      <router-view/>
    </v-content>
	<TopButton></TopButton>
	<ChatBot></ChatBot>
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
import TopButton from './components/TopButton'
import ChatBot from './components/ChatBot'

Vue.prototype.$axios = axios
Vue.use(Vuetify)
Vue.use(VueSwal)
AOS.init()

export default {
	name: 'App',
	components: {
		Header,
		TopButton,
		Notification,
		ChatBot
	},
	store
}
</script>
