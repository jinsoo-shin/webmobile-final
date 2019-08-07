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
    getPosts() {
        const postsCollection = firestore.collection(POSTS);
        return postsCollection
            .orderBy("created_at", "desc")
            .get()
            .then(docSnapshots => {
                return docSnapshots.docs.map(doc => {
                    let data = doc.data();
                    data.created_at = new Date();
                    return [data, doc];
                });
            });
    },
    postPost(title, body) {
        return firestore
            .collection(POSTS)
            .add({
                title,
                body,
                created_at: firebase.firestore.FieldValue.serverTimestamp(),
                author: sessionStorage.getItem("name")
            })
            .then(function(result) {
                swal("Post Success!", "", "success", {
                    buttons: false,
                    timer: 4000
                });
                var tokens = [];
                Vue.$http.post(
                        'https://192.168.100.90:8000/api/tokens/getAll/0'
                    )
                    .then(response => {
                        tokens = response.data;
                        var sendNewPostNotification = firebase.functions().httpsCallable('sendNewPostNotification');
                        sendNewPostNotification({ tokens: tokens, author: sessionStorage.getItem("name"), component: "Post" }).then(function(result) {
                            window.location.href = "/post";
                        }).catch(function(error) {});
                    });
            })
            .catch(function(error) {});
    },
    editPost(id, title, body, author) {
        var data = {
            title: title,
            body: body,
            created_at: firebase.firestore.FieldValue.serverTimestamp(),
            author: author
        };
        return db.collection(POSTS)
            .doc(id)
            .set(data);
    },
    deletePost(id) {
        return firestore
            .collection(POSTS)
            .doc(id)
            .delete();
    },
    getPortfolios() {
        const postsCollection = firestore.collection(PORTFOLIOS);
        return postsCollection
            .orderBy("created_at", "desc")
            .get()
            .then(docSnapshots => {
                return docSnapshots.docs.map(doc => {
                    let data = doc.data();
                    data.created_at = new Date();
                    return [data, doc];
                });
            });
    },
    postPortfolio(title, body, img) {
        return firestore
            .collection(PORTFOLIOS)
            .add({
                title,
                body,
                img,
                created_at: firebase.firestore.FieldValue.serverTimestamp(),
                author: sessionStorage.getItem("name")
            })
            .then(function(result) {
                swal("Post Success!", "", "success", {
                    buttons: false,
                    timer: 2000
                });
                window.location.href = "/portfolio";
            })
            .catch(function(error) {});
    },
    editPortfolio(id, title, body, img, author) {
        var data = {
            title: title,
            body: body,
            created_at: firebase.firestore.FieldValue.serverTimestamp(),
            img: img,
            author: author
        };
        return db.collection(PORTFOLIOS)
            .doc(id)
            .set(data);
    },
    deletePortfolio(id) {
        return firestore
            .collection(PORTFOLIOS)
            .doc(id)
            .delete();
    },
    sendCommentPush(component) {
        var tokens = [];
        Vue.$http.post(
                'https://192.168.100.90:8000/api/tokens/getAll/3'
            )
            .then(response => {
                tokens = response.data;
                var sendNewCommentNotification = firebase.functions().httpsCallable('sendNewCommentNotification');
                sendNewCommentNotification({ tokens: tokens, author: sessionStorage.getItem("name"), component: "component" }).then(function(result) {}).catch(function(error) {});
            });
    }
}