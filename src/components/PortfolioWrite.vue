<template>
  <div id="wirtePage_div" v-if="rankchk" >
    <v-container>
      <v-form>
        <v-layout>
          <v-flex xs12 md4>
            <v-text-field
              :rules="[(v) => v.length <= 20 || 'Max 20 characters']"
              v-model="title"
              :counter="20"
              label="Title"
              required
            ></v-text-field>
          </v-flex>
        </v-layout>
      </v-form>
      <div class="container">
        <markdown-editor v-model="content"></markdown-editor>
        <v-flex xs12 md6 align-center>
          <img :src="imageUrl" width="100%" v-if="imageUrl" class=""/>
        </v-flex>
      </div>
      <v-flex xs12 sm6 md6 class="text-xs-center text-sm-center text-md-center text-lg-center">
        <v-text-field label="Select Image" @click='pickFile' v-model='imageName' prepend-icon='attach_file'></v-text-field>
        <input
          type="file"
          style="display: none"
          ref="image"
          accept="image/*"
          @change="onFilePicked"
        >
        <v-btn outline color = "rgb(123,142,169)" class="v-btn theme--dark" dark v-on:click="random_unsplash()">random1</v-btn>
        <v-btn outline color = "rgb(123,142,169)" class="v-btn theme--dark" dark v-on:click="random_imgur()">random2</v-btn>
        <v-btn outline color = "rgb(123,142,169)" class="v-btn theme--dark" dark v-on:click="pull_imgur()">Imgur 가져오기</v-btn>
      </v-flex>

      <br class='fclear'>
      <router-link :to="{ name: 'portfolio', params: {} }">
        <div class="text-xs-right">
          <v-btn class="v-btn theme--dark" color="rgb(123,142,169)" dark v-on:click="writePortfolio">
            <v-icon size="25" class="mr-2">edit</v-icon>글 작성
          </v-btn>
        </div>
      </router-link>
      <br class='fclear'>
    </v-container>
  </div>
</template>

<script>
import Portfolio from '@/components/Portfolio'
import FirebaseService from '@/services/FirebaseService'
import 'v-markdown-editor/dist/index.css'
import Vue from 'vue'
import Editor from 'v-markdown-editor'
import PortfolioList from '../components/PortfolioList'

Vue.use(Editor);
var deletehash = "";
export default{
  name : "PortfolioWrite",
  data() {
    return {
      title : "",
      content : "",
      img : "https://source.unsplash.com/random",
      img_title: "Image Upload",
      dialog: false,
      imageName: '',
      imageUrl: '',
      imageFile: '',
      imgurUserName:'',
      rank: 0
    }
  },
  components: {
    Portfolio
  },
  computed: {
    rankchk(){
      this.rank = sessionStorage.getItem("rank");
      if ( this.rank !=0 && this.rank != 1 && this.rank != null){
        return true;
      }
      else{
        return false;
      }
    }
  },
  methods: {
    pickFile () {
      this.$refs.image.click ()
    },
    onFilePicked (e) {
      const files = e.target.files
      if(files[0] !== undefined) {
        this.imageName = files[0].name
        if(this.imageName.lastIndexOf('.') <= 0) {
            return
        }
        const fr = new FileReader ()
        fr.readAsDataURL(files[0])
        fr.addEventListener('load', () => {
            this.imageUrl = fr.result
            this.imageFile = files[0]
        })
      } else {
        this.imageName = ''
        this.imageFile = ''
        this.imageUrl = ''
      }
    },
    async writePortfolio() {
      if(this.title.length>20){
        alert("제목은 최대 20자까지 입력가능합니다.")    
      }
      else{
        var form = new FormData();
        var settings = {
          "url": "https://api.imgur.com/3/account/DigitCode0110/album/X99Xe9d",
          "method": "GET",
          "timeout": 0,
          "headers": {
            "Authorization": "Bearer f6e6c02501ce44431361c69f313e245bbf3a2800"
          },
          "processData": false,
          "mimeType": "multipart/form-data",
          "contentType": false,
          "data": form
        };

        $.ajax(settings).done(function (response) {
          console.log(response);
        });

        let formData = new FormData();
        console.log(this.imageFile)
        formData.append("image", this.imageFile);
        const axios = require("axios");
        await axios({
        method: "POST",
        url: "https://api.imgur.com/3/image",
        data: formData,
        headers: {
          Authorization: "Client-ID b7b94ab18f1e6ec"
        },
        mimeType: "multipart/form-data"
        })
        .then(res => {
          deletehash = res.data.data.deletehash;
          this.imageUrl = res.data.data.link
          this.UploadAlbum();
          return res.data.data.link;
        })
        .catch(function() {});

        var data = {
          author: sessionStorage.getItem('name'),
          content: this.content,
          title: this.title,
          img: this.imageUrl,
          email: sessionStorage.getItem('email')
        }
        await this.$axios.post(
          'https://192.168.100.90:8000/api/portfolios/insert', data)
        .then(response => {
          location.reload(true)
        })
      }
    },
    async UploadAlbum() {
      let formData = new FormData();
      formData.append("deletehashes[]", deletehash);
      const axios = require("axios");
      await axios({
      method: "POST",
      url: "https://api.imgur.com/3/album/RGyjaTGNOzCAlkU/add",
      data: formData,
      headers: {
        Authorization: "Client-ID b7b94ab18f1e6ec"
      },
      mimeType: "multipart/form-data",
      processData: false,
      contentType: false
      })
      .then(function() {})
      .catch(function() {});
      },
    random_unsplash(){
      this.imageUrl = 'https://source.unsplash.com/random';
    },
    random_imgur(){
      this.$axios({
        method: 'get',
        url: 'https://api.imgur.com/3/gallery/random/random/',
        headers: { 'authorization': 'Client-ID ' + 'b7b94ab18f1e6ec' }
        }).then(response => {
        var rand = Math.floor(Math.random() * 60) + 1;
          console.log(response.data.data[rand]);
          if(response.data.data[rand].images != null)
          {
            this.imageUrl= response.data.data[rand].images[0].link; 
          }
          else{
            this.imageUrl= response.data.data[rand].link;
          }
          this.imageName= response.data.data[rand].title;
            console.log(this.imageUrl)
        }).catch(function(error) {
            console.log(error);
        }).finally(()=>{
        });
    },
    pull_imgur(){
      this.$axios({
        method: 'get',
        url: 'https://api.imgur.com/3/account/DigitCode0110/images/',
        headers: { 'authorization': 'Bearer ' + 'f6e6c02501ce44431361c69f313e245bbf3a2800' }
        }).then(response => {
          var len = response.data.data.length
          var rand = Math.floor(Math.random() * len-1);
          if(response.data != null){
            this.imageUrl= response.data.data[rand].link; 
          }
          else{
            alert("파일이 존재하지 않습니다!")
          }
          this.imageName= "From Imgur";
        }).catch(function(error) {
            console.log(error);
        }).finally(()=>{
        });
    }
   }
}
</script>