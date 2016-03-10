/* * * CONFIGURATION VARIABLES * * */

var m = $("meta[name=_disqusname]");
var disqus_shortname = m.attr("content");
var disqus_identifier = $("#disqus_identifier").val();

var fullUrl = '/interface/comments/' + disqus_identifier + '/disqus';

function disqus_config() {
    this.callbacks.onNewComment = [function (comment) {
        var stringyfiedCommand = JSON.stringify(comment);
        $.ajax({
            url: fullUrl,
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: stringyfiedCommand, //Stringified Json Object
            cache: false,    //This will force requested pages not to be cached by the browser
            processData: false //To avoid making query String instead of JSON
        });
    }];
}


/* * * DON'T EDIT BELOW THIS LINE * * */
(function () {
    var dsq = document.createElement('script');
    dsq.type = 'text/javascript';
    dsq.async = true;
    dsq.src = '//' + disqus_shortname + '.disqus.com/embed.js';
    (document.getElementsByTagName('head')[0] || document.getElementsByTagName('body')[0]).appendChild(dsq);
})();