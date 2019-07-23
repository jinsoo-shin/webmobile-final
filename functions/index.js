const functions = require('firebase-functions');
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