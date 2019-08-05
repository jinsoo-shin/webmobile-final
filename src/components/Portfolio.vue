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
  <v-card @click.stop="dialog = true" style="cursor : pointer" hover>
    <v-img :src="imgSrc" height="200px"></v-img>
    <v-card-title primary-title>
      <div class="text-truncate">
        <div class="headline" id="portfolio_title">{{title}}</div>
        <span class="grey--text" id="portfolio_body">{{body}}</span>
        <v-dialog v-model="dialog" max-width="800px">
          <v-card class="px-3 py-3">
            <v-icon style="position:absolute; left:765px;" flat @click="dialog = false">close</v-icon>
            <v-layout wrap>
              <v-flex xs12 md5 lg5>
                <v-img :src="imgSrc" width="100%" height="50px + 5vw"></v-img>
              </v-flex>
              <v-flex xs12 md7 lg7 class="px-4 py-1">
                <h2>{{title}}</h2><br>
                <v-textarea v-model="body" v-if="flag" full-width height="160px" no-resize readonly></v-textarea>
                <v-textarea v-model="editbody" v-if="!flag" full-width height="160px" no-resize></v-textarea>
                  작성자 : {{author}}
                <div v-if="chkauthor">
                  <v-btn @click="onclickeditbtn()" v-if="flag" class="primary" style="float:right">수정</v-btn>
                  <v-btn @click="editPortfolio(doc, title, editbody, imgSrc, author)" v-if="!flag" class="primary" style="float:right">
                    <v-icon size="25" class="mr-2">done</v-icon>수정완료</v-btn>
                  <v-btn @click="deletePortfolio(doc)" class="warning" style="float:right">삭제</v-btn>
                </div>
              </v-flex>
            </v-layout>
            <br>
            <Comment></Comment>

            <v-layout row>
              <v-flex xs12 >
                <v-card>
                  <v-toolbar color="cyan" dark>
                    <v-toolbar-title>Comment</v-toolbar-title>
                    <v-spacer></v-spacer>
                    <v-btn icon>
                      <v-icon>search</v-icon>
                    </v-btn>
                  </v-toolbar>

                  <v-list three-line>
                    <template v-for="(item, index) in items">
                      <v-subheader
                        v-if="item.header"
                        :key="item.header"
                      >
                        {{ item.header }}
                      </v-subheader>

                      <v-divider
                        v-else-if="item.divider"
                        :key="index"
                        :inset="item.inset"
                      ></v-divider>

                      <v-list-tile
                        v-else
                        :key="item.title"
                        avatar
                        @click=""
                      >
                        <v-list-tile-avatar>
                          <img :src="item.avatar">
                        </v-list-tile-avatar>

                        <v-list-tile-content>
                          <v-list-tile-title v-html="item.title"></v-list-tile-title>
                          <v-list-tile-sub-title v-html="item.subtitle"></v-list-tile-sub-title>
                        </v-list-tile-content>
                      </v-list-tile>
                    </template>
                  </v-list>
                </v-card>
              </v-flex>
            </v-layout>
          </v-card>
        </v-dialog>

      </div>
    </v-card-title>
  </v-card>
</template>
                            
<script>
import Vue from 'vue'
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
        items: [
          { header: 'Today' },
          {
            avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
            title: '2019-08-05',
            subtitle: "<span class='text--primary'>lee</span> &mdash; 또 먹고싶다ㅠㅠ"
          },
          { divider: true, inset: true }
        ]
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
  }
}
</script>

