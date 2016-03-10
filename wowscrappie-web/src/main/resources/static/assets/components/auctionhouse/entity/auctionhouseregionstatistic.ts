import { Item } from '../items/item';

export class AuctionHouseRegionSnapshotStatistic {

    constructor(
        public item:Item,
        public locality:string,
        public totalQuantity:number,
        public averageQuantityPerServer:number,
        public medianQuantityPerServer:number,
        public medianBid:number,
        public medianBuyout:number,
        public averageBid:number,
        public averageBuyout:number
    ){}
}