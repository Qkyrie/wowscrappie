import {Component, ElementRef} from 'angular2/core';
import {AuctionHouseRealmTopSellers} from './auctionhouserealmtopsellers'
import {AuctionHouseRealmTopItems} from './auctionhouserealmtopitems'

@Component({
    selector: 'auctionhouse-realm',
    template: `
            <div class="row">
                <div class="col-md-6">
                   <h3 class="text-center">Top Sellers</h3>
                   <auctionhouse-realm-top-sellers realmId="{{realmId}}"></auctionhouse-realm-top-sellers>
                </div>
                <div class="col-md-6">
                    <h3 class="text-center">Most Popular Items</h3>
                   <auctionhouse-realm-top-items realmId="{{realmId}}"></auctionhouse-realm-top-items>
                </div>
            </div>
    `,
    directives: [AuctionHouseRealmTopSellers, AuctionHouseRealmTopItems]
})
export class AuctionHouseRealm {
    realmId:number;

    constructor(elm:ElementRef) {
        this.realmId = elm.nativeElement.getAttribute('realm-id');
    }
}