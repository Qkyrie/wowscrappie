import 'rxjs/add/operator/map';
import { Injectable } from 'angular2/core';
import { Http, Response, Request } from 'angular2/http';
import { AuctionHouseSnapshot } from '../entity/auctionhousesnapshot';

@Injectable()
export class AuctionHouseItemSearchService {
    constructor(public http:Http) {
    }

    doSearch(itemId:number, realmId:number) {
        return this.http.get('/rest/auctionhouse/latest/item/' + itemId + '/realm/' + realmId)
            .map((responseData) => {
                return responseData.json();
            })
            .map((element: Object) => {
                if(element) {
                    console.log("raw return element:");
                    console.log(element);
                    return new AuctionHouseSnapshot(
                        element.minimumBidCoppers,
                        element.maximumBidCoppers,
                        element.minimumBuyoutCoppers,
                        element.maximumBuyoutCoppers,
                        element.medianBidCoppers,
                        element.medianBuyoutCoppers,
                        element.stdevBidCoppers,
                        element.stdevBuyoutCoppers,
                        element.quantity,
                        element.averageBidCoppers,
                        element.averageBuyoutCoppers,
                        element.exportTimePretty,
                        element.actualExportTime,
                        element.itemName,
                        element.itemId,
                        element.realmName,
                        element.realmId
                    );
                }
            });
    }

}
