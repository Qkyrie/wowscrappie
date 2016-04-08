import "rxjs/add/operator/map";
import {Injectable} from "angular2/core";
import {Http} from "angular2/http";
import {AuctionHouseTopItem} from "../entity/auctionhousetopitem";

@Injectable()
export class AuctionHouseRealmSearchService {
    constructor(public http:Http) {
    }

    searchForTopItems(realm:number) {
        return this.http.get('/rest/auctionhouse/realm/' + realm + '/mostavailable')
            .map((responseData) => {
                return responseData.json();
            })
            .map((elements:Object[]) => {
                if (elements) {
                    return elements.map(element => {
                            return new AuctionHouseTopItem(
                                element.name, element.totalQuantity
                            )
                        }
                    );
                }
            });
    }
}
