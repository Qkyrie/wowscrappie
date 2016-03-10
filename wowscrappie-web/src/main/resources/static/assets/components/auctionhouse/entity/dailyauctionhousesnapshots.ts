export class DailyAuctionHouseSnapshot {
    constructor(public minimumBidCoppers:number,
                public maximumBidCoppers:number,
                public minimumBuyoutCoppers:number,
                public maximumBuyoutCoppers:number,
                public medianBidCoppers:number,
                public medianBuyoutCoppers:number,
                public stdevBidCoppers:number,
                public stdevBuyoutCoppers:number,
                public quantity:number,
                public averageBidCoppers:number,
                public averageBuyoutCoppers:number,
                public exportTimePretty:string,
                public actualExportTime:string,
                public itemId:number,
                public realmId:number) {
    }
}