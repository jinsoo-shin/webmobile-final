const functions = require('firebase-functions');
const admin = require('firebase-admin');
// admin.initializeApp();
admin.initializeApp(functions.config().firebase);
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
    var access = data.access;
    var email = data.email;
    var token = data.token;
    var date = new Date();
    date.setHours(date.getHours() + 9);
    console.log("email : " + email + " token : " + token)
    if (token) {
        const payload = {
            notification: {
                title: email + " 로그인",
                body: "테스트테스트테스트"
            },
            token: token
        };
        admin.messaging().send(payload);
        // admin.messaging().sendToDevice(token, payload)
    }

});
// exports.sendNewPostNotification = functions.https.onCall((data, context) => {
//     var access = data.access;
//     var email = data.email;
//     var token = data.token;
//     var date = new Date();
//     date.setHours(date.getHours() + 9);
//     console.log("email : " + email + " token : " + token)
//     if (token) {
//         const payload = {
//             notification: {
//                 title: email + " 로그인",
//                 body: "테스트테스트테스트"
//             }
//         };
//         admin.messaging().sendToDevice(token, payload).then(function(response) {
//                 return console.log('Successfully sent message:', response);
//             })
//             .catch(function(error) {
//                 return console.log('Error sending message:', error);
//             });

//     }

// });