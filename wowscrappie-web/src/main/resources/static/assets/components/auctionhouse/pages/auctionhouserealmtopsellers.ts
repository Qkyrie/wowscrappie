import {Component, Input, OnInit} from 'angular2/core';
import {AuctionHouseRealmSearchService} from "../services/AuctionHouseRealmSearchService";
import {AuctionHouseTopSeller} from "../entity/auctionhousetopseller";

@Component({
    selector: 'auctionhouse-realm-top-sellers',
    template: `
            <div class="row">
                <div class="col-md-12">
                    <h1>Realm Id: {{realmId}}</h1>
                    {{topSellers}}
                </div>
            </div>
    `,
    providers: [AuctionHouseRealmSearchService]
})
export class AuctionHouseRealmTopSellers implements OnInit {
    @Input("realmId") realmId: number;

    topSellers:AuctionHouseTopSeller[];

    constructor(public ahRealmSearchService:AuctionHouseRealmSearchService) {

    }
    
    ngOnInit() {
        this.ahRealmSearchService.searchForTopSellers(this.realmId)
            .subscribe((topSeller) => {
                this.topSellers = topSeller;
            }, (error) => {
            });
    }
}