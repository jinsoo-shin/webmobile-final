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
  <v-card @click="getComments" @click.stop="dialog = true" style="cursor : pointer" hover>
    <v-img :src="img" height="200px"></v-img>
    <v-card-title primary-title>
      <div class="text-truncate">
        <div class="headline" id="portfolio_title">{{title}}</div>
        <span class="grey--text" id="portfolio_sub">{{content}}</span>

        <v-dialog v-model="dialog" max-width="800px">
          <v-card class="px-3 py-3">
            <v-icon style="position:absolute; left:765px;" flat @click="dialog = false">close</v-icon>
            <v-layout wrap>
              <v-flex xs12 md5 lg5>
                <v-img :src="img" width="100%" height="50px + 5vw"></v-img>
              </v-flex>
              <v-flex xs12 md7 lg7 class="px-4 py-1">
                <h2>{{title}}</h2><br>
                <v-textarea v-model="content" v-if="flag" full-width height="160px" no-resize readonly></v-textarea>
                <v-textarea v-model="editcontent" v-if="!flag" full-width height="160px" no-resize></v-textarea>
                  작성자 : {{author}}
                <div v-if="chkauthor">
                  <v-btn @click="onclickeditbtn()" v-if="flag" class="primary" style="float:right">수정</v-btn>
                  <v-btn @click="editPortfolio(doc, title, editcontent, img, author)" v-if="!flag" class="primary" style="float:right">
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
                  <v-toolbar color="white">
                    <v-toolbar-title>Comment</v-toolbar-title>
                    <v-spacer></v-spacer>
                  </v-toolbar>

                  <v-list three-line>
                    <template v-for="(item, index) in comments">
                      <v-divider v-if="item.divider" :key="index" :inset="item.inset"</v-divider>
                      <v-list-tile v-if :key="item.title" avatar>
                 
                        <v-list-tile-content>
                          <v-list-tile-title>{{item.create_at}}</v-list-tile-title>
                          <v-list-tile-sub-title>{{item.author}} - {{item.content}}</v-list-tile-sub-title>
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
  components:{
    Comment
  },
  data () {
      return {
        dialog: false,
        editcontent: '',
        flag: true,
        name: '',
        comments: []
      }
    },
	props: {
    bno: {type:Number},
    author: {type: String},
		content: {type: String},
		create_at: {type: String},
    img: {type: String},
		title: {type: String}
  },
  methods: {
    async deletePortfolio(id){
      await FirebaseService.deletePortfolio(id)
      this.dialog = false
      location.reload(true)
    },
    onclickeditbtn(){
      this.editcontent = this.content;
      this.flag = false;
    },
    async editPortfolio(doc, title, content, img, author){
      await FirebaseService.editPortfolio(doc, title, content, img, author)
      this.dialog = false
      location.reload(true)
    },
    async getComments() {
			await this.$axios.post(
          'http://192.168.100.90:8000/api/portcomment/getAll/'+this.bno)
			.then(response => {
				this.comments = response.data
        console.log(this.comments)
			})
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

