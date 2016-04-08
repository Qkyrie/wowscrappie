import {Component, ElementRef} from "angular2/core";
import {AuctionHouseRealmTopItems} from "./auctionhouserealmtopitems";
import {AuctionHouseCategories} from "./auctionhousecategories";

@Component({
    selector: 'auctionhouse-realm',
    template: `
            <div class="row">
                <div class="col-md-6">
                    <h3 class="text-center">Most Popular Items</h3>
                   <auctionhouse-realm-top-items realmId="{{realmId}}"></auctionhouse-realm-top-items>
                </div>
                 <div class="col-md-6">
                    <h3 class="text-center">Categories</h3>
                    <auctionhouse-categories></auctionhouse-categories>
                </div>
            </div>
    `,
    directives: [AuctionHouseRealmTopItems, AuctionHouseCategories]
})
export class AuctionHouseRealm {
    realmId:number;

    constructor(elm:ElementRef) {
        this.realmId = elm.nativeElement.getAttribute('realm-id');
    }
}