<template>
  <div>
    <Header>
    </Header>
    <ImgBanner imgSrc="https://source.unsplash.com/random">
      <div style="margin-bottom:20vw; line-height:1.2em; font-family: 'Fascinate', cursive; font-size:calc(30px + 2.5vw) " slot="text">CARPE DIEM</div>
    </ImgBanner>
    <v-container>
      <!-- About Me -->
      <div data-aos="fade-up-right" data-aos-duration="1500">
        <v-tabs v-model="model" centered dark color="rgb(043,063,107)" slider-color="yellow" show-arrows>
          <v-tab v-for="item in items" :key="item.num" :href="`#tab-${item.num}`">
            {{ item.name }}
          </v-tab>
        </v-tabs>
        <v-tabs-items v-model="model">
          <v-tab-item v-for="item in items" :key="item.num" :value="`tab-${item.num}`">
            <v-card flat style="background-color: #fafafa;">
              <v-card-text>
                <v-layout>
                <v-flex xs12 sm8>
                  <h2 class="headline text-xs-center text-sm-left mb-3" v-text="item.title"></h2>
                  <p class="mr-4 text-xs-left text-sm-left" v-html="item.text" align-content-center></p>
                </v-flex>
                <v-flex sm4 id="aboutmeimg">
                  <v-img :src="getImgUrl(item.image)" aspect-ratio="1"/>
                </v-flex>
                </v-layout>
              </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </div>

      <!-- Portfolio -->
      <v-layout my-5>
        <v-flex xs12>
          <v-flex text-xs-center>
            <v-btn flat to="portfolio"><h2 class="headline my-5 text-xs-center">Portfolio</h2><v-icon large class="mr-2">chevron_right</v-icon></v-btn>
          </v-flex>
          <PortfolioList></PortfolioList>
        </v-flex>
      </v-layout>

      <!-- Post -->
      <v-layout my-5>
        <v-flex xs12>
          <v-flex text-xs-center>
            <v-btn flat to="post"><h2 class="headline my-5 text-xs-center">Post</h2><v-icon large class="mr-2">chevron_right</v-icon></v-btn>
          </v-flex>
          <PostList :column="2"></PostList>
        </v-flex>
      </v-layout>
    </v-container>
    <Footer></Footer>
  </div>
</template>

<script>
import ImgBanner from '../components/ImgBanner'
import PortfolioList from '../components/PortfolioList'
import PostList from '../components/PostList'
import RepositoryList from '../components/RepositoryList'
import Graph from '../components/Graph'
import Header from '../components/Header'
import Footer from '../components/Footer'
import test from '../components/test'

export default {
  name: 'HomePage',
	components: {
		ImgBanner,
		PortfolioList,
		PostList,
    RepositoryList,
    Graph,
    Header,
    Footer,
    test
	},
  data () {
      return {
        items:[
        {num:1,name:"5조",image:"pikachu.png",title:"About 5조",text:"안녕하세요. 5G는 5조입니다.<br> 프로젝트 기간 : 2019.07.08~2019.08.14 (7주)<br> 프로젝트 명 : webmobile-final <br><br>벌써 7주가 지나고, <br> 막막해보였던 프로젝트가 어느덧 마무리 되었습니다. <br>프로젝트 기간 동안 수고한 우리 팀원에게 박수를 보냅니다.<br> 짝짝짝~~"},
        {num:2,name:"박성하",image:"park.png",title:"Park Seong Ha",text:"프로젝트를 진행하면서 무엇보다 건강의 중요성을 알게된 시간이었습니다.<br> 몸이 아프니까 집중하기 힘들었습니다.<br> 주말에 푹 쉬고 다음주도 힘내겠습니다!!"},
        {num:3,name:"신진수",image:"mokuroh.png",title:"Shin Jin Soo",text:"안녕하세요.<br>익숙지 않던 SPA를 만들어보는 시간을 갖게 되어 재미있었습니다!!<br> 좋은 팀원을 만나서 좋았습니다~"},
        {num:4,name:"안현상",image:"ahn.png",title:"Ahn Hyun Sang",text:"많이 배우고 실력을 향상시킬 수 있었던 프로젝트였습니다.<br>좋은 조원들과 함께 프로젝트를 진행하여 즐겁게 할 수 있었습니다. <br> 7주동안 고생한 우리 조장과 조원들 모두 수고했습니다~"},
        {num:5,name:"이지선",image:"lee.png",title:"Lee Ji Seon",text:"아직 익숙하지 않아 어려웠지만, <br>조원들과 함께 해결해나가며 성장할 수 있었던 프로젝트였습니다. <br> 모두 함께 열심히 해준 5G는 5조 최고!!"},
        {num:6,name:"조성모",image:"cho.png",title:"Cho Seong Mo",text:"7주간 진행한 프로젝트로 이 정도 성과를 낼 수 있다니 대단하죠?<br>누구나 다 할수 있습니다."}
        ],
        model: 'tab-1'}
  },
	methods: {
		getImgUrl(img) {
			return require('../assets/' + img)
		}
  }
}


</script>
