<template>
  <v-layout row wrap mw-1000>
    <v-flex xs12 sm6 md4 v-bind:key="i" v-for="i in posts.length > limits ? limits : posts.length">
      <Post
              :bno="posts[i-1].bno"
              :create_at="posts[i-1].create_at"
              :title="posts[i-1].title"
              :content="posts[i-1].content"
			  :author="posts[i-1].author"
	  ></Post>
      <v-divider></v-divider>
    </v-flex>
    <v-flex xs12 text-xs-center round my-5 v-if="limits < posts.length">
      <v-btn color="rgb(123,142,169)" dark v-on:click="loadMorePosts"><v-icon size="25" class="mr-2">fa-plus</v-icon> 더 보기</v-btn>
    </v-flex>
  </v-layout>
</template>
<script>
import Post from '@/components/Post'
import FirebaseService from '@/services/FirebaseService'

export default {
	name: 'PostList',
	data() {
		return {
			posts: [],
			limits:6,
			loadMore:true
		}
	},
	components: {
		Post
	},
	mounted() {
		this.getPosts()
	},
	methods: {
		async getPosts() {
			await this.$axios.post(
            'http://192.168.100.90:8000/api/posts/getAll'
			)
			.then(response => {
				this.posts = response.data
			});
		},
		loadMorePosts() {
			this.limits+=6;
			if(this.limits>=this.posts.length){
				this.limits=this.posts.length;
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
