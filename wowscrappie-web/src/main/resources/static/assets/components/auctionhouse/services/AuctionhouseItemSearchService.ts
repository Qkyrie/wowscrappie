import 'rxjs/add/operator/map';
import { Injectable } from 'angular2/core';
import { Http, Response, Request } from 'angular2/http';
import { AuctionHouseSnapshot } from '../entity/auctionhousesnapshot';
import { AuctionHouseRegionSnapshotStatistic } from '../entity/auctionhouseregionstatistic';
import { DailyAuctionHouseSnapshot } from '../entity/dailyauctionhousesnapshots';
import { Item } from '../items/item';
import {Observable} from "rxjs/Observable";

@Injectable()
export class AuctionHouseItemSearchService {
    constructor(public http:Http) {
    }

    searchDailyForItemAndRealm(itemId:number, realm:number) {
        return this.http.get('/rest/auctionhouse/historic/item/' + itemId + '/realm/' + realm)
            .map((responseData) => {
                return responseData.json();
            })
            .map((elements:Object[]) => {
                if (elements) {
                    return elements.map(element => {
                           return new DailyAuctionHouseSnapshot(
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
                                element.itemId,
                                element.realmId
                            );
                        }
                    );
                }
            }).catch((error) => {
                return Observable.empty<any>();
            });
    }

    searchForItemAndLocality(itemId:number, locality:string) {
        return this.http.get('/rest/auctionhouse/latest/item/' + itemId + '/locality/' + locality)
            .map((responseData) => {
                return responseData.json();
            })
            .map((element:Object) => {
                if (element) {
                    return new AuctionHouseRegionSnapshotStatistic(
                        new Item(element.item.name, element.item.id),
                        element.locality,
                        element.totalQuantity,
                        element.averageQuantityPerServer,
                        element.medianQuantityPerServer,
                        element.medianBid,
                        element.medianBuyout,
                        element.averageBid,
                        element.averageBuyout
                    )
                }
            }).catch((error) => {
                return Observable.empty<any>();
            });
    }

    searchForItemAndRealm(itemId:number, realmId:number) {
        return this.http.get('/rest/auctionhouse/latest/item/' + itemId + '/realm/' + realmId)
            .map((responseData) => {
                return responseData.json();
            })
            .map((element:Object) => {
                if (element) {
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
            })
            .catch((error) => {
                return Observable.empty<any>();
            });
    }

}
