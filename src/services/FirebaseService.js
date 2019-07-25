import firebase, { database } from "firebase/app";
import "firebase/firestore";
import "firebase/database";
import "firebase/auth";
import "firebase/functions";
import store from "../store.js";
import { Eventbus } from "../main.js";
import VueSwal from "vue-swal";
import Vue from "vue";
Vue.use(VueSwal);

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
var db;
const firestore = firebase.firestore();
let accessToken = "";
export default {
  getMember: function(email) {
    db = firebase.firestore(app);
    var docRef = db.collection(MEMBER).doc(email);
    return docRef
      .get()
      .then(doc => {
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
    db = firebase.firestore(app);
    var data = {
      age: age,
      album: album,
      created_at: firebase.firestore.FieldValue.serverTimestamp(),
      email: email,
      name: name,
      password: password
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
          data.created_at = new Date(data.created_at.toDate());
          return data;
        });
      });
  },
  postPost(title, body) {
    return firestore.collection(POSTS).add({
      title,
      body,
      created_at: firebase.firestore.FieldValue.serverTimestamp()
    });
  },
  getPortfolios() {
    const postsCollection = firestore.collection(PORTFOLIOS);
    return postsCollection
      .orderBy("created_at", "desc")
      .get()
      .then(docSnapshots => {
        return docSnapshots.docs.map(doc => {
          let data = doc.data();
          data.created_at = new Date(data.created_at.toDate());
          return data;
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
        created_at: firebase.firestore.FieldValue.serverTimestamp()
      })
      .then(function(result) {
        swal("Post Success!", "", "success", {
          buttons: false,
          timer: 2000
        });
        window.location.href = "/";
      })
      .catch(function(error) {});
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
      } else {
      }
    });
  },
  loginService(e, email, pw) {
    e.preventDefault();
    var tmp = email;
    firebase
      .auth()
      .signInWithEmailAndPassword(email, pw)
      .then(
        function(user) {
          store.state.accessToken = tmp;
          swal("Login Success!", "You are ready to start!", "success", {
            buttons: false,
            timer: 2000
          });
          var signInWithEmailLog = firebase
            .functions()
            .httpsCallable("signInWithEmailLog");
          signInWithEmailLog({ access: "Email", email: email })
            .then(function(result) {})
            .catch(function(error) {});

          setTimeout(() => {
            window.location.href = "/";
          }, 2000);
        },
        function(err) {
          swal(
            "Login Failed!",
            "Please Check your E-mail or Password!",
            "warning",
            {
              buttons: false,
              timer: 1500
            }
          );
        }
      );
    e.preventDefault();
  },
  logOut() {
    var email = sessionStorage.getItem("email");
    var signOutLog = firebase.functions().httpsCallable("signOutLog");
    signOutLog({ email: email })
      .then(function(result) {})
      .catch(function(error) {});
    firebase
      .auth()
      .signOut()
      .then(function() {});
  }
};
