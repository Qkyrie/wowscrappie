System.register(["angular2/core", "../../services/AuctionHouseCategoryService"], function(exports_1, context_1) {
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
    var SubCategory;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (AuctionHouseCategoryService_1_1) {
                AuctionHouseCategoryService_1 = AuctionHouseCategoryService_1_1;
            }],
        execute: function() {
            SubCategory = (function () {
                function SubCategory(elm, auctionhouseCategoryService) {
                    this.auctionhouseCategoryService = auctionhouseCategoryService;
                    this.subcategory = elm.nativeElement.getAttribute('subcategory-id');
                }
                SubCategory = __decorate([
                    core_1.Component({
                        selector: 'subcategory',
                        template: "\n      <h1>Subs Come here for {{subcategory}}</h1>\n    ",
                        providers: [AuctionHouseCategoryService_1.AuctionHouseCategoryService]
                    }), 
                    __metadata('design:paramtypes', [core_1.ElementRef, AuctionHouseCategoryService_1.AuctionHouseCategoryService])
                ], SubCategory);
                return SubCategory;
            }());
            exports_1("SubCategory", SubCategory);
        }
    }
});
//# sourceMappingURL=subcategory.js.map