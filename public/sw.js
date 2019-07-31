self.addEventListener('install', function(event) {
    event.waitUntil(
        caches.open('my-cache').then(function(cache) {
            return cache.addAll([
                './',
                './index.html',
                './src/css/style.css',
                './assets/icon/facebook.svg',
                './assets/icon/github.svg',
                './assets/icon/google.svg',
                './assets/ahn.png',
                './assets/cho.png',
                './assets/lee.png',
                './assets/longstone.png',
                './assets/minion.png',
                './assets/mokuro.gif',
                './assets/park.png',
                './assets/pikachu.png'
            ]);
        })
    );
});
self.addEventListener('fetch', function(event) {
    event.respondWith(caches.match(event.request).then(function(response) {
        if (response !== undefined) {
            return response;
        } else {
            return fetch(event.request).then(function(response) {
                let responseClone = response.clone();
                caches.open('my-cache').then(function(cache) {
                    cache.put(event.request, responseClone);
                });
                return response;
            }).catch(function() {
                return caches.match('/src/assets/mokuroh.png');
            });
        }
    }));
});
self.addEventListener('activate', function(event) {
    event.waitUntil(
        caches.keys().then(function(cacheNames) {
            return Promise.all(
                cacheNames.filter(function(cacheName) {
                    // Return true if you want to remove this cache,
                    // but remember that caches are shared across
                    // the whole origin
                }).map(function(cacheName) {
                    return caches.delete(cacheName);
                })
            );
        })
    );
});