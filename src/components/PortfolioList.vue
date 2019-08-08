<template>
  <v-layout mt-5 wrap>
    <v-flex v-bind:key="i" xs12 sm6 md4 v-for="i in portfolios.length > limits ? limits : portfolios.length">
      <Portfolio class="ma-3"
	  		  :bno="portfolios[i-1].bno"
              :create_at="portfolios[i-1].create_at"
              :title="portfolios[i-1].title"
              :content="portfolios[i-1].content"
			  :img="portfolios[i-1].img"
			  :author="portfolios[i-1].author"
			  :email="portfolios[i-1].email"
      ></Portfolio>
    </v-flex>

    <v-flex xs12 text-xs-center round my-5 v-if="limits < portfolios.length">
      <v-btn color="rgb(123,142,169)" dark v-on:click="loadMorePortfolios"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>
    </v-flex>
  </v-layout>
</template>
<script>
import Portfolio from '@/components/Portfolio'
import FirebaseService from '@/services/FirebaseService'

export default {
	name: 'PortfoliosList',
	data() {
		return {
			portfolios: [],
			limits:6,
			loadMore:true
		}
	},
	components: {
		Portfolio
	},
	mounted() {
		this.getPortfolios()
	},
	methods: {
		async getPortfolios() {
			await this.$axios.post(
            'https://192.168.100.90:8000/api/portfolios/getAll'
			)
			.then(response => {
				this.portfolios = response.data
				localStorage.setItem("portfolios", JSON.stringify(response.data));
			})
			.catch( response => { 
			this.portfolios=JSON.parse(localStorage.getItem("portfolios"))
			});
		},
		loadMorePortfolios() {
			this.limits+=6;
			if(this.limits>=this.portfolios.length){
				this.limits=this.portfolios.length;
				this.loadMore=false;
			}
		}
	}
}
</script>
<style>
  .mw-700 {
    max-width: 700px;
    margin: auto;
  }
</style>
