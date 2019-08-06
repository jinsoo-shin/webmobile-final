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
            v-model="commentcontent"
            label="Comment Content"
            required
          ></v-text-field>
        </v-flex>
        <v-btn @click="writeComment()" class="warning" style="float:right">댓글작성</v-btn>
        <v-icon xs1 md2 color="primary">edit</v-icon>
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
            <v-divider v-if="item.divider" :key="index" :inset="item.inset"</v-divider>
            <v-list-tile v-if :key="item.title" avatar>
        
              <v-list-tile-content>
                <v-list-tile-title>{{item.create_at}}</v-list-tile-title>
                <v-list-tile-sub-title>{{item.author}} - {{item.content}}</v-list-tile-sub-title>
                <v-btn @click="deleteComment(item)" class="warning" style="float:right">삭제</v-btn>
              </v-list-tile-content>
            </v-list-tile>
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
import Portfolio from '../components/Portfolio'

export default {
    name: "Comment",
    data: () => ({
      valid: false,
      commentcontent: '',
      username: sessionStorage.getItem('name'),
      comments: []
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
    methods: {
      async writeComment() {
        var data = {
            bno: this.bno,
            author: this.username,
            content: this.commentcontent
          }
        await this.$axios.post(
            'http://192.168.100.90:8000/api/portcomment/insert', data)
        .then(response => { })
      },
      async getComments() {
        await this.$axios.post(
            'http://192.168.100.90:8000/api/portcomment/getAll/'+this.bno)
        .then(response => {
          this.comments = response.data
        })
      },
      async deleteComment(item) {
        console.log(this.comments.indexOf(item))
        await this.$axios.post(
            'http://192.168.100.90:8000/api/portcomment/delete/'+item.cno)
        .then(
          this.comments.splice(this.comments.indexOf(item),1)
        )
      }
    }
  }
</script>