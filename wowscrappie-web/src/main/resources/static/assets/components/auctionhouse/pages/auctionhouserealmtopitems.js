System.register(['angular2/core', "../services/AuctionHouseRealmSearchService"], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, AuctionHouseRealmSearchService_1;
    var AuctionHouseRealmTopItems;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (AuctionHouseRealmSearchService_1_1) {
                AuctionHouseRealmSearchService_1 = AuctionHouseRealmSearchService_1_1;
            }],
        execute: function() {
            AuctionHouseRealmTopItems = (function () {
                function AuctionHouseRealmTopItems(ahRealmSearchService) {
                    this.ahRealmSearchService = ahRealmSearchService;
                }
                AuctionHouseRealmTopItems.prototype.ngOnInit = function () {
                    var _this = this;
                    this.ahRealmSearchService.searchForTopItems(this.realmId)
                        .subscribe(function (topSeller) {
                        _this.topItemsAmount = topSeller;
                        _this.topItemsCount = topSeller;
                    }, function (error) {
                    });
                };
                __decorate([
                    core_1.Input("realmId"), 
                    __metadata('design:type', Number)
                ], AuctionHouseRealmTopItems.prototype, "realmId", void 0);
                AuctionHouseRealmTopItems = __decorate([
                    core_1.Component({
                        selector: 'auctionhouse-realm-top-items',
                        template: "\n                <div class=\"col-md-6\">\n                    <h4>by count</h4>\n                    \n                    <ul>\n                        <li *ngFor=\"#topItem of topItemsCount; var index=index\">\n                         {{topItem.name}}\n                        </li>\n                    </ul>\n                </div>\n                <div class=\"col-md-6\">\n                    <h4>by value</h4>\n                    \n                    <ul>\n                        <li *ngFor=\"#topItem of topItemsAmount; var index=index\">\n                         {{topItem.name}}\n                        </li>\n                    </ul>\n                </div>\n    ",
                        providers: [AuctionHouseRealmSearchService_1.AuctionHouseRealmSearchService]
                    }), 
                    __metadata('design:paramtypes', [AuctionHouseRealmSearchService_1.AuctionHouseRealmSearchService])
                ], AuctionHouseRealmTopItems);
                return AuctionHouseRealmTopItems;
            }());
            exports_1("AuctionHouseRealmTopItems", AuctionHouseRealmTopItems);
        }
    }
});
//# sourceMappingURL=auctionhouserealmtopitems.js.map