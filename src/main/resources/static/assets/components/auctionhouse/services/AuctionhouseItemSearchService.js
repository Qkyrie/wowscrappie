System.register(['rxjs/add/operator/map', 'angular2/core', 'angular2/http', '../entity/auctionhousesnapshot'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1, auctionhousesnapshot_1;
    var AuctionHouseItemSearchService;
    return {
        setters:[
            function (_1) {},
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (auctionhousesnapshot_1_1) {
                auctionhousesnapshot_1 = auctionhousesnapshot_1_1;
            }],
        execute: function() {
            AuctionHouseItemSearchService = (function () {
                function AuctionHouseItemSearchService(http) {
                    this.http = http;
                }
                AuctionHouseItemSearchService.prototype.doSearch = function (itemId, realmId) {
                    return this.http.get('/rest/auctionhouse/latest/item/' + itemId + '/realm/' + realmId)
                        .map(function (responseData) {
                        return responseData.json();
                    })
                        .map(function (element) {
                        if (element) {
                            console.log(element);
                            return new auctionhousesnapshot_1.AuctionHouseSnapshot(element.minimumBidCoppers, element.maximumBidCoppers, element.minimumBuyoutCoppers, element.maximumBuyoutCoppers, element.medianBidCoppers, element.medianBuyoutCoppers, element.stdevBidCoppers, element.stdevBuyoutCoppers, element.quantity, element.averageBidCopeprs, element.averageBuyoutCoppers, element.exportTimePretty, element.actualExportTime, element.itemName, element.itemId, element.realmName, element.realmId);
                        }
                    });
                };
                AuctionHouseItemSearchService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], AuctionHouseItemSearchService);
                return AuctionHouseItemSearchService;
            })();
            exports_1("AuctionHouseItemSearchService", AuctionHouseItemSearchService);
        }
    }
});
//# sourceMappingURL=AuctionhouseItemSearchService.js.map