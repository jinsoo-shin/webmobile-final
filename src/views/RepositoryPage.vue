<template>
  <div>
      <Header></Header>
    <ImgBanner imgSrc="https://source.unsplash.com/random">
      <div style="margin-bottom:20vw; line-height:1.2em; font-family: 'Righteous', cursive; font-size:calc(30px + 2.5vw)" slot="text">Repository</div>
    </ImgBanner>

    <v-container>

      <!-- graph -->
      <v-layout my-5>
        <v-flex hidden-xs-only>
          <h2 class="headline text-xs-center mb-3">Commit Counts</h2>
            <Graph></Graph>
        </v-flex>
      </v-layout>

      <!-- btn -->
      <v-layout hidden-xs-only mx-3 mb-5 row align-center justify-space-around>
          <a target = "_blank" href="https://lab.ssafy.com/SeongHaPark"><v-icon size="25" class="mb-5 pb-5">fa-gitlab</v-icon></a>
          <a target = "_blank" href="https://lab.ssafy.com/jinsoo"><v-icon size="25" class="mb-5 pb-5">fa-gitlab</v-icon></a>
          <a target = "_blank" href="https://lab.ssafy.com/ahnhyunsang"><v-icon size="25" class="mb-5 pb-5">fa-gitlab</v-icon></a>
          <a target = "_blank" href="https://lab.ssafy.com/wiahawm"><v-icon size="25" class="mb-5 pb-5">fa-gitlab</v-icon></a>
          <a target = "_blank" href="https://lab.ssafy.com/chosm10"><v-icon size="25" class="mb-5 pb-5">fa-gitlab</v-icon></a>
      </v-layout>


    <!-- Repository -->
      <v-layout my-5>
        <v-flex xs12>
          <h2 class="headline my-5 text-xs-center">Projects</h2>
          <test></test>
        </v-flex>
      </v-layout>
    </v-container>


    <Footer></Footer>
  </div>
</template>

<script>
import ImgBanner from '../components/ImgBanner'
import Repository from '../components/Repository'
import RepositoryList from '../components/RepositoryList'
import Graph from '../components/Graph.vue'
import Header from '../components/Header'
import Footer from '../components/Footer'
import test from '../components/test'

export default {
	name: 'RepositoryPage',
	components: {
		ImgBanner,
    Header,
    Footer,
    Repository,
    RepositoryList,
    Graph,
    test
	}
}
</script>
