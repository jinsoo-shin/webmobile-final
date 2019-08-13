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
self.addEventListener("fetch", function(event) {
    if (event.request.method !== 'GET') {
        return;
    }
    event.respondWith(
        caches
        .match(event.request)
        .then(function(cached) {
            var networked = fetch(event.request)
                .then(fetchedFromNetwork, unableToResolve)
                .catch(unableToResolve);
            return cached || networked;

            function fetchedFromNetwork(response) {
                var cacheCopy = response.clone();
                caches
                    .open('my-cache')
                    .then(function add(cache) {
                        cache.put(event.request, cacheCopy);
                    })
                    .then(function() {});
                return response;
            }

            function unableToResolve() {
                return new Response('<h1>Service Unavailable</h1>', {
                    status: 503,
                    statusText: 'Service Unavailable',
                    headers: new Headers({
                        'Content-Type': 'text/html'
                    })
                });
            }
        })
    );
});