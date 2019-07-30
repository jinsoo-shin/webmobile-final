
 <template>
 <div id="disqus_thread"></div>
 </template>
<script>
    var disqus_config = function () {
        // this.page.url = '{{ site.url }}{{ page.url }}';  // Replace PAGE_URL with your page's canonical URL variable
        // this.page.identifier = '5GisAwesome'; // Replace PAGE_IDENTIFIER with your page's unique identifier variable
        var disqus_developer = 1;
        };
    
        (function() { // DON'T EDIT BELOW THIS LINE
        var d = document, s = d.createElement('script');
        s.src = 'https://5gisawesome.disqus.com/embed.js';
        s.setAttribute('data-timestamp', +new Date());
        (d.head || d.body).appendChild(s);
        })();
</script>
<script>
export default {
  name: 'Comment'
}
</script>
