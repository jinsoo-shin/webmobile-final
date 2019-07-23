<template></template>

<script>
// Chrome 1 - 71
var isChrome = !!window.chrome && (!!window.chrome.webstore || !!window.chrome.runtime);

window.onload = function () {
            if (window.Notification) {
                Notification.requestPermission();
            }
            notify();
        }
 
        function notify() {
            if (Notification.permission !== 'granted') {
                // alert('notification is disabled');
                }
            else {
                if(!isChrome){
                alert('해당 사이트는 크롬에 최적화 되어 있습니다.');
                    var notification = new Notification('해당 사이트는 크롬에 최적화 되어 있습니다.',{
                        icon:'./chrome.png'
                    });
                }
 
            }
        }
</script>