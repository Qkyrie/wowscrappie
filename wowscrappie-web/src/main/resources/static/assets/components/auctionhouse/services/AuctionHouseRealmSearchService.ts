import 'rxjs/add/operator/map';
import { Injectable } from 'angular2/core';
import { Http, Response, Request } from 'angular2/http';
import { AuctionHouseTopSeller } from '../entity/auctionhousetopseller';
import {Observable} from "rxjs/Observable";

@Injectable()
export class AuctionHouseRealmSearchService {
    constructor(public http:Http) {
    }

    searchForTopSellers(realm:number) {
        return this.http.get('/rest/auctionhouse/realm/'+ realm +'/topsellers/')
            .map((responseData) => {
                return responseData.json();
            })
            .map((elements:Object[]) => {
                if (elements) {
                    return elements.map(element => {
                            return new AuctionHouseTopSeller(
                                "Pikachü",
                                1000,
                                10000000000
                            );
                        }
                    );
                }
            });
    }


    searchForTopItems(realm:number) {
        return this.http.get('/rest/auctionhouse/realm/'+ realm +'/mostavailable')
            .map((responseData) => {
                return responseData.json();
            })
            .map((elements:Object[]) => {
                if (elements) {
                    return elements.map(element => {
                            return new AuctionHouseTopSeller(
                                "Draenic Intellect Flask",
                                1000,
                                10000000000
                            );
                        }
                    );
                }
            });
    }
}
