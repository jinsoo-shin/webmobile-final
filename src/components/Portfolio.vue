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
  <v-card>
    <v-img :src="imgSrc" height="200px"></v-img>
    <v-card-title primary-title>
      <div class="text-truncate">
        <div class="headline" id="portfolio_title">{{title}}</div>
        <span class="grey--text" id="portfolio_body">{{body}}</span>
        <v-btn color="primary" dark @click.stop="dialog = true">자세히보기</v-btn>

        <v-dialog v-model="dialog" max-width="800">
          <v-card class="px-3 py-3">
            <v-icon style="float:right" large flat @click="dialog = false"> close</v-icon>
            <v-layout>
              <v-flex md5 lg5>
            <v-img :src="imgSrc" width="100%" height="300px"></v-img>
              </v-flex>
              <v-flex md5 lg5 class="px-3 py-1">
            <h2>{{title}}</h2><br>
            {{body}}<br>
            <v-btn class="primary">수정</v-btn>
            <v-btn class="warning">삭제</v-btn>
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
export default {
	name: 'Portfolio',
  data () {
      return {
        dialog: false,
      }
    },
	props: {
		date: {type: String},
		title: {type: String},
		body: {type: String},
		imgSrc: {type: String},
	}
}
</script>
