export class AuctionHouseSnapshot {
    minimumBidCoppers: number;
    maximumBidCoppers: number;
    minimumBuyoutCoppers: number;
    maximumBuyoutCoppers: number;
    medianBidCoppers: number;
    medianBuyoutCoppers: number;
    stdevBidCoppers: number;
    stdevBuyoutCoppers: number;
    quantity: number;
    averageBidCoppers: number;
    averageBuyoutCoppers: number;
    exportTimePretty: string;
    actualExportTime: string;
    itemName: string;
    itemId: number;
    realmName: string;
    realmId: number;


    constructor(minimumBidCoppers:number, maximumBidCoppers:number, minimumBuyoutCoppers:number, maximumBuyoutCoppers:number, medianBidCoppers:number, medianBuyoutCoppers:number, stdevBidCoppers:number, stdevBuyoutCoppers:number, quantity:number, averageBidCoppers:number, averageBuyoutCoppers:number, exportTimePretty:string, actualExportTime:string, itemName:string, itemId:number, realmName:string, realmId:number) {
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


}