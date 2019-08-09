var isChrome = !!window.chrome && (!!window.chrome.webstore || !!window.chrome.runtime);

window.onload = function() {
    if (Notification.permission == 'default' && window.Notification) {
        Notification.requestPermission().then(function(permission) {
            if (permission == 'granted' && !isChrome) {
                alert('해당 사이트는 크롬에 최적화 되어 있습니다.');
                var notification = new Notification('해당 사이트는 크롬에 최적화 되어 있습니다.', {
                    icon: './chrome.png'
                });
            }
        });
    }
}