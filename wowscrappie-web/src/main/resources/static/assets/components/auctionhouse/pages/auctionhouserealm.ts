import {Component, ElementRef} from 'angular2/core';
import { AuctionHouseRealmTopSellers } from './auctionhouserealmtopsellers'

@Component({
    selector: 'auctionhouse-realm',
    template: `
            <div class="row">
                <div class="col-md-12">
                   <div class="col-md-6">
                        <auctionhouse-realm-top-sellers realmId="{{realmId}}"></auctionhouse-realm-top-sellers>
                    </div>
                </div>
            </div>
    `,
    directives: [AuctionHouseRealmTopSellers],
    inputs: ["whatever"]

})
export class AuctionHouseRealm {
    realmId:number; 

    constructor(elm: ElementRef) {
        this.realmId = elm.nativeElement.getAttribute('realm-id');
    }
}