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
Vue.$http = axios;

const MEMBER = "members";
const POSTS = "posts";
const PORTFOLIOS = "portfolios";

const config = {
    apiKey: "AIzaSyCif27PgFJSlqA4-fZdPEEgikmP3UAQ7-A",
    authDomain: "team-42093.firebaseapp.com",
    databaseURL: "https://team-42093.firebaseio.com",
    projectId: "team-42093",
    storageBucket: "team-42093.appspot.com",
    messagingSenderId: "527574403487",
    appId: "1:527574403487:web:1df4659fb3babe48"
};

var app = firebase.initializeApp(config);
let db = firebase.firestore(app);

db.enablePersistence({ experimentalTabSynchronization: true })
const firestore = firebase.firestore();
const messaging = firebase.messaging();


export default {
    sendCommentPush(component) {
        var tokens = [];
        Vue.$http.post(
                'https://192.168.100.90:8000/api/tokens/getAll/3'
            )
            .then(response => {
                tokens = response.data;
                var sendNewCommentNotification = firebase.functions().httpsCallable('sendNewCommentNotification');
                sendNewCommentNotification({ tokens: tokens, author: sessionStorage.getItem("name"), component: component }).then(function(result) {}).catch(function(error) {});
            });
    }
}