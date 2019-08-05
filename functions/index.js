const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

// var admin = require("firebase-admin");

// var serviceAccount = require("path/to/serviceAccountKey.json");

// admin.initializeApp({
// credential: admin.credential.cert(serviceAccount),
// databaseURL: "https://team-42093.firebaseio.com"
// });
exports.signInLog = functions.https.onCall((data, context) => {
    var access = data.access;
    var email = context.auth.token.email;
    var date = new Date();
    date.setHours(date.getHours() + 9);
    console.log(date + " " + access + "으로 " + email + " 로그인");
    return true;
});
exports.signInWithEmailLog = functions.https.onCall((data, context) => {
    var access = data.access;
    var email = data.email;
    var date = new Date();
    date.setHours(date.getHours() + 9);
    console.log(date + " " + access + "으로 " + email + " 로그인");
    return true;
});
exports.signUpLog = functions.https.onCall((data, context) => {
    var name = data.name;
    var email = data.email;
    var date = new Date();
    date.setHours(date.getHours() + 9);
    console.log(date + " 이름 :" + name + " , 이메일 : " + email + " 회원가입");
    return true;
});
exports.signOutLog = functions.https.onCall((data, context) => {
    var email = data.email;
    var date = new Date();
    date.setHours(date.getHours() + 9);
    console.log(date + " " + email + " 로그아웃");
    return true;
});
exports.sendNewPostNotification = functions.https.onCall((data, context) => {
    var tokens = data.tokens;
    var component = data.component;
    var author = data.author;
    tokens.forEach(function(element) {
        const payload = {
            notification: {
                title: component + " 새글알림",
                body: author + "님이 새로운 글을 등록했습니다."
            },
            token: element.token
        };
        admin.messaging().send(payload);
    })





    // admin.messaging().send(payload);
    // const payload = {
    //     notification: {
    //         title: "test",
    //         body: "testtesttest"
    //     },
    //     token: element.token
    // };
    // admin.messaging().sendToDevice(token, payload)
});