import {Component, ElementRef} from "angular2/core";
import {AuctionHouseCategoryService} from "../../services/AuctionHouseCategoryService";


@Component({
    selector: 'subcategory',
    template: `
      <h1>Subs Come here for {{subcategory}}</h1>
    `,
    providers: [AuctionHouseCategoryService]
})
export class SubCategory {
    subcategory:number;

    constructor(elm:ElementRef,
                public auctionhouseCategoryService:AuctionHouseCategoryService) {
        this.subcategory = elm.nativeElement.getAttribute('subcategory-id');
    }
}