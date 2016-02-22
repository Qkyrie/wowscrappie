System.register(['angular2/platform/browser', './auctionhouse/pages/singleItemSearch', 'angular2/http'], function(exports_1) {
    var browser_1, singleItemSearch_1, http_1;
    return {
        setters:[
            function (browser_1_1) {
                browser_1 = browser_1_1;
            },
            function (singleItemSearch_1_1) {
                singleItemSearch_1 = singleItemSearch_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            browser_1.bootstrap(singleItemSearch_1.SingleItemSearch, http_1.HTTP_PROVIDERS);
        }
    }
});
//# sourceMappingURL=pages.auctionhouse.overview.js.map