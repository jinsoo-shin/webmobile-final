<template>
      <div id="frogue-container" class="position-right-bottom"
      data-chatbot="a53ec1db-a3a5-4dfb-83b1-ce036e806906"
      data-user="사용자ID"
      data-init-key="value"
      ></div>
</template>
<script>
  (function(d, s, id){
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {return;}
    js = d.createElement(s); js.id = id;
    js.src = "https:\/\/danbee.ai/js/plugins/frogue-embed/frogue-embed.min.js";
    fjs.parentNode.insertBefore(js, fjs);
  }(document, 'script', 'frogue-embed'));
  export default {
	name: 'ChatBot',
  data () {
      return {

      }
  },
  mounted(){

    },
  methods: {
 
  }
};
</script>


