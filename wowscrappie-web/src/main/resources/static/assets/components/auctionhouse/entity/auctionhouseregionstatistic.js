System.register([], function(exports_1) {
    var AuctionHouseRegionSnapshotStatistic;
    return {
        setters:[],
        execute: function() {
            AuctionHouseRegionSnapshotStatistic = (function () {
                function AuctionHouseRegionSnapshotStatistic(item, locality, totalQuantity, averageQuantityPerServer, medianQuantityPerServer, medianBid, medianBuyout, averageBid, averageBuyout) {
                    this.item = item;
                    this.locality = locality;
                    this.totalQuantity = totalQuantity;
                    this.averageQuantityPerServer = averageQuantityPerServer;
                    this.medianQuantityPerServer = medianQuantityPerServer;
                    this.medianBid = medianBid;
                    this.medianBuyout = medianBuyout;
                    this.averageBid = averageBid;
                    this.averageBuyout = averageBuyout;
                }
                return AuctionHouseRegionSnapshotStatistic;
            })();
            exports_1("AuctionHouseRegionSnapshotStatistic", AuctionHouseRegionSnapshotStatistic);
        }
    }
});
//# sourceMappingURL=auctionhouseregionstatistic.js.map