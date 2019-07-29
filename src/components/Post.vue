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
  <v-layout py-4 h-100 @click.stop="dialog = true">
    <v-flex row>
      <div class="caption">{{formatedDate}}</div>
      <h2 class="color-333 headline font-weight-light" id="post_title">{{title}}</h2>
      <p class="mb-1 color-666 font-weight-light subheading" id="post_sub">{{body}}</p>

      <v-dialog v-model="dialog" max-width="800px">
          <v-card class="px-3 py-3">
            <v-icon style="float:right" large flat @click="dialog = false"> close</v-icon>
            <v-layout>
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

    </v-flex>
  </v-layout>
</template>

<script>
export default {
	name: 'Post',
  data () {
      return {
        dialog: false,
      }
    },
	props: {
		date: {type: Date},
		title: {type: String},
		body: {type: String},
    author: {type: String}
	},
  computed: {
		formatedDate() {
			return `${this.date.getFullYear()}년 ${this.date.getMonth()}월 ${this.date.getDate()}일`
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

