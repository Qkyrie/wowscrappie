System.register(["rxjs/add/operator/map", "angular2/core", "angular2/http", "../entity/auctionhousecategory", "../entity/auctionhousesubcategory"], function(exports_1, context_1) {
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
    var core_1, http_1, auctionhousecategory_1, auctionhousesubcategory_1;
    var AuctionHouseCategoryService;
    return {
        setters:[
            function (_1) {},
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (auctionhousecategory_1_1) {
                auctionhousecategory_1 = auctionhousecategory_1_1;
            },
            function (auctionhousesubcategory_1_1) {
                auctionhousesubcategory_1 = auctionhousesubcategory_1_1;
            }],
        execute: function() {
            AuctionHouseCategoryService = (function () {
                function AuctionHouseCategoryService(http) {
                    this.http = http;
                }
                AuctionHouseCategoryService.prototype.categories = function () {
                    return this.http.get('/rest/auctionhouse/category')
                        .map(function (responseData) {
                        return responseData.json();
                    })
                        .map(function (elements) {
                        if (elements) {
                            return elements.map(function (element) {
                                return new auctionhousecategory_1.AuctionHouseCategory(element.id, element.name, element.slug, element.subCategories.map(function (subElement) {
                                    return new auctionhousesubcategory_1.AuctionHouseSubCategory(subElement.id, subElement.name, subElement.slug);
                                }));
                            });
                        }
                    });
                };
                AuctionHouseCategoryService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], AuctionHouseCategoryService);
                return AuctionHouseCategoryService;
            }());
            exports_1("AuctionHouseCategoryService", AuctionHouseCategoryService);
        }
    }
});
//# sourceMappingURL=AuctionHouseCategoryService.js.map