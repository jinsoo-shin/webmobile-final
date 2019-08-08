<template>
  <div>
    <v-layout row>
      <v-flex xs12 sm6 offset-sm3 px-4>

        <!-- <v-card>
          <v-list two-line class="noPadding">
            <v-list-tile
              :disabled="loading"
              active-class="highlighted"
              :class="item.username === selecteduser ? 'primary' : ''"
              v-for="(item, i) in members"
              :key="i"
              @click="changeMember(item.username)"
            >
              <v-list-tile-avatar>
                <img :src="item.avatar_url" />
              </v-list-tile-avatar>

              <v-list-tile-content>
                <v-list-tile-title v-html="item.name"></v-list-tile-title>
                <v-list-tile-sub-title
                  v-html="item.web_url"
                ></v-list-tile-sub-title>
              </v-list-tile-content>
            </v-list-tile>
          </v-list>
        </v-card> -->
      </v-flex>
    </v-layout>

        <div id="app">
          <v-app id="inspire">
            <v-layout justify-space-around wrap>
          
              <v-avatar color="indigo">
                <v-icon dark>account_circle</v-icon>
              </v-avatar>
          
              <v-avatar>
                <img
                  src="https://cdn.vuetifyjs.com/images/john.jpg"
                  alt="John"
                >
              </v-avatar>
          
              <v-avatar color="red">
                <span class="white--text headline">CJ</span>
              </v-avatar>
          
            </v-layout>
          </v-app>
        </div>


    <br />
    <v-layout column px-4>
      <div>
        <v-expansion-panel
          v-if="loading"
          :disabled="true"
          expand
          v-model="loadingPanel"
        >
          <v-expansion-panel-content disabled>
            <template v-slot:header>
              <Repository :repos="loadingRepo"></Repository>
            </template>
          </v-expansion-panel-content>
        </v-expansion-panel>
        <v-expansion-panel
          v-model="panel"
          expand
          v-if="!loading"
          :disabled="!show"
          v-resize="isMobile"
        >
          <v-expansion-panel-content
            v-for="i in repositories.length > limits
              ? limits
              : repositories.length"
            :key="i"
          >
            <template v-slot:header>
              <Repository
                :repos="repositories[i - 1]"
                :user="selecteduser"
                :graphno="selecteduser + 'graph' + i"
              ></Repository>
            </template>
            <v-card class="hidden-xs-only">
              <v-card-text class="grey lighten-3">
                <RepositoryGraph
                  :show="panel[i - 1]"
                  :repos="repositories[i - 1]"
                  :user="selecteduser"
                  :graphno="selecteduser + 'graph' + i"
                >
                </RepositoryGraph>
              </v-card-text>
            </v-card>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </div>
    </v-layout>
  </div>
</template>

<script>

import RepositoryGraph from "@/components/RepositoryGraph";
import Repository from "@/components/Repository";
import GitlabService from "@/services/GitlabService";


export default {
  name: "RepositoryList",
  props: {
    limits: {
      type: Number,
      default: 5
    },
    loadMore: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      panel: [],
      loadingPanel: [true],
      repositories: [],
      members: [],
      selecteduser: "ClearRoot",
      loading: false,
      loadingRepo: {
        path_with_namespace: "로딩중",
        namespace: {
          name: "Https://lab.ssafy.com/api/v4에서 정보를 받아오는 중입니다..."
        }
      },
      show: true
    };
  },
  components: {
    Repository,
    RepositoryGraph
  },
	mounted() {
    this.getGitlabRepos("clearroot");
    this.getGitlabMembers();
	},
  methods: {
    isMobile() {
      if (window.innerWidth > 600) this.show = true;
      else this.show = false;
    },
    async getGitlabRepos(userName) {
      this.loading = true;
      const response = await GitlabService.getRepos2(userName);
      if (response.status !== 200) {
        return;
      }
      this.repositories = response.data;
      this.loading = false;
    },
    async getGitlabMembers() {
      const mem = await GitlabService.getMembers();
      if (mem.status !== 200) {
        return;
      }
      this.members = mem.data;
    },
    changeMember(username) {
      this.getGitlabRepos(username);
      this.selecteduser = username;
      this.panel = [];
    }
  }
};

</script>
