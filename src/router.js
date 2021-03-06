import Vue from "vue";
import Router from "vue-router";
import HomePage from "./views/HomePage.vue";
import PostPage from "./views/PostPage.vue";
import PortfolioPage from "./views/PortfolioPage.vue";
import LoginPage from "./views/LoginPage.vue";
import AdminPage from "./views/AdminPage.vue";
import RepositoryPage from "./views/RepositoryPage.vue"

Vue.use(Router);

export default new Router({
    scrollBehavior() {
        return { x: 0, y: 0 };
    },
    mode: "history",
    base: process.env.BASE_URL,
    routes: [{
            path: "/",
            name: "home",
            component: HomePage
        },
        {
            path: "/post",
            name: "post",
            component: PostPage
        },
        {
            path: "/portfolio",
            name: "portfolio",
            component: PortfolioPage
        },
        {
            path: "/login",
            name: "login",
            component: LoginPage
        },
        {
            path: "/admin",
            name: "admin",
            component: AdminPage
        },
        {
            path: "/repository",
            name: "repository",
            component: RepositoryPage
        }
    ]
});