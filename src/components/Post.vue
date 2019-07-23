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
  <v-layout py-4 h-100>
    <v-flex row>
      <div class="caption">{{formatedDate}}</div>
      <h2 class="color-333 headline font-weight-light" id="post_title">{{title}}</h2>
      <p class="mb-1 color-666 font-weight-light subheading" id="post_sub">{{body}}</p>
    </v-flex>
  </v-layout>
</template>

<script>
export default {
	name: 'Post',
	props: {
		date: {type: Date},
		title: {type: String},
		body: {type: String}
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

