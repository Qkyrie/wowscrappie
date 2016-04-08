import {Component, Input, OnInit} from "angular2/core";
import {AuctionHouseCategoryService} from "../services/AuctionHouseCategoryService";
import {AuctionHouseCategory} from "../entity/auctionhousecategory";

@Component({
    selector: 'auctionhouse-categories',
    template: `
                <div class="col-md-6">
                    <ul>
                        <li *ngFor="#category of categories; var index=index">
                         {{category.name}}
                        </li>
                    </ul>
                </div>
    `,
    providers: [AuctionHouseCategoryService]
})
export class AuctionHouseCategories implements OnInit {

    categories:AuctionHouseCategory[];

    constructor(public auctionhouseCategoryService:AuctionHouseCategoryService) {

    }

    ngOnInit() {
        this.auctionhouseCategoryService.categories()
            .subscribe((categories) => {
                this.categories = categories;
            }, (error) => {
            });
    }
}