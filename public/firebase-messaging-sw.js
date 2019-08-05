importScripts('https://www.gstatic.com/firebasejs/4.13.0/firebase-app.js')
importScripts('https://www.gstatic.com/firebasejs/4.13.0/firebase-messaging.js')
firebase.initializeApp({
    'messagingSenderId': '527574403487'
});
<<<<<<< HEAD
// const messaging = firebase.messaging();
// messaging.setBackgroundMessageHandler(function(payload) {
//     var notificationTitle = payload.notification.title;
//     var notificationOptions = {
//         body: payload.notification.body
//     };
//     return self.registration.showNotification(notificationTitle,
//         notificationOptions);
// });

=======
>>>>>>> cd19f455fd5aa86784edea86bdbbb7eb8e48c995
const messaging = firebase.messaging();
messaging.setBackgroundMessageHandler(function(payload) {
    var notificationTitle = payload.notification.title;
    var notificationOptions = {
        body: payload.notification.body
    };
    return self.registration.showNotification(notificationTitle,
        notificationOptions);
});