<template>
  <div id="frogue-container" 
    class="position-right-bottom" 
    data-chatbot="d07e7a23-9840-4caf-a114-f4aed4d541ca" 
    data-user="wiahawm7@gmail.com" 
    data-init-key="value">
  </div>
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
  }
</script>


