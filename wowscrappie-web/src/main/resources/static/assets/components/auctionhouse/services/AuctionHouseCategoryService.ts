import "rxjs/add/operator/map";
import {Injectable} from "angular2/core";
import {Http} from "angular2/http";
import {AuctionHouseCategory} from "../entity/auctionhousecategory";
import {AuctionHouseSubCategory} from "../entity/auctionhousesubcategory";

@Injectable()
export class AuctionHouseCategoryService {
    constructor(public http:Http) {
    }

    categories() {
        return this.http.get('/rest/auctionhouse/category')
            .map((responseData) => {
                return responseData.json();
            })
            .map((elements:Object[]) => {
                if (elements) {
                    return elements.map(element => {
                            return new AuctionHouseCategory(
                                element.id,
                                element.name,
                                element.slug,
                                element.subCategories.map(
                                    subElement => {
                                        return new AuctionHouseSubCategory(
                                            subElement.id,
                                            subElement.name,
                                            subElement.slug
                                        );
                                    }
                                )
                            )
                        }
                    );
                }
            });
    }
}
