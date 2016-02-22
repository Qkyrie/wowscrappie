System.register([], function(exports_1) {
    var AuctionHouseSnapshot;
    return {
        setters:[],
        execute: function() {
            AuctionHouseSnapshot = (function () {
                function AuctionHouseSnapshot(minimumBidCoppers, maximumBidCoppers, minimumBuyoutCoppers, maximumBuyoutCoppers, medianBidCoppers, medianBuyoutCoppers, stdevBidCoppers, stdevBuyoutCoppers, quantity, averageBidCoppers, averageBuyoutCoppers, exportTimePretty, actualExportTime, itemName, itemId, realmName, realmId) {
                    this.minimumBidCoppers = minimumBidCoppers;
                    this.maximumBidCoppers = maximumBidCoppers;
                    this.minimumBuyoutCoppers = minimumBuyoutCoppers;
                    this.maximumBuyoutCoppers = maximumBuyoutCoppers;
                    this.medianBidCoppers = medianBidCoppers;
                    this.medianBuyoutCoppers = medianBuyoutCoppers;
                    this.stdevBidCoppers = stdevBidCoppers;
                    this.stdevBuyoutCoppers = stdevBuyoutCoppers;
                    this.quantity = quantity;
                    this.averageBidCoppers = averageBidCoppers;
                    this.averageBuyoutCoppers = averageBuyoutCoppers;
                    this.exportTimePretty = exportTimePretty;
                    this.actualExportTime = actualExportTime;
                    this.itemName = itemName;
                    this.itemId = itemId;
                    this.realmName = realmName;
                    this.realmId = realmId;
                }
                return AuctionHouseSnapshot;
            })();
            exports_1("AuctionHouseSnapshot", AuctionHouseSnapshot);
        }
    }
});
//# sourceMappingURL=auctionhousesnapshot.js.map