import {AuctionHouseSubCategory} from "./auctionhousesubcategory";

export class AuctionHouseCategory {
    constructor(public id:number,
                public name:string,
                public slug:string,
                public subCategories:AuctionHouseSubCategory) {
    }
}