<template>
  <div>
    <v-layout row wrap>
      <v-flex md2 lg2>
        <v-card>
          <v-toolbar color="#fc4103" dark>
            <img src="https://cdn2.iconfinder.com/data/icons/poke-ball-set-free/150/Poke_Ball-512.png" style="width:20px; height:20px"/>
            <v-toolbar-title>5G Admin</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
          <v-sheet dark height="90vh">
            
            <v-list subheader>
            <template v-for="(item, index) in drawitem">
              <v-list-tile :key="index" @click="">
                <v-list-tile-content @click="tmp(item.title)">
                  <v-list-tile-title><i class="material-icons">{{item.icon}}</i>{{ item.title }}</v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
            </template>
          </v-list>

          </v-sheet>
          
        </v-card>
      </v-flex>
      <v-flex md10 lg10>
        <v-flex column>
            <v-toolbar>
            <!-- <router-link to ="/"><v-icon>home</v-icon></router-link> -->
            <v-btn flat icon v-on:click='go("home")'><v-icon>home</v-icon></v-btn>
            <v-toolbar-title style=""></v-toolbar-title>
            <v-spacer></v-spacer>
            <v-toolbar-items class="hidden-xs-only">
                <v-btn flat v-for="item in items" :key="item.title" v-on:click='go(item)'>{{item.title}}</v-btn>
            </v-toolbar-items>
                
            <div class="hidden-sm-and-up">
                <v-toolbar-side-icon dark @click.stop="drawer = !drawer"></v-toolbar-side-icon>
            </div>
            </v-toolbar>
            <v-sheet white height="90vh" class="pa-3">
              <img v-if="!select" src="../../public/img/admin_page_wallpaper.jpg" 
              style="z-index:-1; width:auto; height:100%;">
            
            <v-card v-if="select">
                <v-card-title>
                {{data_title}}
                <v-spacer></v-spacer>
                <v-text-field
                    v-model="search"
                    append-icon="search"
                    label="Search"
                    single-line
                    hide-details
                ></v-text-field>
                </v-card-title>
                <v-data-table
                :headers="headers"
                :items="desserts"
                :search="search"
                >
                <template v-slot:items="props">
                    <td>{{ props.item.name }}</td>
                    <td class="text-xs-right">{{ props.item.calories }}</td>
                    <td class="text-xs-right">{{ props.item.fat }}</td>
                    <td class="text-xs-right">{{ props.item.carbs }}</td>
                    <td class="text-xs-right">{{ props.item.protein }}</td>
                    <td class="text-xs-right">{{ props.item.iron }}</td>
                    <td class="text-xs-right">
                      <v-icon small class="mr-2" @click="editItem(props.item)">
                        edit
                      </v-icon>
                      <v-icon small @click="deleteItem(props.item)">
                        delete
                      </v-icon>
                    </td>
            
                </template>
                <template v-slot:no-results>
                    <v-alert :value="true" color="error" icon="warning">
                    Your search for "{{ search }}" found no results.
                    </v-alert>
                </template>
                </v-data-table>
            </v-card>
            </v-sheet>
            
        </v-flex>
<!-- 
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
                    <v-btn flat style="color:black; font-family: 'Jua', sans-serif;" v-on:click='go(item)'>{{ item.title }}</v-btn>
                    </v-list-tile-content>
                </v-list-tile>
                </v-list>
            </v-navigation-drawer>
            </div> -->
                <!-- 이 부분은 데이터를 나타내는 곳이다. -->
                
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
import FirebaseService from '@/services/FirebaseService'
import store from '../store.js'
import {Eventbus} from '../main.js'

export default {
  name: 'AdminHeader',
	props: {
		imgSrc: {type: String},
		text: {type: String}
  },
  data () {
    return {
      search: '',
            data: [],
            data_title: '',
            select : false,
            headers: [
          {
            text: 'Dessert (100g serving)',
            align: 'left',
            sortable: false,
            value: 'name',
          },
          { text: 'Calories', value: 'calories' },
          { text: 'Fat (g)', value: 'fat' },
          { text: 'Carbs (g)', value: 'carbs' },
          { text: 'Protein (g)', value: 'protein' },
          { text: 'Iron (%)', value: 'iron' },
          { text: 'Action', value: 'name', sortable: false }
        ],
        desserts: [
          {
            name: 'Frozen Yogurt',
            calories: 159,
            fat: 6.0,
            carbs: 24,
            protein: 4.0,
            iron: '1%',
          },
          {
            name: 'Ice cream sandwich',
            calories: 237,
            fat: 9.0,
            carbs: 37,
            protein: 4.3,
            iron: '1%',
          },
          {
            name: 'Eclair',
            calories: 262,
            fat: 16.0,
            carbs: 23,
            protein: 6.0,
            iron: '7%',
          },
          {
            name: 'Cupcake',
            calories: 305,
            fat: 3.7,
            carbs: 67,
            protein: 4.3,
            iron: '8%',
          },
          {
            name: 'Gingerbread',
            calories: 356,
            fat: 16.0,
            carbs: 49,
            protein: 3.9,
            iron: '16%',
          },
          {
            name: 'Jelly bean',
            calories: 375,
            fat: 0.0,
            carbs: 94,
            protein: 0.0,
            iron: '0%',
          },
          {
            name: 'Lollipop',
            calories: 392,
            fat: 0.2,
            carbs: 98,
            protein: 0,
            iron: '2%',
          },
          {
            name: 'Honeycomb',
            calories: 408,
            fat: 3.2,
            carbs: 87,
            protein: 6.5,
            iron: '45%',
          },
          {
            name: 'Donut',
            calories: 452,
            fat: 25.0,
            carbs: 51,
            protein: 4.9,
            iron: '22%',
          },
          {
            name: 'KitKat',
            calories: 518,
            fat: 26.0,
            carbs: 65,
            protein: 7,
            iron: '6%',
          },
        ],
      email:"5G는5조",
      name : "5G는5조",
      drawer: null,
      right: null,
      items: [
        { title: 'Portpolio', url: 'portfolio' },
        { title: 'Post', url: 'post' },
        { title: 'Repository', url: 'repository' },
        { title: 'Login', url: 'login'},
        { title: 'QnA', url: '/'}
      ],
      drawitem: [
          { icon : 'face', title: 'USERS'},
          { icon : 'art_track', title: 'PORTFOLIOS'},
          { icon : 'favorite', title: 'POSTS' },
          { icon : 'dns', title: 'REPOSITORY' },
          { icon : 'help', title: 'ETC'}
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
          this.items[3].title="Logout";
          this.items[3].url="/";
        }
        if(!this.login){
          this.items[3].title="Login";
          this.items[3].url="login";
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
      },
        tmp(str){
          this.data_title = str;
          this.select = true;
          if(str === "USERS")
          {
            this.$axios.post('http://192.168.100.90:8000/api/members/getAll')
                .then(response => {
                  alert("user 불러 옴")
                })
                .catch(error => {
                  console.log("failed")
                  console.log(error)
                });
          }
          else if(str === "PORTFOLIOS")
          {
            this.$axios.post('http://192.168.100.90:8000/api/portfolios/getAll')
                .then(response => {
                  alert("PORTFO 불러 옴")
                })
                .catch(error => {
                  console.log("failed")
                });
          }
          else if(str === "POSTS")
          {
            console.log(str)
          }
        },
        editItem(item){
          alert(item)
        },
        deleteItem(item){
          alert(item)
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
    width: 100%; 
}
.material-icons{
  vertical-align: text-bottom;
  margin-right: 3px;
}

</style>