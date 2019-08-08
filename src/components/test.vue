<template>
  <div>
    <v-layout>
      <v-flex style="padding: 70px"
       v-for="(item, i) in members"
      :key="i">
      <v-avatar
      :disabled="loading"
      active-class="highlighted"
      :class="item.username === selecteduser ? 'primary' : ''"
      @click="changeMember(item.username)">
        <img :src="item.avatar_url" />
      </v-avatar>
      <h3>{{item.name}}</h3>
      </v-flex>
      </v-layout>

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
      selecteduser: "Leejiseon",
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
    this.getGitlabRepos("Leejiseon");
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