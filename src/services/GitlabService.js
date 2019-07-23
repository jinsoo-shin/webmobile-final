import Api from "@/services/Api";

const BASE_URL = "https://lab.ssafy.com/api/v4";

export default {
  getRepos() {
    return Api(BASE_URL).get(`/projects?membership=true`);
  },
  getCommits(fullName, page) {
    return Api(BASE_URL).get(
      `/projects/${fullName}/repository/commits?per_page=100&page=${page}`
    );
  }
};
