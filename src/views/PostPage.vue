<template>
  <div>
      <Header></Header>
    <ImgBanner imgSrc="https://source.unsplash.com/random">
      <div style="margin-bottom:20vw; line-height:1.2em; font-family: 'Righteous', cursive; font-size:calc(30px + 2.5vw)" slot="text">Post</div>
    </ImgBanner>
    <v-container>

      <!-- Post -->
      <v-layout>
        <v-flex xs12>
          <PostList :limits=cnt :load-more="true"></PostList>
        </v-flex>
      </v-layout>
      <PostWrite></PostWrite>
    </v-container>
  </div>
</template>

<script>
import ImgBanner from '../components/ImgBanner'
import PostList from '../components/PostList'
import PostWrite from '../components/PostWrite'
import PortfolioListVue from '../components/PostList.vue'
import Header from '../components/Header'

export default {
	name: 'PostPage',
  props:{
    cnt:PostList.limits,
    loadmore:PostList.loadmore
  },
	components: {
		ImgBanner,
    PostList,
    PostWrite,
    Header
	}
}
</script>
