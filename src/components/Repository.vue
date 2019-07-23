<style>
#repo_name{
	white-space : nowrap;
	 overflow: hidden;
   text-overflow: ellipsis;
   width:70vw;
	/* word-break : nowrap;  */
	 /* word-break:break-all;
    white-space: nowrap;
   overflow: hidden;
   text-overflow: ellipsis;
   width:80vw; */
}
</style>

<template>
  <div class="py-3">
    <v-layout>

      <v-flex xs8 mw-1000>
        <h2 class="font-weight-regular" id="repo_name">{{repos.path_with_namespace}}</h2>
        <p class="subheading mb-1 grey--text text--darken-1 font-weight-light">{{repos.namespace.name}}</p>
      </v-flex>

    </v-layout>
  </div>
</template>

<script>
import GitlabService from '@/services/GitlabService'

export default {
	name: 'Repository',
	props: {
		repos: {type: null}
	},
	data() {
		return {
			stats: {}
		}
	},
  mounted() {
		this.drawStatGraph()
  },
	methods: {
		async drawStatGraph() {
			this.commits = await GitlabService.getCommits(this.repos.id,1)
		}
	}
}
</script>
