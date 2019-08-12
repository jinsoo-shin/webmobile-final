<template>
  <div>
    <v-img :src="imgSrc"
           aspect-ratio="1.7">

      <v-layout row justify-center>
        <v-dialog v-model="dialog" max-width="50vw">
          <template v-slot:activator="{ on }">
            <v-btn color="#FBC02D" v-if="loginChk" dark v-on="on" v-on:click="click_select" style="margin-top:20vw;">Image Change</v-btn>
          </template>
          <v-card>
            <v-card-title>Select Image</v-card-title>
            <v-divider></v-divider>
            <v-card-text >
              <v-layout row wrap justify-center>
                <v-flex xs12 md6 sm6 lg6 xl6 v-for="image in images" :key="image.id">
                  <div style="text-align:center;">
                    <img :id="image.link" v-on:click="select_img(image.link)"  class="img" :key="image.link" :src="image.link" :for="image.id">
                  </div>  
                </v-flex>
              </v-layout>
            </v-card-text>
            <v-divider></v-divider>
            <v-card-actions>
                <v-btn style="font-size:1.5vw;" color="blue darken-1" flat v-on:click="change_img" @click="dialog = false">Fix</v-btn>
                <v-btn style="font-size:1.5vw;" color="blue darken-1" flat @click="dialog = false">Close</v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-layout>
      <v-layout align-center justify-center row fill-height>
        <v-flex text-xs-center>
          <span class="text-shadow display-2 font-weight-light">
            <slot name="text"/>
          </span>
        </v-flex>
      </v-layout>
    </v-img>
  </div>
</template>

<script>
import LoginService from '@/services/LoginService'
const clientid = '0c352653059d518'
const randomImg = 'https://source.unsplash.com/random'
export default {
	name: 'ImgBanner',
	props: {
    text: {type: String},
  },
  data () {
      return {
        dialogm1: '',
        dialog: false,
        images: [],
        img: '',
        imgSrc: randomImg,
        selected: '' 
      }
  },
  computed:{
    loginChk:function(){
      if(sessionStorage.getItem('name')){
        return true;
      }
      else{
        return false;
      }
    }
  },
  mounted() {
    this.imgSrc = randomImg
  },
	methods: {
    async click_select() {
      let user_email=sessionStorage.getItem('email')
      if(user_email) {
        let member =  await LoginService.getMember(user_email);
        let album='';
        if(member) {
          album = member.album;
        } else {
          album = 'X99Xe9d';
        }
        let link = 'https://api.imgur.com/3/album/' + album + '/images';
        this.$axios({
                    method: 'get',
                    url: link,
                    headers: { 'authorization': 'Client-ID ' + clientid }
                    }).then(response => {
                      this.images = response.data.data;
                    }).catch(function(error) {
                      this.imgSrc = randomImg;
                      console.log(error);
                    });
      }
    },
    change_img() {
      this.imgSrc = this.img
      if(this.imgSrc==="")
      {
        this.imgSrc = "https://source.unsplash.com/random"
      }
    },
    select_img(link) {
      if(this.selected != '') {
        document.getElementById(this.selected).style.border = 'none'
      }
      document.getElementById(link).style.border = '1px solid black'
      this.selected = link
      this.img = link
    }
	},
}
</script>
<style>
.text-shadow {
  text-shadow: 0 0 15px rgb(255,255,255);
}
</style>
