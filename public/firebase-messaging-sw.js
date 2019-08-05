importScripts('https://www.gstatic.com/firebasejs/4.13.0/firebase-app.js')
importScripts('https://www.gstatic.com/firebasejs/4.13.0/firebase-messaging.js')
firebase.initializeApp({
    'messagingSenderId': '527574403487'
});

messaging.setBackgroundMessageHandler(function(payload) {
    console.log('[firebase-messaging-sw.js] Received background message ', payload);
    // Customize notification here
    var notificationTitle = 'Background Message Title';
    var notificationOptions = {
        body: 'Background Message body.',
        icon: '/firebase-logo.png'
    };

    return self.registration.showNotification(notificationTitle,
        notificationOptions);
});
// const messaging = firebase.messaging();
// messaging.setBackgroundMessageHandler(function(payload) {
//     var notificationTitle = payload.notification.title;
//     var notificationOptions = {
//         body: payload.notification.body
//     };
//     return self.registration.showNotification(notificationTitle,
//         notificationOptions);
// });

// const messaging = firebase.messaging();
// messaging.setBackgroundMessageHandler(function(payload) {
//     var notificationTitle = payload.notification.title;
//     var notificationOptions = {
//         body: payload.notification.body
//     };
//     return self.registration.showNotification(notificationTitle,
//         notificationOptions);
// });