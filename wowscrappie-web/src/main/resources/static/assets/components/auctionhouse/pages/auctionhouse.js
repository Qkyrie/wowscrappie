System.register(['angular2/platform/browser', './auctionhouserealm', 'angular2/http'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var browser_1, auctionhouserealm_1, http_1;
    return {
        setters:[
            function (browser_1_1) {
                browser_1 = browser_1_1;
            },
            function (auctionhouserealm_1_1) {
                auctionhouserealm_1 = auctionhouserealm_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            browser_1.bootstrap(auctionhouserealm_1.AuctionHouseRealm, http_1.HTTP_PROVIDERS);
        }
    }
});
//# sourceMappingURL=auctionhouse.js.map