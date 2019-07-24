<template>
  <div id="app">
    <v-toolbar dark color="#FBC02D" fixed>
      <!-- <router-link to ="/"><v-icon>home</v-icon></router-link> -->
      <v-btn flat icon v-on:click='go("home")'><v-icon>home</v-icon></v-btn>
      <v-toolbar-title style="font-family: 'Jua', sans-serif;">5G는5조</v-toolbar-title>
      <v-spacer></v-spacer>

      <div id="google_translate_element"></div> 

      <v-toolbar-items class="hidden-xs-only">
        <v-btn flat v-for="item in items" :key="item.title" v-on:click='go(item)'>{{item.title}}</v-btn>
      </v-toolbar-items>
      <v-btn flat icon v-on:click="favorite"><v-icon color="yellow">bookmark</v-icon></v-btn>
          
      <div class="hidden-sm-and-up">
        <v-toolbar-side-icon dark @click.stop="drawer = !drawer"></v-toolbar-side-icon>
      </div>
    </v-toolbar>

    <div class="hidden-sm-and-up">
      <v-navigation-drawer v-model="drawer" fixed hide-overlay disable-resize-watcher>
        <v-list class="pa-1">
          <v-list-tile avatar>
            <v-list-tile-avatar>
              <img src="../assets/mokuro.gif">
            </v-list-tile-avatar>

            <v-list-tile-content>
              <v-list-tile-title style="font-family: 'Jua', sans-serif;">{{email}}</v-list-tile-title>
            </v-list-tile-content>
          </v-list-tile>
        </v-list>

        <v-list class="pt-0">
          <v-divider></v-divider>
          <v-list-tile
            v-for="item in items" :key="item.title">
            <v-list-tile-action>
              <v-icon> keyboard_arrow_right</v-icon>
            </v-list-tile-action>
            <v-list-tile-content>
              <v-btn flat style="color:black" v-on:click='go(item)'>{{ item.title }}</v-btn>
            </v-list-tile-content>
          </v-list-tile>
        </v-list>
      </v-navigation-drawer>
    </div>
  </div>
</template>

<script>
import FirebaseService from '@/services/FirebaseService'
import store from '../store.js'
import {Eventbus} from '../main.js'

export default {
  name: 'Header',
	props: {
		imgSrc: {type: String},
		text: {type: String}
  },
  data () {
    return {
      email:"5G는5조",
      drawer: null,
      right: null,
      items: [
        { title: 'Post', url: 'post' },
        { title: 'Portpolio', url: 'portfolio' },
        { title: 'Login', url: 'login'},
        { title: 'QnA', url: '/'}
      ],
      login: false
    }
  },
  created(){
    Eventbus.$on('getUserEmail',getEmail=>{
      this.login=true;
      sessionStorage.setItem('email',getEmail)
      this.email=getEmail;
      this.changeTitle();
    });
    FirebaseService.loginChk();
  },
	methods: {
      favorite:function(){
        var title = document.title;
        var url = location.href;
      
        if (window.sidebar && window.sidebar.addPanel) {
            window.sidebar.addPanel(title, url, "");
        } else if (window.opera && window.print) {
            var elem = document.createElement('a');
            elem.setAttribute('href', url);
            elem.setAttribute('title', title);
            elem.setAttribute('rel', 'sidebar');
            elem.click();
        }else if (document.all) { //msie
            window.external.AddFavorite(url, title);
        }else {
            alert("해당브라우저는 즐겨찾기 추가기능이 지원되지 않습니다.\n\nCtrl+D를 눌러 즐겨찾기에 추가해주세요.");
        }
      },
      logout(){
        FirebaseService.logOut();
        this.login=false;
        sessionStorage.removeItem('email');
        this.$swal("LOGOUT!", "Good Bye!", "success",{
          buttons: false,
                    timer: 2000,
                })
        setTimeout(() => {
          this.$router.push("/");
          this.drawer=null;
        }, 2000)
        this.changeTitle();
      },
      changeTitle(){
        if(this.login){
          this.items[2].title="Logout";
          this.items[2].url="/";
        }
        if(!this.login){
          this.items[2].title="Login";
          this.items[2].url="login";
          this.email="5G는5조";
        }
      },
      go(item){
        if(item=="home"){
          if(this.$router.currentRoute.path==("/")){
            window.location.href="/"
          }else{
            this.$router.push("/");
          }
        }else{
          if(item.title=="Logout"){
            this.logout();
          }else{
            if(this.$router.currentRoute.path==("/"+item.url)){
              window.location.href=item.url
            }else{
              this.$router.push(item.url);
            }
          }
        }
      }
    }
}
</script>
<style>
.text-shadow {
  text-shadow: 0 0 15px rgb(255,255,255);
}
.v-btn__content>a{
  color:white;
}
.v-list__tile>a{
  color:black;
}
.goog-te-combo{
  color:black;
}
.goog-te-banner-frame{
  display:none;
}
</style>
