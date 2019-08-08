<template>
<div>
    <v-form v-model="valid">
    <v-container grid-list-xl>
      <v-layout wrap>
        <v-flex xs2 md1>
          <h3 class="pl-2 pt-3" >{{username}}</h3>
        </v-flex>
        <v-flex xs7 md9>
          <v-text-field
            v-model="content"
            label="Comment Content"
            required
          ></v-text-field>
        </v-flex>
        <v-btn @click="writeComment()" class="warning" style="float:right">댓글작성</v-btn>
      </v-layout>
    </v-container>
  </v-form>

  <v-layout row>
    <v-flex xs12 >
      <v-card>
        <v-toolbar color="white">
          <v-toolbar-title>Comment</v-toolbar-title>
          <v-spacer></v-spacer>
        </v-toolbar>
        <v-list three-line>
          <template v-for="(item, index) in chkdialog">
            <v-divider v-if="item.divider" :key="index" :inset="item.inset"></v-divider>
            <v-list-tile :key="item.title" avatar>
              <v-list-tile-content>
                <v-list-tile-title>{{item.create_at}}</v-list-tile-title>
                <v-list-tile-sub-title v-if="flag">{{item.author}} - {{item.content}}</v-list-tile-sub-title>
                <v-textarea v-model="editcomment" v-if="!flag" full-width height="160px"></v-textarea>

                <span>
                  <v-btn @click="onclickupdatebtn(item.content)" v-if="flag" class="primary" style="float:right">수정</v-btn>
                  <v-btn @click="updateComment(item)" v-if="!flag" class="primary" style="float:right">
                    <v-icon size="25" class="mr-2">done</v-icon>수정완료</v-btn>
                  <v-btn @click="deleteComment(item)" class="warning" style="float:right">삭제</v-btn>
                </span>
              </v-list-tile-content>
            </v-list-tile>
            <br>
          </template>
        </v-list>
      </v-card>
    </v-flex>
  </v-layout>
  </div>
</template>

<script>
import FirebaseService from '@/services/FirebaseService'
import store from '../store.js'
import Post from '../components/Post'

export default {
    name: "Comment",
    data: () => ({
      valid: false,
      flag: true,
      content: '',
      username: sessionStorage.getItem('name'),
      comments: [],
      editcomment:''
    }),
    props: {
      bno:{type:Number},
      dialog:{type:Boolean}
    },
    computed:{
      chkdialog:function(){
      if(this.dialog){
      this.getComments();
      }
        return this.comments;
      }
    },
    mounted(){
      console.log(this.editcomment)
    },
    methods: {
      async writeComment() {
        var data = {
            bno: this.bno,
            author: this.username,
            content: this.content
          }
        await this.$axios.post(
            'https://192.168.100.90:8000/api/postcomment/insert', data)
        .then(response => {
          this.content=''
          FirebaseService.sendCommentPush("Post")
        })
      },
      async getComments() {
        await this.$axios.post(
            'https://192.168.100.90:8000/api/postcomment/getAll/'+this.bno)
        .then(response => {
          this.comments = response.data
        })
      },
      onclickupdatebtn(content){
        this.editcomment = content
        console.log(this.editcontent)
        this.flag = false
      },
      async updateComment(item) {
        var data = {
            bno: this.bno,
            cno: item.cno,
            author: this.username,
            content: this.editcomment
          }
        await this.$axios.post(
            'https://192.168.100.90:8000/api/postcomment/update', data)
        .then(response => { })
      },
      async deleteComment(item) {
        await this.$axios.post(
            'https://192.168.100.90:8000/api/postcomment/delete/'+item.cno)
        .then(
          this.comments.splice(this.comments.indexOf(item),1)
        )
      }
    }
  }
</script>