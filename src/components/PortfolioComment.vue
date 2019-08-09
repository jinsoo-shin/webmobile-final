<template>
<div>
  <v-form v-model="valid" v-if="chklogin">
    <v-container grid-list-xl>
      <v-layout wrap row>
        <v-flex hidden-xs-only md5></v-flex>
        <v-flex xs3 md2 align-self-center style="text-align:center;">
          <h3>{{username}}</h3>
        </v-flex>
        <v-flex xs6 md4>
          <v-text-field
            :rules="[(v) => v.length <= 70 || 'Max 70 characters']"
            :counter="70"
            v-model="content"
            label="Comment Content"
            required
             v-on:keyup.enter="writeComment()"
          ></v-text-field>
        </v-flex>
        <v-flex xs3 md1 align-self-center>
        <v-icon @click="writeComment()" class="material-icons" style="cursor:pointer; margin-right: 0; !important" >create</v-icon>
        </v-flex>
      </v-layout>
    </v-container>
  </v-form>

  <v-layout row>
    <v-flex md10>
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
    <v-flex md2>
        <v-list three-line>
         <template v-for="(item, index) in chkdialog">
            <v-divider v-if="item.divider" :key="index" :inset="item.inset"></v-divider>
                <v-btn @click="updateComment(item, index)" :key="index" v-if="!flag && select[index]" class="primary" style="float:right">
                <v-icon size="25" class="mr-2">done</v-icon>수정완료</v-btn>
            <v-list-tile v-if="flag" :key="item.title">
                <span v-if="chkauthor(item)" :key="item.title">
                  <v-icon @click="deleteComment(item)" v-if="flag" size="25" class="mr-2" color="warning" style="float:right">delete</v-icon>
                  <v-icon @click="onclickupdatebtn(index)" v-if="flag" size="25" class="mr-2 ml-2" color="primary" style="float:right">create</v-icon>
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
    },
    chklogin(){
      if (sessionStorage.getItem('name') != null){
        return true;
      }
      else{
        return false;
      }
    },
    chkauthor(){
        return(item)=>{
          this.username = sessionStorage.getItem('name')
            if(this.username==item.author){
              return true
            }else{
              return false
            }
        };
    }
  },
  methods: {
    async writeComment() {
      if(this.content.length>70){
        alert("댓글은 최대 70자까지 입력가능합니다.")    
      }
      else{
        var data = {
            bno: this.bno,
            author: this.username,
            content: this.content
          }
        await this.$axios.post(
            'http://192.168.100.90:8000/api/portcomment/insert', data)
        .then(response => {
          FirebaseService.sendCommentPush("Portfolio")
          this.getComments();
          this.content=''
        })
      }
    },
    async getComments() {
        await this.$axios.post(
            'http://192.168.100.90:8000/api/portcomment/getAll/'+this.bno)
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
        'http://192.168.100.90:8000/api/portcomment/update', data)
      .then(response => {
        this.getComments();
        this.flag = true;
        this.select[index] = false;
      })
    },
    async deleteComment(item) {
      await this.$axios.post(
          'http://192.168.100.90:8000/api/portcomment/delete/'+item.cno)
      .then(
        this.comments.splice(this.comments.indexOf(item),1)
      )
    }
  }
}
</script>
