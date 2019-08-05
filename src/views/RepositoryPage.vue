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

    <!-- Repository -->
      <v-layout my-5>
        <v-flex xs12>
          <h2 class="headline my-5 text-xs-center">Projects</h2>
          <RepositoryList></RepositoryList>
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

export default {
	name: 'RepositoryPage',
	components: {
		ImgBanner,
    Header,
    Footer,
    Repository,
    RepositoryList,
    Graph
	}
}
</script>
