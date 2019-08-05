<template>
  <div>
    <div class="text-xs-center">
      <v-progress-circular indeterminate color="primary" v-if="loading">
      </v-progress-circular>
    </div>
    <div v-bind:id="graphno" class="titleText"></div>
  </div>
</template>

<script>
import GitlabService from "@/services/GitlabService";
import { createGitgraph, TemplateName, templateExtend } from "@gitgraph/js";

export default {
  name: "Repository",

  props: {
    repos: {
      type: null
    },
    graphno: null,
    show: null
  },
  data() {
    return {
      onLoad: false,
      loading: true
    };
  },
  watch: {
    show: function(newVal, oldVal ) {
      if (this.show) {
        if (this.onLoad == window.innerWidth) return;
        else {
          document.getElementById(this.graphno).innerHTML = "";
          this.drawStatGraph();
        }
      }
    }
  },
  methods: {
    async drawStatGraph() {
      this.commits = await GitlabService.getCommits(this.repos.id, 1);
      var page = 2;
      var arr = this.commits.data;
      for (;;) {
        var temp = await GitlabService.getCommits(this.repos.id, page);
        if (temp.data.length == 0) break;
        arr = arr.concat(temp.data);
        page++;
      }
      this.commits.data = arr;
      await this.drawGitGraph(this.commits);
    },
    goLink(link) {
      window.open("https://lab.ssafy.com/" + link);
    },

    async drawGitGraph(commits) {
      this.loading = true;
      var customBranchLabels = templateExtend(TemplateName.Metro, {
        colors: ["black", "blue", "green", "yellow", "magenta"],
        branch: {
          lineWidth: 10, // 선 두께
          spacing: 50 // 점과 메세지 간격
        },
        commit: {
          spacing: 50, // 상하 간격
          dot: {
            size: 10 // 커밋 점 크기
          },
          message: {
            font: "normal 10pt Arial"
          }
        }
      });

      var graphContainer = document.getElementById(this.graphno);
      var gitgraph = createGitgraph(graphContainer, {
        template: customBranchLabels
      });
      var master = gitgraph.branch("master");

      for (var i = 0; i < commits.data.length; i++) {
        let link =
          this.repos.path_with_namespace + "/commit/" + commits.data[i].id;
        var imo;
        var message = commits.data[i].title.toLowerCase();
        if (i == commits.data.length - 1) {
          imo = "🎉";
        } else if (message.indexOf("merge") >= 0) {
          imo = "🔀";
        } else if (message.indexOf("fix") >= 0 || message.indexOf("bug") >= 0) {
          imo = "🐛";
        } else if (message.indexOf("delete") >= 0) {
          imo = "➖";
        } else if (message.indexOf("add") >= 0) {
          imo = "➕";
        } else if (message.indexOf("commit") >= 0) {
          imo = "📝";
        } else {
          imo = "💬";
        }
        master.commit({
          subject: commits.data[i].title,
          dotText: imo,
          onClick: function() {
            window.open("https://lab.ssafy.com/" + link);
          },
          onMessageClick: function() {
            window.open("https://lab.ssafy.com/" + link);
          },
          onMouseOver: function() {
            document.body.style.cursor = "pointer";
          },
          onMouseOut: function() {
            document.body.style.cursor = "default";
          },
          author:
            commits.data[i].committer_name +
            " <" +
            commits.data[i].author_email +
            ">"
        });
      }
      this.loading = false;
    }
  }
};
</script>

<style>
.titleText {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
}

text {
  cursor: pointer;
}
</style>