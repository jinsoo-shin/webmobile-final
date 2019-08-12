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
      <div class="mb-2 caption">{{create_at}}</div>
      <h2 class="mb-2 color-333 headline " id="post_title">{{title}}</h2>
      <p class="mb-2 color-666 font-weight-light subheading" id="post_sub">{{content}}</p>

      <v-dialog v-model="dialog" max-width="800px">
        <v-card class="px-3 py-3">
          <v-icon style="float:right" large flat @click="dialog = false"> close</v-icon>
          <v-layout>
            <v-flex class="px-3 py-1">
              <h2 class="py-1">{{title}}</h2>
              <v-textarea v-model="content" v-if="flag" full-width height="250px" no-resize readonly></v-textarea>
              <v-textarea v-model="editcontent" v-if="!flag" full-width height="250px" no-resize></v-textarea>
                작성자 : {{author}} <br>
                작성일 : {{create_at}}
              <div v-if="chkauthor">
                <v-btn style="float:right" v-if="flag" @click="onclickeditbtn()" class="primary">수정</v-btn>
                <v-btn style="float:right" v-if="!flag" @click="updatePost(doc, title, editcontent, author)" class="primary">
                  <v-icon size="25" class="mr-2">done</v-icon>수정완료</v-btn>
                <v-btn style="float:right" @click="deletePost()" class="warning">삭제</v-btn>
              </div>
            </v-flex>
          </v-layout>
          <br>
          <PostComment :bno="bno" :dialog="dialog"></PostComment>
        </v-card>
      </v-dialog>

    </v-flex>
  </v-layout>
</template>

<script>
import FirebaseService from '@/services/FirebaseService'
import PostComment from './PostComment.vue'

export default {
  name: 'Post',
  components:{
    PostComment
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
    bno:{type:Number},
		create_at: {type: String},
		title: {type: String},
		content: {type: String},
    author: {type: String}
  },
  methods: {
    async deletePost(){
      await this.$axios.post(
          'http://192.168.100.90:8000/api/posts/delete/'+this.bno)
			.then(response => {
				this.dialog = false
        location.reload(true)
			})
		},
    onclickeditbtn(){
      this.editcontent = this.content
      this.flag = false
    },
    async updatePost(){
      var data = {
        bno: this.bno,
        author: this.author,
        email: this.email,
        content: this.editcontent,
        title: this.title
      }
      await this.$axios.post(
          'http://192.168.100.90:8000/api/posts/update', data)
			.then(response => {
				this.dialog = false
        location.reload(true)
			})
		},
    async getComments() {
			await this.$axios.post(
          'http://192.168.100.90:8000/api/postcomment/getAll/'+this.bno)
			.then(response => {
				this.comments = response.data
			})
		},
    async deleteComment(item) {
      console.log(this.comments.indexOf(item))
			await this.$axios.post(
          'http://192.168.100.90:8000/api/postcomment/delete/'+item.cno)
			.then(
        this.comments.splice(this.comments.indexOf(item),1)
      )
		}
  },
  computed: {
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

