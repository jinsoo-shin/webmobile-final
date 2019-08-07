<template>
<div id="wirtePage_div" v-if="chk">
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
    </div>
    <br class='fclear'>
    <router-link :to="{ name: 'post', params: {} }">
    <div class="text-xs-right">
    <v-btn class="v-btn theme--dark" color="rgb(123,142,169)" dark v-on:click="writePost()"><v-icon size="25" class="mr-2">edit</v-icon>글 작성</v-btn>
   </div>
    </router-link>
    <br class='fclear'> </v-container>
  </div>
</template>

<script>
import Post from '@/components/Post'
import FirebaseService from '@/services/FirebaseService'
import 'v-markdown-editor/dist/index.css'
import Vue from 'vue'
import Editor from 'v-markdown-editor'
import PostList from '../components/PostList'

Vue.use(Editor);

export default{
  name : "PostWrite",
   data() {
      return {
        title : "",
        content : "",
        dialog: false,
        rank: 0,
      }
   },
   components: {
      Post
   },
   computed: {
       chk(){
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
    async writePost() {
        if(this.title.length>20){
          alert("제목은 최대 20자까지 입력가능합니다.")
        }
        else{
          var data = {
              author: sessionStorage.getItem('name'),
              content: this.content,
              title: this.title,
              email: sessionStorage.getItem('email')
            }
          await this.$axios.post(
            'https://192.168.100.90:8000/api/posts/insert', data)
            .then(response => {
            location.reload(true)
          })
        }
      }
    }
    }
</script>