<template>
  <v-layout column px-4>
    <v-flex v-bind:key="i" v-for="i in repositories.length > limits ? limits : repositories.length">
      <v-divider v-if="i === 1"></v-divider>
      <Repository :repos="repositories[i - 1]"></Repository>
      <v-divider></v-divider>
    </v-flex>

	<v-flex xs12 text-xs-center round my-5 v-if="loadMore">
      <v-btn color="rgb(123,142,169)" dark v-on:click="loadMoreReposistories"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>
    </v-flex>
  </v-layout>
</template>

<script>
import Repository from '@/components/Repository'
import GitlabService from '@/services/GitlabService'

export default {
	name: 'RepositoryList',
	data() {
		return {
			repositories: [],
			limits:3,
			loadMore:true
    }
	},
	components: {
		Repository
	},
	mounted() {
		this.getGitlabRepos()
	},
	mounted() {
		this.getGitlabRepos('chosm10')
	},
	methods: {
		async getGitlabRepos() {
			const response = await GitlabService.getRepos()
			if(response.status !== 200) {
				return
			}
			this.repositories = response.data
			
		},
		loadMoreReposistories() {
			this.limits+=2;
			if(this.limits>=this.repositories.length){
				this.limits=this.repositories.length;
				this.loadMore=false;
			}
		}
	}
}
</script>
