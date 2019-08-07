<template>
  <div>
    <v-layout row wrap>
      <v-flex xs2 md2 lg2>
        <v-card>
          <v-toolbar color="#fcbe03" dark>
            <img src="https://cdn2.iconfinder.com/data/icons/poke-ball-set-free/150/Poke_Ball-512.png" style="width:20px; height:20px"/>
            <v-toolbar-title>5G Admin</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
          <v-sheet tile dark height="90.5vh">
            
            <v-list subheader>
            <template v-for="(item, index) in drawitem">
              <v-list-tile :key="index" @click="">
                <v-list-tile-content @click="show(item.title)">
                  <v-list-tile-title><i class="material-icons">{{item.icon}}</i>{{ item.title }}</v-list-tile-title>
                </v-list-tile-content>
              </v-list-tile>
            </template>
          </v-list>

          </v-sheet>
          
        </v-card>
      </v-flex>
      <v-flex xs10 md10 lg10>
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
            <v-sheet tile white height="90.5vh" class="pa-3" style="text-align:center">
              <img v-if="!select" src="../../public/img/admin_page_wallpaper.jpg"
              style="z-index:-1; width:100%; height:auto;">
            
            <!--User-->
            <v-card v-if="Usershow">
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
                :headers="Userheaders"
                :items="Users"
                :search="search"
                >
                <template v-slot:items="props">
                    <td class="text-xs-left">{{ props.item.name }}</td>
                    <td class="text-xs-left">{{ props.item.email }}</td>
                    <td class="text-xs-left"><v-text-field type="number" max="3" min="1" v-model="props.item.ranks"></v-text-field></td>
                    <td class="text-xs-left">
                      <v-icon small class="mr-2" @click="editItem(props.item, data_title)">
                        edit
                      </v-icon>
                      <v-icon small @click="deleteItem(props.item, data_title)">
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

            <!--PF-->
            <v-card v-if="Portfolioshow">
                <v-card-title>
                <v-badge color="green">
                  <template v-slot:badge>
                    <span style="font-size:11px">{{length}}</span>
                  </template>
                  <span>{{data_title}}</span>
                </v-badge>
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
                :headers="Portfolioheaders"
                :items="Portfoilos"
                :search="search"
                >
                <template v-slot:items="props">
                    <td class="text-xs-left">{{ props.item.title }}</td>
                    <td class="text-xs-left">{{ props.item.content }}</td>
                    <td class="text-xs-left">{{ props.item.author }}</td>
                    <td class="text-xs-left">{{ props.item.create_at }}</td>
                    <td class="text-xs-left">
                      <v-icon small @click="deleteItem(props.item, data_title)">
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

            <!--PF-->
            <v-card v-if="Postshow">
                <v-card-title>
                <v-badge color="green">
                  <template v-slot:badge>
                    <span style="font-size:11px">{{length}}</span>
                  </template>
                  <span>{{data_title}}</span>
                </v-badge>
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
                :headers="Postheaders"
                :items="Posts"
                :search="search"
                >
                <template v-slot:items="props">
                    <td class="text-xs-left">{{ props.item.title }}</td>
                    <td class="text-xs-left">{{ props.item.content }}</td>
                    <td class="text-xs-left">{{ props.item.author }}</td>
                    <td class="text-xs-left">{{ props.item.create_at }}</td>
                    <td class="text-xs-left">
                      <v-icon small @click="deleteItem(props.item, data_title)">
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

              <div v-show="Etcshow" id="embed-api-auth-container"></div>
              <div  v-show="Etcshow" id="chart-container"></div>
              <div  v-show="Etcshow" id="view-selector-container"></div>

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
            data_title: '',
            select : false,
            data: [],
            Users:[],
            Portfoilos:[],
            Posts : [],
            Usershow : false,
            Portfolioshow : false,
            Postshow : false,
            Repositoryshow : false,
            Etcshow : false,
            length : 0,
            Userheaders: [
            {
              text: 'Name',
              align: 'left',
              sortable: false,
              value: 'name',
            },
            { text: 'E-mail', value: 'email' },
            { text: 'Rank', value: 'rank' },
            { text: '', value: 'name', sortable: false }
            ],
            Portfolioheaders: [
            {
              text: 'Title',
              align: 'left',
              sortable: false,
              value: 'title',
            },
            { text: 'Content', value: 'content' },
            { text: 'Author', value: 'author' },
            { text: 'Date', value: 'create_at' },
            { text: '', value: 'title', sortable: false }
            ],
            Postheaders: [
            {
              text: 'Title',
              align: 'left',
              sortable: false,
              value: 'title',
            },
            { text: 'Content', value: 'content' },
            { text: 'Author', value: 'author' },
            { text: 'Date', value: 'create_at' },
            { text: '', value: 'title', sortable: false }
            ],
      email:"5G는5조",
      name : "5G는5조",
      drawer: null,
      right: null,
      items: [
        { title: 'Portpolio', url: 'portfolio' },
        { title: 'Post', url: 'post' },
        { title: 'Repository', url: 'repository' },
        // { title: 'Login', url: 'login'},
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
    // Eventbus.$on('getUserEmail',getEmail=>{
    //   this.login=true;
    //   sessionStorage.setItem('email',getEmail)
    //   this.email=getEmail;
    //   this.name=sessionStorage.getItem('name');
    //   this.changeTitle();
    // });
    // FirebaseService.loginChk();
  },
	methods: {
      test(){
        gapi.analytics.ready(function() {

  /**
   * Authorize the user immediately if the user has already granted access.
   * If no access has been created, render an authorize button inside the
   * element with the ID "embed-api-auth-container".
   */
  gapi.analytics.auth.authorize({
    container: 'embed-api-auth-container',
    clientid: '527574403487-5rjveiqpfsptnn22qge8ohmkl3p3obmi.apps.googleusercontent.com'
  });


  /**
   * Create a new ViewSelector instance to be rendered inside of an
   * element with the id "view-selector-container".
   */
  var viewSelector = new gapi.analytics.ViewSelector({
    container: 'view-selector-container'
  });

  // Render the view selector to the page.
  viewSelector.execute();


  /**
   * Create a new DataChart instance with the given query parameters
   * and Google chart options. It will be rendered inside an element
   * with the id "chart-container".
   */
  var dataChart = new gapi.analytics.googleCharts.DataChart({
    query: {
      metrics: 'ga:sessions',
      dimensions: 'ga:date',
      'start-date': '30daysAgo',
      'end-date': 'yesterday'
    },
    chart: {
      container: 'chart-container',
      type: 'LINE',
      options: {
        width: '100%'
      }
    }
  });


  /**
   * Render the dataChart on the page whenever a new view is selected.
   */
  viewSelector.on('change', function(ids) {
    dataChart.set({query: {ids: ids}}).execute();
  });

});
      },
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
      show(str){
          this.data_title = str;
          this.select = true;

          this.Usershow = false;
          this.Portfolioshow = false;
          this.Postshow = false;
          this.Repositoryshow = false;
          this.Etcshow = false;

          if(str === "USERS")
          {
            this.Usershow = true;
            this.$axios.post('https://192.168.100.90:8000/api/members/getAll')
                .then(response => {
                  this.Users = response.data;
                })
                .catch(error => {
                  console.log("failed")
                }); 
          }
          else if(str === "PORTFOLIOS")
          {
            this.Portfolioshow = true;
            this.$axios.post('https://192.168.100.90:8000/api/portfolios/getAll')
                .then(response => {
                  this.Portfoilos = response.data;
                  this.length = reponse.data.length;
                })
                .catch(error => {
                  console.log("failed")
                });
          }
          else if(str === "POSTS")
          {
            this.Postshow = true;
            this.$axios.post('https://192.168.100.90:8000/api/posts/getAll')
                .then(response => {
                  this.Posts = response.data;
                  this.length = reponse.data.length;
                })
                .catch(error => {
                  console.log("failed")
                });
          }
          else if(str === "REPOSITORY")
          {
            this.Repositoryshow = true;
          }
          else if(str === "ETC")
          {
            this.Etcshow = true;
            this.test();
          }
        },
        editItem(item, str){
          if(str === "USERS")
          {
            var data = {
            email: item.email,
            ranks: item.ranks
            };
            this.$axios.post('https://192.168.100.90:8000/api/members/update',data).then(response => {
              alert("수정 완료!")
            }).catch(error => {
              console.log(error)
            })
          }
        },
        deleteItem(item, str){
          if(str === "USERS")
          {
            var data =item.email;
            this.$axios.post('https://192.168.100.90:8000/api/members/delete/'+data).then(response => {
              this.$axios.post('https://192.168.100.90:8000/api/members/getAll')
                .then(response => {
                  this.Users = response.data;
                })
                .catch(error => {
                  console.log("failed")
                }); 
            }).catch(error => {
              console.log(error)
            })
          }
          else if(str === "PORTFOLIOS")
          {
            var data =item.bno;
            this.$axios.post('https://192.168.100.90:8000/api/portfolios/delete/'+data).then(response => {
              this.$axios.post('https://192.168.100.90:8000/api/portfolios/getAll')
                .then(response => {
                  this.Portfoilos = response.data;
                })
                .catch(error => {
                  console.log("failed")
                }); 
            }).catch(error => {
              console.log(error)
            })
          }
          else if(str === "POSTS")
          {
            var data =item.bno;
            this.$axios.post('https://192.168.100.90:8000/api/posts/delete/'+data).then(response => {
              this.$axios.post('https://192.168.100.90:8000/api/posts/getAll')
                .then(response => {
                  this.Posts = response.data;
                })
                .catch(error => {
                  console.log("failed")
                }); 
            }).catch(error => {
              console.log(error)
            })
          }
          else if(str === "REPOSITORY")
          {
          }
          else if(str === "ETC")
          {
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
    width: 100%; 
}
.material-icons{
  vertical-align: bottom;
  margin-right: 3px;
}

</style>