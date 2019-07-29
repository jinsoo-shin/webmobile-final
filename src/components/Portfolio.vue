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
            <v-textarea v-model="body" full-width height="160px" no-resize readonly></v-textarea>
            작성자 : {{author}}
            <v-btn class="primary">수정</v-btn>
            <v-btn @click="deletePortfolio(doc)" class="warning">삭제</v-btn>
              </v-flex>
            </v-layout>
            댓글란
          </v-card>
        </v-dialog>

      </div>
    </v-card-title>
  </v-card>
</template>
<script>
import FirebaseService from '@/services/FirebaseService'

export default {
	name: 'Portfolio',
  data () {
      return {
        dialog: false,
      }
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
    }
  }
}
</script>
