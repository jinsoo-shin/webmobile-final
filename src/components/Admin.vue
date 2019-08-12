<template>
  <div>
    <v-layout row wrap>
      <v-flex xs2 md2 lg2>
        <v-card>
          <v-toolbar color="#fcbe03" dark>
            <img src="https://cdn2.iconfinder.com/data/icons/poke-ball-set-free/150/Poke_Ball-512.png" style="width:20px; height:20px"/>
            <v-toolbar-title style="cursor:pointer">5G Admin</v-toolbar-title>
            <v-spacer></v-spacer>
          </v-toolbar>
          <v-sheet tile dark height="91.2vh">
            
            <v-list subheader>
            <template v-for="(item, index) in drawitem">
              <v-list-tile :key="index">
                <v-list-tile-content @click="show(item.title)">
                  <v-list-tile-title style="cursor:pointer"><i class="material-icons">{{item.icon}}</i>{{ item.title }}</v-list-tile-title>
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
            <v-sheet tile white height="91.2vh" class="pa-3" style="text-align:center">
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
                <v-data-table dense
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
              <div v-show="false" id="embed-api-auth-container"></div>
              <div  v-show="Etcshow" id="chart-container"></div>
              <div  v-show="Etcshow" id="view-selector-container"></div>

            </v-sheet>
            
        </v-flex>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
import store from '../store.js'
import {Eventbus} from '../main.js'
var accessToken=""
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
   
  },
  mounted(){
    if(sessionStorage.getItem("rank") != 3)
    {
      alert("관리자만 접근할 수 있습니다.")
      this.$router.push('/');
    }
  },
	methods: {
      async test(){
        
        await this.getToken();
        gapi.analytics.ready(async function() {
        gapi.analytics.auth.authorize({
          container: 'embed-api-auth-container',
          clientid: '527574403487-5rjveiqpfsptnn22qge8ohmkl3p3obmi.apps.googleusercontent.com',
          serverAuth: {
            'access_token': accessToken
           }
        });

        var viewSelector = new gapi.analytics.ViewSelector({
          container: 'view-selector-container'
        });
        viewSelector.execute();

        var dataChart = new gapi.analytics.googleCharts.DataChart({
          query: {
            metrics: 'ga:sessions',
            dimensions: 'ga:date',
            'start-date': '30daysAgo',
            'end-date': 'today'
          },
          chart: {
            container: 'chart-container',
            type: 'LINE',
            options: {
              width: '10%'
            }
          }
        });
      
        viewSelector.on('change', function(ids) {
          dataChart.set({query: {ids: ids}}).execute();
        });
      });
      },
      async getToken(){
        var httpRequest = new XMLHttpRequest()
        httpRequest.onreadystatechange = function () {
          if (httpRequest.readyState === XMLHttpRequest.DONE && httpRequest.status === 200) {
            accessToken = JSON.parse(httpRequest.responseText)
          }
        }
        httpRequest.open("GET", "http://192.168.100.90:5000/gapi/token/access", false)
        await httpRequest.send()

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
            this.$axios.post('http://192.168.100.90:8000/api/members/getAll')
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
            this.$axios.post('http://192.168.100.90:8000/api/portfolios/getAll')
                .then(response => {
                  this.Portfoilos = response.data;
                  this.length = response.data.length;
                })
                .catch(error => {
                  console.log(error)
                });
          }
          else if(str === "POSTS")
          {
            this.Postshow = true;
            this.$axios.post('http://192.168.100.90:8000/api/posts/getAll')
                .then(response => {
                  this.Posts = response.data;
                  this.length = response.data.length;
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
              this.$axios.post('http://192.168.100.90:8000/api/members/update',data).then(response => {
                this.$axios.post('http://192.168.100.90:8000/api/members/getAll').then(res => {
                  var cnt_admin=0;
                  var member_data=res.data;
                  member_data.forEach(function(element){
                    if(element.ranks=='3'){
                      cnt_admin=cnt_admin+1;
                    }
                  });
                  if(cnt_admin=='0'){
                        data.ranks=3;
                        this.$axios.post('http://192.168.100.90:8000/api/members/update',data).then(response => {
                          alert("관리자는 최소 1명 이상이어야 합니다")
                          this.$axios.post('http://192.168.100.90:8000/api/members/getAll').then(res => {
                            console.log(res.data)
                          this.Users = res.data; 
                           })
                          })
                    }else{
                      alert("수정 완료!")
                    }
                })
              }).catch(error => {
              })
          }
        },
        deleteItem(item, str){
          if(str === "USERS")
          {
            if(item.ranks=='3'){
               this.$axios.post('http://192.168.100.90:8000/api/members/getAll').then(res => {
                  var cnt_admin=0;
                  var member_data=res.data;
                  member_data.forEach(function(element){
                    if(element.ranks=='3'){
                      cnt_admin=cnt_admin+1;
                    }
                  });
                  if(cnt_admin=='1'){
                    alert("관리자는 최소 1명 이상이어야 합니다")
                   }else{
                     var result= confirm(item.email+" 정말 삭제하시겠습니까?")
                     if(result){
                       var data =item.email;
                       this.$axios.post('http://192.168.100.90:8000/api/members/delete/'+data).then(response => {
                         this.$axios.post('http://192.168.100.90:8000/api/members/getAll')
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
                   }
                })
            }else{
              var result= confirm(item.email+" 정말 삭제하시겠습니까?")
              if(result){
                var data =item.email;
                this.$axios.post('http://192.168.100.90:8000/api/members/delete/'+data).then(response => {
                  this.$axios.post('http://192.168.100.90:8000/api/members/getAll')
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
            }
          }
          else if(str === "PORTFOLIOS")
          {
             var result= confirm(item.title+"을 정말 삭제하시겠습니까?")
            if(result){
              var data =item.bno;
              this.$axios.post('http://192.168.100.90:8000/api/portfolios/delete/'+data).then(response => {
                this.$axios.post('http://192.168.100.90:8000/api/portfolios/getAll')
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
          }
          else if(str === "POSTS")
          {
             var result= confirm(item.title+"을 정말 삭제하시겠습니까?")
            if(result){
              var data =item.bno;
              this.$axios.post('http://192.168.100.90:8000/api/posts/delete/'+data).then(response => {
                this.$axios.post('http://192.168.100.90:8000/api/posts/getAll')
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
  margin-right: 9px;
}

</style>