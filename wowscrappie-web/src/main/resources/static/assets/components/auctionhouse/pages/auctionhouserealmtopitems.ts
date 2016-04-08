import {Component, Input, OnInit} from 'angular2/core';
import {AuctionHouseRealmSearchService} from "../services/AuctionHouseRealmSearchService";
import {AuctionHouseTopItem} from "../entity/auctionhousetopitem";

@Component({
    selector: 'auctionhouse-realm-top-items',
    template: `
                <div class="col-md-12">
                    <h4>by count</h4>
                    
                    <ul>
                        <li *ngFor="#topItem of topItemsCount; var index=index">
                         {{topItem.name}}
                        </li>
                    </ul>
                </div>
    `,
    providers: [AuctionHouseRealmSearchService]
})
export class AuctionHouseRealmTopItems implements OnInit {
    @Input("realmId") realmId:number;

    topItemsCount:AuctionHouseTopItem[];

    constructor(public ahRealmSearchService:AuctionHouseRealmSearchService) {

    }

    ngOnInit() {
        this.ahRealmSearchService.searchForTopItems(this.realmId)
            .subscribe((topSeller) => {
                this.topItemsCount = topSeller;
            }, (error) => {
            });
    }
}