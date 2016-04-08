System.register(["angular2/core", "./auctionhouserealmtopitems", "./auctionhousecategories"], function(exports_1, context_1) {
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
    var core_1, auctionhouserealmtopitems_1, auctionhousecategories_1;
    var AuctionHouseRealm;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (auctionhouserealmtopitems_1_1) {
                auctionhouserealmtopitems_1 = auctionhouserealmtopitems_1_1;
            },
            function (auctionhousecategories_1_1) {
                auctionhousecategories_1 = auctionhousecategories_1_1;
            }],
        execute: function() {
            AuctionHouseRealm = (function () {
                function AuctionHouseRealm(elm) {
                    this.realmId = elm.nativeElement.getAttribute('realm-id');
                }
                AuctionHouseRealm = __decorate([
                    core_1.Component({
                        selector: 'auctionhouse-realm',
                        template: "\n            <div class=\"row\">\n                <div class=\"col-md-6\">\n                    <h3 class=\"text-center\">Most Popular Items</h3>\n                   <auctionhouse-realm-top-items realmId=\"{{realmId}}\"></auctionhouse-realm-top-items>\n                </div>\n                 <div class=\"col-md-6\">\n                    <h3 class=\"text-center\">Categories</h3>\n                    <auctionhouse-categories></auctionhouse-categories>\n                </div>\n            </div>\n    ",
                        directives: [auctionhouserealmtopitems_1.AuctionHouseRealmTopItems, auctionhousecategories_1.AuctionHouseCategories]
                    }), 
                    __metadata('design:paramtypes', [core_1.ElementRef])
                ], AuctionHouseRealm);
                return AuctionHouseRealm;
            }());
            exports_1("AuctionHouseRealm", AuctionHouseRealm);
        }
    }
});
//# sourceMappingURL=auctionhouserealm.js.map