<template>
<div>
    <v-form v-model="valid">
    <v-container grid-list-xl>
      <v-layout wrap row>
        <v-flex hidden-xs-only md5></v-flex>
        <v-flex xs3 md2 align-self-center style="text-align:center;">
          <h3>{{username}}</h3>
        </v-flex>
        <v-flex xs6 md4>
          <v-text-field
            v-model="content"
            label="Comment Content"
            required
          ></v-text-field>
        </v-flex>
        <v-flex xs3 md1 align-self-center>
        <i @click="writeComment()" class="material-icons" style="cursor:pointer; margin-right: 0; !important" >create</i>
        </v-flex>
      </v-layout>
    </v-container>
  </v-form>

  <v-layout row>
    <v-flex md8>
      <v-card flat>
        <v-list three-line>
              <v-textarea v-model="editcomment" v-if="!flag" full-width height="160px"></v-textarea>
          <template v-for="(item, index) in chkdialog">
            <v-divider v-if="item.divider" :key="index" :inset="item.inset"></v-divider>
            <v-list-tile  v-if="flag" :key="item.title">
              <v-list-tile-content>
                <v-list-tile-title >{{item.create_at}}</v-list-tile-title>
                <v-list-tile-sub-title>{{item.author}} - {{item.content}}</v-list-tile-sub-title>
              </v-list-tile-content>
            </v-list-tile>
          </template>
        </v-list>
      </v-card>
    </v-flex>
    <v-flex md4>
        <v-list three-line>
         <template v-for="(item, index) in chkdialog">
            <v-divider v-if="item.divider" :key="index" :inset="item.inset"></v-divider>
                <v-btn @click="updateComment(item, index)" :key="index" v-if="!flag && select[index]" class="primary" style="float:right">
                <v-icon size="25" class="mr-2">done</v-icon>수정완료</v-btn>
            <v-list-tile v-if="flag" :key="item.title">
                <span :key="item.title">
                  <v-btn @click="onclickupdatebtn(index)" v-if="flag" class="primary" style="float:right">수정</v-btn>
                  <v-btn @click="deleteComment(item)" v-if="flag" class="warning" style="float:right">삭제</v-btn>
                </span>
            </v-list-tile>
          </template>
        </v-list>

    </v-flex>
  </v-layout>
  </div>
</template>

<script>
import FirebaseService from '@/services/FirebaseService'
import store from '../store.js'
import Portfolio from '../components/Portfolio'

export default {
    name: "Comment",
    data: () => ({
      valid: false,
      flag: true,
      content: '',
      username: sessionStorage.getItem('name'),
      comments: [],
      editcomments:[],
      editcomment : '',
      select : []
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
    
    },
    methods: {
      async writeComment() {
        var data = {
            bno: this.bno,
            author: this.username,
            content: this.content
          }
        await this.$axios.post(
            'https://192.168.100.90:8000/api/portcomment/insert', data)
        .then(response => {
          FirebaseService.sendCommentPush("Portfolio")
          this.getComments();
          this.content=''
        })
      },
      async getComments() {
          await this.$axios.post(
              'https://192.168.100.90:8000/api/portcomment/getAll/'+this.bno)
          .then(response => {
            this.comments = response.data
            for(var i = 0; i < this.comments.length ;i++)
            {
              this.editcomments.push(this.comments[i].content);
              this.select.push(false);
            }
          })
        
      },
      onclickupdatebtn(index){
        this.editcomment = this.editcomments[index];
        this.flag = false
        this.select[index] = true;
      },
      async updateComment(item, index) {
        var data = {
            bno: this.bno,
            cno: item.cno,
            author: this.username,
            content: this.editcomment
          }
        await this.$axios.post(
          'https://192.168.100.90:8000/api/portcomment/update', data)
        .then(response => {
          this.getComments();
          this.flag = true;
          this.select[index] = false;
        })
      },
      async deleteComment(item) {
        await this.$axios.post(
            'https://192.168.100.90:8000/api/portcomment/delete/'+item.cno)
        .then(
          this.comments.splice(this.comments.indexOf(item),1)
        )
      }
    }
  }
</script>
