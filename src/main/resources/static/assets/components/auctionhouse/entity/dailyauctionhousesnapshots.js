System.register([], function(exports_1) {
    var DailyAuctionHouseSnapshot;
    return {
        setters:[],
        execute: function() {
            DailyAuctionHouseSnapshot = (function () {
                function DailyAuctionHouseSnapshot(minimumBidCoppers, maximumBidCoppers, minimumBuyoutCoppers, maximumBuyoutCoppers, medianBidCoppers, medianBuyoutCoppers, stdevBidCoppers, stdevBuyoutCoppers, quantity, averageBidCoppers, averageBuyoutCoppers, exportTimePretty, actualExportTime, itemId, realmId) {
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
                    this.itemId = itemId;
                    this.realmId = realmId;
                }
                return DailyAuctionHouseSnapshot;
            })();
            exports_1("DailyAuctionHouseSnapshot", DailyAuctionHouseSnapshot);
        }
    }
});
//# sourceMappingURL=dailyauctionhousesnapshots.js.map