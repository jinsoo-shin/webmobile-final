<style>
#portfolio_title{
  word-break:break-all;
  white-space: nowrap;
  overflow: hidden;
  width: 40vw;
  text-overflow: ellipsis;
 }
 #portfolio_body{
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
  <v-card>
    <v-img :src="imgSrc" height="200px">
    </v-img>
    <v-card-title primary-title>
      <div class="text-truncate">
        <div class="headline" id="portfolio_title">{{title}}</div>
        <span class="grey--text" id="portfolio_body">{{body}}</span>
        <v-btn color="primary" dark @click.stop="dialog = true">자세히보기</v-btn>
        <v-dialog v-model="dialog" max-width="290">
          <v-card>
            <v-card-title class="headline">{{title}}</v-card-title>
            <v-card-text>{{body}}</v-card-text>

            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="red darken-1" dark outline text @click="dialog = false">
                Close
              </v-btn>
            </v-card-actions>
            
          </v-card>
      </v-dialog>
      </div>
    </v-card-title>
  </v-card>
</template>
<script>
export default {
	name: 'Portfolio',
  data () {
      return {
        dialog: false,
      }
    },
	props: {
		date: {type: String},
		title: {type: String},
		body: {type: String},
		imgSrc: {type: String},
	}
}
</script>
