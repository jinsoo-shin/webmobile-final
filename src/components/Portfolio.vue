<style>
#portfolio_title{
  word-break:break-all;
  white-space: nowrap;
  overflow: hidden;
  width: 40vw;
  text-overflow: ellipsis;
 }
 #portfolio_body{
   overflow: hidden;
   display: -webkit-box;
   -webkit-line-clamp: 3 ;
   -webkit-box-orient: vertical ;
   word-break:break-all;
 }
 #app{
   height: auto;
 }

</style>
<template>
  <v-card @click.stop="dialog = true" style="cursor : pointer">
    <v-img :src="imgSrc" height="200px"></v-img>
    <v-card-title primary-title>
      <div class="text-truncate">
        <div class="headline" id="portfolio_title">{{title}}</div>
        <span class="grey--text" id="portfolio_body">{{body}}</span>
        <v-dialog v-model="dialog" max-width="800px">
          <v-card class="px-3 py-3">
            <v-icon style="float:right" large flat @click="dialog = false"> close</v-icon>
            <v-layout>
              <v-flex md5 lg5>
                <v-img :src="imgSrc" width="100%" height="300px"></v-img>
              </v-flex>
              <v-flex md6 lg6 class="px-3 py-1">
                <h2>{{title}}</h2><br>
                <v-textarea v-model="body" v-if="flag" full-width height="160px" no-resize readonly></v-textarea>
                <v-textarea v-model="editbody" v-if="!flag" full-width height="160px" no-resize></v-textarea>
                작성자 : {{author}}
                <div v-if="chkauthor">
                  <v-btn @click="onclickeditbtn()" v-if="flag" class="primary">수정</v-btn>
                  <v-btn @click="editPortfolio(doc, title, editbody, imgSrc, author)" v-if="!flag" class="primary">수정완료</v-btn>
                  <v-btn @click="deletePortfolio(doc)" class="warning">삭제</v-btn>
                </div>
              </v-flex>
            </v-layout>
            댓글란
            <Comment></Comment>
          </v-card>
        </v-dialog>

      </div>
    </v-card-title>
  </v-card>
</template>
                            
<script>
import Vue from 'vue'
import VueDisqus from 'vue-disqus'
import FirebaseService from '@/services/FirebaseService'
import Comment from './Comment.vue'
export default {
	name: 'Portfolio',
  data () {
      return {
        dialog: false,
        editbody: '',
        flag: true,
        name: '',
      }
    },
  components: {
    Comment
  },
	props: {
    doc: '',
		date: {type: String},
		title: {type: String},
		body: {type: String},
    imgSrc: {type: String},
    author: {type: String}
	},
  methods: {
    async deletePortfolio(id){
      await FirebaseService.deletePortfolio(id)
      this.dialog = false
      location.reload(true)
    },
    onclickeditbtn(){
      this.editbody = this.body;
      this.flag = false;
    },
    async editPortfolio(doc, title, body, img, auth){
      await FirebaseService.editPortfolio(doc, title, body, img, auth)
      this.dialog = false
      location.reload(true)
    }
  },
  computed: {
    chkauthor(){
      this.name = sessionStorage.getItem('name')
      if(this.name==this.author){
        return true
      }else{
        return false
      }
    }
  },
  mounted() {
    console.log("Hello!")
    try {
        let disqus_config = function () {
            this.page.url = location.origin;
            this.page.identifier = location.pathname;
        };
        (function () {
            let d = document, s = d.createElement('script');
            s.src = 'https://happylovetkd.disqus.com/embed.js';
            s.setAttribute('data-timestamp', +new Date());
            (d.head || d.body).appendChild(s);
        })();
    } catch (e) {
        // some error
    }
  }
}
</script>

