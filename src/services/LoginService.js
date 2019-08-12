import firebase, { database } from "firebase/app";
import "firebase/firestore";
import "firebase/database";
import "firebase/auth";
import "firebase/functions";
import "firebase/messaging";
import store from "../store.js";
import { Eventbus } from "../main.js";
import VueSwal from "vue-swal";
import Vue from "vue";
import { SSL_OP_NO_SESSION_RESUMPTION_ON_RENEGOTIATION } from "constants";
Vue.use(VueSwal);
import axios from 'axios'
import FirebaseService from "./FirebaseService.js";
Vue.$http = axios;
const MEMBER = "members";
const POSTS = "posts";
const PORTFOLIOS = "portfolios";
const firestore = firebase.firestore();
const messaging = firebase.messaging();

export default {
    getMember(email) {
        Vue.$http.post(
                'http://192.168.100.90:8000/api/members/get/' + email
            )
            .then(response => {
                sessionStorage.setItem("name", response.data.name);
                sessionStorage.setItem("rank", response.data.ranks);
                sessionStorage.setItem("email", response.data.email);
                return response.data;
            });
    },
    postMember(name, password, email, album, age) {
        var currentDate = new Date();
        var currentDay = currentDate.toISOString().substr(0, 10);
        var data = {
            age: age,
            album: album,
            email: email,
            name: name,
            password: password,
            ranks: 1
        };
        Vue.$http.post(
                'http://192.168.100.90:8000/api/members/insert', data
            )
            .then(response => {});
    },
    loginChk() {
        if (sessionStorage.getItem('email')) {
            Eventbus.$emit("getUserEmail");
        }
    },
    loginService(e, email, pw) {
        e.preventDefault();
        var tmp = email
        var token = "";
        var member = "";
        messaging.requestPermission()
            .then(function() {
                messaging.getToken().then((currentToken) => {
                    token = currentToken;
                    var ranks = "";
                    Vue.$http.post(
                            'http://192.168.100.90:8000/api/members/get/' + email
                        )
                        .then(response => {
                            member = response.data;
                            ranks = member.ranks;
                            if (member && member.password == pw) {
                                store.state.accessToken = tmp;
                                swal("Login Success!", "You are ready to start!", "success", {
                                    buttons: false,
                                    timer: 2000,
                                })
                                var signInWithEmailLog = firebase.functions().httpsCallable('signInWithEmailLog');
                                signInWithEmailLog({ access: "Email", email: email }).then(function(result) {}).catch(function(error) {});
                                setTimeout(() => {
                                    window.location.href = "/"
                                }, 2000);
                            } else {
                                swal("Login Failed!", "Please Check your E-mail or Password!", "warning", {
                                    buttons: false,
                                    timer: 1500,
                                })
                            }
                        });
                    Vue.$http.post(
                            'http://192.168.100.90:8000/api/tokens/get/' + email
                        )
                        .then(response => {
                            if (response.data) {
                                Vue.$http.post(
                                        'http://192.168.100.90:8000/api/tokens/update', { email: email, ranks: ranks, token: token }
                                    )
                                    .then(response => {});
                            } else {
                                Vue.$http.post(
                                        'http://192.168.100.90:8000/api/tokens/insert', { email: email, ranks: ranks, token: token }
                                    )
                                    .then(response => {});
                            }
                        });
                })
            })
            .catch(function(err) {});
        e.preventDefault();
    },
    logOut() {
        var email = sessionStorage.getItem('email');
        var signOutLog = firebase.functions().httpsCallable('signOutLog');
        signOutLog({ email: email }).then(function() {}).catch(function(error) {});
        if (firebase.auth()) {
            firebase.auth().signOut().then(function() {})
        }
        location.reload(true)
    },
    loginWithGoogle() {
        let provider = new firebase.auth.GoogleAuthProvider();
        return firebase
            .auth()
            .signInWithPopup(provider)
            .then(function(result) {
                store.state.accessToken = result.credential.accessToken;
                if (store.state.accessToken != "") {
                    store.state.login = false;
                }
                let user = result.user;
                sessionStorage.setItem("name", "visitor");
                sessionStorage.setItem("rank", 1);
                sessionStorage.setItem("email", user.email);
                var signInLog = firebase.functions().httpsCallable("signInLog");
                signInLog({ access: "Google" })
                    .then(function(result) {})
                    .catch(function(error) {});
                return result;
            })
            .catch(function(error) {});
    },
    loginWithFacebook() {
        let provider = new firebase.auth.FacebookAuthProvider();
        return firebase
            .auth()
            .signInWithPopup(provider)
            .then(function(result) {
                store.state.accessToken = result.credential.accessToken;
                if (store.state.accessToken != "") {
                    store.state.login = false;
                }
                let user = result.user;
                sessionStorage.setItem("name", "visitor");
                sessionStorage.setItem("rank", 1);
                sessionStorage.setItem("email", user.email);
                var signInLog = firebase.functions().httpsCallable("signInLog");
                signInLog({ access: "Facebook" })
                    .then(function(result) {})
                    .catch(function(error) {});
                return result;
            })
            .catch(function(error) {});
    },

}