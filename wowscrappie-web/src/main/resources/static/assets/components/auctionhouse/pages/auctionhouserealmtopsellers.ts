import {Component, Input, OnInit} from 'angular2/core';
import {AuctionHouseRealmSearchService} from "../services/AuctionHouseRealmSearchService";
import {AuctionHouseTopSeller} from "../entity/auctionhousetopseller";

@Component({
    selector: 'auctionhouse-realm-top-sellers',
    template: `
                <div class="col-md-6">
                    <h4>by items</h4>
                    
                    <ul>
                        <li *ngFor="#topSeller of topSellersItems; var index=index">
                         {{topSeller.name}}
                        </li>
                    </ul>
                </div>
                <div class="col-md-6">
                    <h4>by amount</h4>
                    
                    <ul>
                        <li *ngFor="#topSeller of topSellersAmount; var index=index">
                         {{topSeller.name}}
                        </li>
                    </ul>
                </div>
    `,
    providers: [AuctionHouseRealmSearchService]
})
export class AuctionHouseRealmTopSellers implements OnInit {
    @Input("realmId") realmId:number;

    topSellersAmount:AuctionHouseTopSeller[];
    topSellersItems:AuctionHouseTopSeller[];

    constructor(public ahRealmSearchService:AuctionHouseRealmSearchService) {

    }

    ngOnInit() {
        this.ahRealmSearchService.searchForTopSellers(this.realmId)
            .subscribe((topSeller) => {
                this.topSellersAmount = topSeller;
                this.topSellersItems = topSeller;
            }, (error) => {
            });
    }
}