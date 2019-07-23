<template>
    <button type ="button" v-if = "isVisible" @click = "$vuetify.goTo(0)" class = "v-btn v-btn--bottom v-btn--floating v-btn--fixed v-btn--right theme--dark" style = "background-color:rgba(043,063,107,0.7);" data-v-10ea4164 = "">
    <div class="v-btn__content">
    <i aria-hidden = "true" class = "v-icon material-icons theme--dark"> keyboard_arrow_up </i>
    </div>
    </button>
</template>

<style>
.v-btn:before {
  border-radius: inherit;
  color: inherit;
  content: '';
  position: absolute;
  left: 0;
  top: 0;
  height: 100%;
  opacity: 0.12;
  transition: 0.3s cubic-bezier(0.25, 0.8, 0.5, 1);
  width: 100%;
}

.isVisible {
  opacity: .8;
  z-index: 4;
  transition: all .4s ease-in;
}
</style>
<script>
export default {
  name: 'BacktoTop',
  data: function() {
    return {
      isVisible: false
    };
  },
  mounted(){
    window.addEventListener('scroll', this.showBtn);
  },
  methods : {
    showBtn(){
      if(window.scrollY >= 50) {
        this.isVisible = true;
      }
      else {
        this.isVisible = false;
      }
    }
  }
}
</script>
