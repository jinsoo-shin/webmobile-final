<template>
  <div>
    <v-layout row wrap>
      <v-flex md2 lg2>
        <v-card>
          <v-toolbar dark>
            <v-toolbar-side-icon></v-toolbar-side-icon>
            <v-toolbar-title>5G Admin</v-toolbar-title>
            <v-spacer></v-spacer>
            <v-btn icon>
              <v-icon>search</v-icon>
            </v-btn>
            <v-btn icon>
              <v-icon>check_circle</v-icon>
            </v-btn>
          </v-toolbar>
          <v-list two-line>
            <template v-for="(item, index) in itemsex">
              <v-list-tile :key="index" avatar ripple>
                <v-list-tile-content>
                  <v-list-tile-title>{{ item.title }}</v-list-tile-title>
                  <v-list-tile-sub-title class="text--primary">{{ item.headline }}</v-list-tile-sub-title>
                  <v-list-tile-sub-title>{{ item.subtitle }}</v-list-tile-sub-title>
                </v-list-tile-content>
                <v-list-tile-action>
                  <v-list-tile-action-text>{{ item.action }}</v-list-tile-action-text>
                  <v-icon color="grey lighten-1">star_border</v-icon>
                </v-list-tile-action>
              </v-list-tile>
              <v-divider v-if="index + 1 < items.length" :key="`divider-${index}`"></v-divider>
            </template>
          </v-list>
        </v-card>
      </v-flex>
      <v-flex md10 lg10>
            <v-toolbar dark>
            <!-- <router-link to ="/"><v-icon>home</v-icon></router-link> -->
            <v-btn flat icon v-on:click='go("home")'><v-icon>home</v-icon></v-btn>
            <v-toolbar-title style="font-family: 'Jua', sans-serif;">5G는5조</v-toolbar-title>
            <v-spacer></v-spacer>

            <div id="google_translate_element"></div> 

            <v-toolbar-items class="hidden-xs-only">
                <v-btn flat v-for="item in items" :key="item.title" v-on:click='go(item)'>{{item.title}}</v-btn>
            </v-toolbar-items>
                
            <div class="hidden-sm-and-up">
                <v-toolbar-side-icon dark @click.stop="drawer = !drawer"></v-toolbar-side-icon>
            </div>
            </v-toolbar>

            <div class="hidden-sm-and-up">
            <v-navigation-drawer v-model="drawer" fixed right hide-overlay disable-resize-watcher>
                <v-list class="pa-1">
                <v-list-tile avatar>
                    <v-list-tile-avatar>
                    <img src="../assets/mokuro.gif">
                    </v-list-tile-avatar>

                    <v-list-tile-content>
                    <v-list-tile-title style="font-family: 'Jua', sans-serif;">{{name}}</v-list-tile-title>
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
      </v-flex>
    </v-layout>
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
      name : "5G는5조",
      drawer: null,
      right: null,
      items: [
        { title: 'Portpolio', url: 'portfolio' },
        { title: 'Post', url: 'post' },
        { title: 'Login', url: 'login'},
        { title: 'QnA', url: '/'},
        { title: 'Admin', url: 'admin'}
      ],
      itemsex: [
          { action: '15 min', headline: 'Brunch this weekend?', title: 'Ali Connors', subtitle: "I'll be in your neighborhood doing errands this weekend. Do you want to hang out?" },
          { action: '2 hr', headline: 'Summer BBQ', title: 'me, Scrott, Jennifer', subtitle: "Wish I could come, but I'm out of town this weekend." },
          { action: '6 hr', headline: 'Oui oui', title: 'Sandra Adams', subtitle: 'Do you have Paris recommendations? Have you ever been?' },
          { action: '12 hr', headline: 'Birthday gift', title: 'Trevor Hansen', subtitle: 'Have any ideas about what we should get Heidi for her birthday?' },
          { action: '18hr', headline: 'Recipe to try', title: 'Britta Holt', subtitle: 'We should eat this: Grate, Squash, Corn, and tomatillo Tacos.' }
        ],
      login: false
    }
  },
  created(){
    Eventbus.$on('getUserEmail',getEmail=>{
      this.login=true;
      sessionStorage.setItem('email',getEmail)
      this.email=getEmail;
      this.name=sessionStorage.getItem('name');
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
        sessionStorage.removeItem('name');
        sessionStorage.removeItem('rank');
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
          this.name="5G는5조";
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
.fixed {
    position: fixed;
    top:0; left:0;
    width: 100%; }
</style>