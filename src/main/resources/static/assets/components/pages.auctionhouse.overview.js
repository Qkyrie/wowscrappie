System.register(['angular2/platform/browser', './pages.auctionhouse.itemsearch', 'angular2/http'], function(exports_1) {
    var browser_1, pages_auctionhouse_itemsearch_1, http_1;
    return {
        setters:[
            function (browser_1_1) {
                browser_1 = browser_1_1;
            },
            function (pages_auctionhouse_itemsearch_1_1) {
                pages_auctionhouse_itemsearch_1 = pages_auctionhouse_itemsearch_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            browser_1.bootstrap(pages_auctionhouse_itemsearch_1.SingleItemSearch, http_1.HTTP_PROVIDERS);
        }
    }
});
//# sourceMappingURL=pages.auctionhouse.overview.js.map