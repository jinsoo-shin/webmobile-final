importScripts('https://www.gstatic.com/firebasejs/4.13.0/firebase-app.js')
importScripts('https://www.gstatic.com/firebasejs/4.13.0/firebase-messaging.js')
firebase.initializeApp({
    'messagingSenderId': '527574403487'
});
const messaging = firebase.messaging();

messaging.setBackgroundMessageHandler(function(payload) {
    var notificationTitle = 'Background Message Title';
    var notificationOptions = {
        body: 'Background Message body.'
    };

    return self.registration.showNotification(notificationTitle,
        notificationOptions);
});