import {Component, OnInit} from "angular2/core";
import {AuctionHouseCategoryService} from "../services/AuctionHouseCategoryService";
import {AuctionHouseCategory} from "../entity/auctionhousecategory";

@Component({
    selector: 'auctionhouse-categories',
    template: `
                        <div class="col-md-6" *ngFor="#category of categories; var index=index">
                         <h4>{{category.name}}</h4>
                         <ul>
                                <li *ngFor="#subCategory of category.subCategories">
                                    <a (click)="visitSubSection(subCategory.id, $event)" href="#" >{{subCategory.name}}</a>
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

    getFullUrl() {
        var fullbaseUrlMeta = $("meta[name=_fullbaseurl]");
        return fullbaseUrlMeta.attr("content");
    }

    visitSubSection(id:number, e) {
        e.preventDefault();
        window.location.href = this.getFullUrl() + "/auctionhouse/subcategory/" + id;
    }


    ngOnInit() {
        this.auctionhouseCategoryService.categories()
            .subscribe((categories) => {
                this.categories = categories;
            }, (error) => {
            });
    }
}