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
    getMember: function(email) {
        // db = firebase.firestore(app);
        var docRef = db.collection(MEMBER).doc(email);
        return docRef
            .get()
            .then(doc => {
                sessionStorage.setItem("name", doc.data().name);
                sessionStorage.setItem("rank", doc.data().rank);
                return doc.data();
            })
            .catch(function(error) {
                console.log("Error getting document:", error);
            });
    },
    postMember(name, password, email, album, age) {
        firebase
            .auth()
            .createUserWithEmailAndPassword(email, password)
            .then(
                function(user) {
                    var signUpLog = firebase.functions().httpsCallable("signUpLog");
                    signUpLog({ name: name, email: email })
                        .then(function(result) {})
                        .catch(function(error) {});
                    alert("가입등록이 완료되었습니다. 다시 로그인해 주세요");
                },
                function(err) {
                    alert("실패" + err.message);
                }
            );
        // db = firebase.firestore(app);
        var data = {
            age: age,
            album: album,
            created_at: firebase.firestore.FieldValue.serverTimestamp(),
            email: email,
            name: name,
            password: password,
            rank: 1
        };
        db.collection(MEMBER)
            .doc(email)
            .set(data);
    },
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
                        'http://192.168.100.90:8000/api/tokens/getAll/0'
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
        // db = firebase.firestore(app);
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
        // db = firebase.firestore(app);
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
                var signInLog = firebase.functions().httpsCallable("signInLog");
                signInLog({ access: "Google" })
                    .then(function(result) {})
                    .catch(function(error) {});
                return result;
            })
            .catch(function(error) {
                console.error("[Google Login Error]", error);
            });
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
                console.log(firebase.auth.token.email)
                var signInLog = firebase.functions().httpsCallable("signInLog");
                signInLog({ access: "Facebook" })
                    .then(function(result) {})
                    .catch(function(error) {});
                return result;
            })
            .catch(function(error) {
                console.error("[Facebook Login Error]", error);
            });
    },
    loginChk() {
        firebase.auth().onAuthStateChanged(function(user) {
            if (user) {
                Eventbus.$emit("getUserEmail", user.email);
            } else {}
        });
    },
    loginService(e, email, pw) {
        e.preventDefault();
        var tmp = email
        var token = "";
        messaging.requestPermission()
            .then(function() {
                messaging.getToken().then((currentToken) => {
                    token = currentToken;
                    var ranks = "";
                    Vue.$http.post(
                            'http://192.168.100.90:8000/api/members/get/' + email
                        )
                        .then(response => {
                            ranks = response.data.ranks;
                        });
                    Vue.$http.post(
                            'http://192.168.100.90:8000/api/tokens/get/' + email
                        )
                        .then(response => {
                            console.log("response", response)
                            if (response.data) {
                                Vue.$http.post(
                                        'http://192.168.100.90:8000/api/tokens/update', { email: email, ranks: ranks, token: token }
                                    )
                                    .then(response => {
                                        console.log("토큰 DB 업데이트")
                                    });
                            } else {
                                Vue.$http.post(
                                        'http://192.168.100.90:8000/api/tokens/insert', { email: email, ranks: ranks, token: token }
                                    )
                                    .then(response => {
                                        console.log("토큰 DB 생성")
                                    });
                            }
                        });
                })
            })
            .catch(function(err) {
                console.log('Error Occured.')
            });


        firebase.auth().signInWithEmailAndPassword(email, pw).then(
            function(user) {
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
            },
            function(err) {
                swal("Login Failed!", "Please Check your E-mail or Password!", "warning", {
                    buttons: false,
                    timer: 1500,
                })
            }
        )
        e.preventDefault();
    },
    logOut() {
        var email = sessionStorage.getItem('email');
        var signOutLog = firebase.functions().httpsCallable('signOutLog');
        signOutLog({ email: email }).then(function() {}).catch(function(error) {});
        firebase.auth().signOut().then(function() {})
    }
}