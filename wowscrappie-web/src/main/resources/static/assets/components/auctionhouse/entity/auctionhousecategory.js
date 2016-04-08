System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var AuctionHouseCategory;
    return {
        setters:[],
        execute: function() {
            AuctionHouseCategory = (function () {
                function AuctionHouseCategory(id, name, slug, subCategories) {
                    this.id = id;
                    this.name = name;
                    this.slug = slug;
                    this.subCategories = subCategories;
                }
                return AuctionHouseCategory;
            }());
            exports_1("AuctionHouseCategory", AuctionHouseCategory);
        }
    }
});
//# sourceMappingURL=auctionhousecategory.js.map