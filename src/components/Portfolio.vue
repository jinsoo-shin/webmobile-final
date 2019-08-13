<style>
#portfolio_title{
  word-break:break-all;
  white-space: nowrap;
  overflow: hidden;
  width: 40vw;
  text-overflow: ellipsis;
 }
 #portfolio_sub{
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
    <v-img :src="img" height="200px"></v-img>
    <v-card-title primary-title>
      <div class="text-truncate">
        <div class="headline" id="portfolio_title">{{title}}</div>
        <span class="grey--text" id="portfolio_sub">{{content}}</span>

        <v-dialog v-model="dialog" max-width="800px">
          <v-card class="px-3 py-3">
            <v-icon style="position:absolute; right:0px;" flat @click="dialog = false">close</v-icon>
            <v-layout wrap>
              <v-flex xs12 md5 lg5>
              <p>{{create_at}}</p>
                <v-img :src="img" width="100%" height="50px + 5vw"></v-img>
              </v-flex>
              <v-flex xs12 md7 lg7 class="px-4 py-1">
                <h2>{{title}}</h2><br>
                <v-textarea v-model="content" v-if="flag" full-width height="160px" no-resize readonly></v-textarea>
                <v-textarea v-model="editcontent" v-if="!flag" full-width height="160px" no-resize></v-textarea>
                  작성자 : {{author}}
                <div v-if="chkauthor">
                  <v-btn outline @click="deletePortfolio()" color="warning" style="float:right">삭제</v-btn>
                  <v-btn outline @click="onclickupdatebtn()" v-if="flag" color="primary" style="float:right">수정</v-btn>
                  <v-btn outline @click="updatePortfolio(doc, title, editcontent, img, author)" v-if="!flag" color="primary" style="float:right">
                    <v-icon size="25" class="mr-2">done</v-icon>수정완료</v-btn>
                </div>
              </v-flex>
            </v-layout>
            <br>
            <PortfolioComment :bno="bno" :dialog="dialog"></PortfolioComment>
          </v-card>
        </v-dialog>

      </div>
    </v-card-title>
  </v-card>
</template>
                            
<script>
const url = 'http://52.78.157.214:8000'
import Vue from 'vue'
import FirebaseService from '@/services/FirebaseService'
import PortfolioComment from './PortfolioComment.vue'

export default {
	name: 'Portfolio',
  components:{
    PortfolioComment
  },
  data () {
      return {
        dialog: false,
        editcontent: '',
        flag: true,
        name: '',
        comments: [],
        commentcontent: ''
      }
    },
	props: {
    bno: {type:Number},
    author: {type: String},
		content: {type: String},
		create_at: {type: String},
    img: {type: String},
		title: {type: String},
    email: {type: String}
  },
  methods: {
    async deletePortfolio(){
      await this.$axios.post(
          url+'/api/portfolios/delete/'+this.bno)
			.then(response => {
				this.dialog = false
        location.reload(true)
			})
		},
    async updatePortfolio(){
      var data = {
        bno: this.bno,
        author: this.author,
        email: this.email,
        content: this.editcontent,
        title: this.title,
        img: this.img
      }
      await this.$axios.post(
          url+'/api/portfolios/update', data)
			.then(response => {
				this.dialog = false
        location.reload(true)
			})
		},
    onclickupdatebtn(){
      this.editcontent = this.content;
      this.flag = false;
    },
    async getComments() {
			await this.$axios.post(
          url+'/api/portcomment/getAll/'+this.bno)
			.then(response => {
				this.comments = response.data
			})
		},
    async deleteComment(item) {
      console.log(this.comments.indexOf(item))
			await this.$axios.post(
          url+'/api/portcomment/delete/'+item.cno)
			.then(
        this.comments.splice(this.comments.indexOf(item),1)
      )
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

