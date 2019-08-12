<template>
  <v-footer  height="auto">
    <v-card dark color="#FF6F61" class="flex" flat tile>
      <v-card-title class="">
        <v-layout hidden-xs-only>
          <v-flex sm4 offset-sm2>
            <v-icon dark small>favorite</v-icon>Team - 5G는 5조 <br> 
            <v-icon dark small>notifications</v-icon>Project - webmobile-final <br> 
            <v-icon dark small>group</v-icon>Members - 박성하, 신진수, 안현상, 이지선, 조성모 <br>
            <a href="https://lab.ssafy.com/Leejiseon/webmobile-final" target="_blank" style="color:rgb(043,063,107); font-weight:bold">
              <v-icon dark small color="rgb(043,063,107)">fa-gitlab</v-icon> &nbsp; Go to Project Gitlab page
            </a>
          </v-flex>
          <v-flex sm4>
            <v-layout align-center v-if="issupport === true">
              <v-flex sm4 offset-sm2>
                <v-img :src="imgSrc" aspect-ratio="1" max-height="100px" min-height="30px" max-width="100px" min-width="30px"></v-img>
              </v-flex>
              <v-flex sm6>
                <v-icon dark small>place</v-icon>{{ locate }}<br>
                <v-icon dark small>wb_sunny</v-icon>{{ temperature }}℃<br>
                <v-icon dark small>wb_cloudy</v-icon>{{ sky.name }}
              </v-flex>
            </v-layout>
            <v-layout align-center v-else>
              <v-flex sm4 offset-sm2> 위치 정보를 얻을 수 없습니다 </v-flex>
            </v-layout>
          </v-flex>
        </v-layout>
        <v-layout hidden-sm-and-up column reverse>
          <v-flex xs12>
            <v-icon dark small>favorite</v-icon>Team - 5G는 5조 <br> 
            <v-icon dark small>notifications</v-icon>Project - webmobile-final <br> 
            <v-icon dark small>group</v-icon>Members - 박성하, 신진수, 안현상, 이지선, 조성모 <br>
            <a href="https://lab.ssafy.com/Leejiseon/webmobile-final" target="_blank" style="color:rgb(043,063,107); font-weight:bold">
              <v-icon dark small color="rgb(043,063,107)">fa-gitlab</v-icon> &nbsp; Go to Project Gitlab page
            </a>
          </v-flex>
          <v-flex xs12>
            <v-layout align-center  v-if="issupport === true">
              <v-flex xs4>
                <v-img :src="imgSrc" aspect-ratio="1" max-height="100px" min-height="30px" max-width="100px" min-width="30px"/>
              </v-flex>
              <v-flex xs6>
                <v-icon dark small>place</v-icon>{{ locate }}<br>
                <v-icon dark small>wb_sunny</v-icon>{{ temperature }}℃<br>
                <v-icon dark small>wb_cloudy</v-icon>{{ sky.name }}
              </v-flex>
            </v-layout>
            <v-layout align-center v-else>
              <v-flex sm4 offset-sm2>위치 정보를 얻을 수 없습니다</v-flex>
            </v-layout>
          </v-flex>
        </v-layout>
      </v-card-title>
      <v-card-actions class="grey darken-3 justify-center">
        &copy;2019 — <strong> SSAFY</strong>
      </v-card-actions>
    </v-card>
  </v-footer>
</template>
<script>
  export default {
	name: 'Footer',
  data () {
      return {
        issupport:false,
        lat:"",
        lon:"",
        locate:"",
        sky:{code:"",name:""},
        temperature:"",
        imgSrc:""
      }
  },
  mounted(){
    this.isLocation();
  },
	methods: {
    isLocation(){
      if(navigator.geolocation == undefined){
        alert("위치 정보 기능을 지원하지 않습니다!")
        this.issupport = false;
      }else{
        this.issupport = true;
        this.getLocation();
      }
    },
    getLocation(){
      navigator.geolocation.getCurrentPosition(position => {
        var tmpposition = position.coords;
        this.lat = tmpposition["latitude"];
        this.lon = tmpposition["longitude"];
        this.$axios.get(
          'https://apis.openapi.sk.com/weather/current/hourly?lat=' + this.lat + '&lon=' + this.lon + '&appkey=1942ce07-b6d7-4ee1-9e37-784283ae53f8'
          )
        .then(response => {
            var tmp = response.data.weather.hourly[0];
            this.locate = tmp.grid.city + " " + tmp.grid.county + " " + tmp.grid.village;
            this.sky.code = tmp.sky.code;
            this.sky.name = tmp.sky.name;
            this.temperature = tmp.temperature.tc;
            this.imgSrc = require("../assets/weather/" + this.sky.code + ".gif");
          });
      });
    }
  }
};
</script>
<style>
.text-shadow {
  text-shadow: 0 0 15px rgb(255, 255, 255);
}
</style>
