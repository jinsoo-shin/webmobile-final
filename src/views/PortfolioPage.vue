<template>
  <div>
      <Header></Header>
    <ImgBanner imgSrc="https://source.unsplash.com/random">
      <div style="margin-bottom:20vw; line-height:1.2em; font-family: 'Righteous', cursive; font-size:calc(30px + 2.5vw)" slot="text">Portfolio</div>
    </ImgBanner>
    <v-container>

      <!-- Portfolio -->
      <v-layout>
        <v-flex xs12>
          <PortfolioList :limits=cnt :load-more=loadmore></PortfolioList>
        </v-flex>
      </v-layout>
    </v-container>
<PortfolioWrite></PortfolioWrite>
<Footer></Footer>
  </div>
</template>

<script>
import ImgBanner from '../components/ImgBanner'
import PortfolioList from '../components/PortfolioList'
import PortfolioWrite from '../components/PortfolioWrite'
import PortfolioListVue from '../components/PortfolioList.vue'
import Header from '../components/Header'
import Footer from '../components/Footer'

export default {
  name: 'PortfolioPage',
  props:{
    cnt:PortfolioList.limits,
    loadmore:PortfolioList.loadmore
  },
	components: {
		ImgBanner,
    PortfolioList,
    PortfolioWrite,
    Header,
    Footer
    }
  }
</script>
