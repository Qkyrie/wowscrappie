System.register(['angular2/core', './singleItemSearch', './multiItemSearch'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, singleItemSearch_1, multiItemSearch_1;
    var AuctionHouseSearch;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (singleItemSearch_1_1) {
                singleItemSearch_1 = singleItemSearch_1_1;
            },
            function (multiItemSearch_1_1) {
                multiItemSearch_1 = multiItemSearch_1_1;
            }],
        execute: function() {
            AuctionHouseSearch = (function () {
                function AuctionHouseSearch() {
                }
                AuctionHouseSearch = __decorate([
                    core_1.Component({
                        selector: 'auctionhouse-search',
                        template: "\n               <div id=\"page-inner\">\n\n        <div class=\"row\">\n            <div class=\"col-md-12\">\n                <ul class=\"nav nav-tabs style-1\" role=\"tablist\">\n                    <li role=\"presentation\" id=\"homeTab\" class=\"active\"><a href=\"#singleItemSearch\" aria-controls=\"home\"\n                                                                           role=\"tab\" data-toggle=\"tab\">Single Item\n                        Search</a></li>\n                    <li role=\"presentation\" id=\"compareTab\"><a href=\"#compareItemSearch\" aria-controls=\"profile\"\n                                                               role=\"tab\"\n                                                               data-toggle=\"tab\">Item Compare</a></li>\n                </ul>\n            </div>\n        </div>\n\n        <div class=\"tab-content\">\n            <div role=\"tabpanel\" class=\"tab-pane fade in active\" id=\"singleItemSearch\">\n                <single-search></single-search>\n            </div>\n            <div role=\"tabpanel\" class=\"tab-pane fade in\" id=\"compareItemSearch\">\n                <multi-search></multi-search>\n            </div>\n        </div>\n\n    </div>\n    ",
                        directives: [singleItemSearch_1.SingleItemSearch, multiItemSearch_1.MultiItemSearch]
                    }), 
                    __metadata('design:paramtypes', [])
                ], AuctionHouseSearch);
                return AuctionHouseSearch;
            })();
            exports_1("AuctionHouseSearch", AuctionHouseSearch);
        }
    }
});
//# sourceMappingURL=auctionhousesearch.js.map