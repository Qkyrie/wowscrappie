System.register(["angular2/core", "../services/AuctionHouseCategoryService"], function(exports_1, context_1) {
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
    var core_1, AuctionHouseCategoryService_1;
    var AuctionHouseCategories;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (AuctionHouseCategoryService_1_1) {
                AuctionHouseCategoryService_1 = AuctionHouseCategoryService_1_1;
            }],
        execute: function() {
            AuctionHouseCategories = (function () {
                function AuctionHouseCategories(auctionhouseCategoryService) {
                    this.auctionhouseCategoryService = auctionhouseCategoryService;
                }
                AuctionHouseCategories.prototype.getFullUrl = function () {
                    var fullbaseUrlMeta = $("meta[name=_fullbaseurl]");
                    return fullbaseUrlMeta.attr("content");
                };
                AuctionHouseCategories.prototype.visitSubSection = function (id, e) {
                    e.preventDefault();
                    window.location.href = this.getFullUrl() + "/auctionhouse/subcategory/" + id;
                };
                AuctionHouseCategories.prototype.ngOnInit = function () {
                    var _this = this;
                    this.auctionhouseCategoryService.categories()
                        .subscribe(function (categories) {
                        _this.categories = categories;
                    }, function (error) {
                    });
                };
                AuctionHouseCategories = __decorate([
                    core_1.Component({
                        selector: 'auctionhouse-categories',
                        template: "\n                        <div class=\"col-md-6\" *ngFor=\"#category of categories; var index=index\">\n                         <h4>{{category.name}}</h4>\n                         <ul>\n                                <li *ngFor=\"#subCategory of category.subCategories\">\n                                    <a (click)=\"visitSubSection(subCategory.id, $event)\" href=\"#\" >{{subCategory.name}}</a>\n                                </li>\n                            </ul>\n                        </div>\n    ",
                        providers: [AuctionHouseCategoryService_1.AuctionHouseCategoryService]
                    }), 
                    __metadata('design:paramtypes', [AuctionHouseCategoryService_1.AuctionHouseCategoryService])
                ], AuctionHouseCategories);
                return AuctionHouseCategories;
            }());
            exports_1("AuctionHouseCategories", AuctionHouseCategories);
        }
    }
});
//# sourceMappingURL=auctionhousecategories.js.map