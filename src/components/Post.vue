<style>
#post_title{
    white-space: nowrap;
   overflow: hidden;
   width: 100%;
   text-overflow: ellipsis;
 }

#post_sub{
 overflow: hidden;
   display: -webkit-box;
   width: 100%;
   word-wrap: break-word;
   -webkit-line-clamp: 3;
   -webkit-box-orient: vertical;
 }
</style>

<template>
  <v-layout px-4 py-4 h-100 @click.stop="dialog = true">
    <v-flex row>
      <div class="mb-2 caption">{{formatedDate}}</div>
      <h2 class="mb-2 color-333 headline font-weight-light" id="post_title">{{title}}</h2>
      <p class="mb-2 color-666 font-weight-light subheading" id="post_sub">{{body}}</p>

      <v-dialog v-model="dialog" max-width="800px">
        <v-card class="px-3 py-3">
          <v-icon style="float:right" large flat @click="dialog = false"> close</v-icon>
          <v-layout>
            <v-flex class="px-3 py-1">
              <h2>{{title}}</h2>
              <v-textarea v-model="body" v-if="flag" full-width height="160px" no-resize readonly></v-textarea>
              <v-textarea v-model="editbody" v-if="!flag" full-width height="160px" no-resize></v-textarea>
              작성자 : {{author}} <br>
              작성일 : {{formatedDate}}
              <div v-if="chkauthor">
                <v-btn style="float:right" v-if="flag" @click="onclickeditbtn()" class="primary">수정</v-btn>
                <v-btn style="float:right" v-if="!flag" @click="editPost(doc, title, editbody)" class="primary">수정완료</v-btn>
                <v-btn style="float:right" @click="deletePost(doc)" class="warning">삭제</v-btn>
              </div>
            </v-flex>
          </v-layout>
          댓글란
        </v-card>
      </v-dialog>

    </v-flex>
  </v-layout>
</template>

<script>
import FirebaseService from '@/services/FirebaseService'

export default {
	name: 'Post',
  data () {
      return {
        dialog: false,
        editbody: '',
        flag: true,
        name: '',
      }
    },
	props: {
    doc: '',
		date: {type: Date},
		title: {type: String},
		body: {type: String},
    author: {type: String}
  },
  methods: {
    async deletePost(id){
      await FirebaseService.deletePost(id)
      this.dialog = false
      location.reload(true)
    },
    onclickeditbtn(){
      this.editbody = this.body;
      this.flag = false;
    },
    editPost(doc, title, body){
      FirebaseService.editPost(doc, title, body)
      this.dialog = false
      location.reload(true)
    }
  },
  computed: {
		formatedDate() {
			return `${this.date.getFullYear()}년 ${this.date.getMonth()}월 ${this.date.getDate()}일`
    },
    chkauthor(){
      this.name = sessionStorage.getItem("name");
      if ( this.name == this.author){
        return true
      }
      else{
        return false;
      }
    }
  }
}
</script>
<style>
  .color-666 {
    color: #666;
  }
  .color-333 {
    color: #333;
  }
  .h-100 {
    height: 100%;
  }
</style>

