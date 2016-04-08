import {Component, ElementRef} from "angular2/core";
import {AuctionHouseRealmTopItems} from "./auctionhouserealmtopitems";
import {AuctionHouseCategories} from "./auctionhousecategories";

@Component({
    selector: 'auctionhouse-realm',
    template: `
      <section class="main-container">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <h3 class="text-center">Most Popular Items</h3>
                       <auctionhouse-realm-top-items realmId="{{realmId}}"></auctionhouse-realm-top-items>
                    </div>
                </div>
             </div>
       </section>
            
            <section class="dark-translucent-bg pv-30">
                <div class="container">
                  <div class="row">
                    <auctionhouse-categories></auctionhouse-categories>
                  </div>
                </div>
          
            </section>
    `,
    directives: [AuctionHouseRealmTopItems, AuctionHouseCategories]
})
export class AuctionHouseRealm {
    realmId:number;

    constructor(elm:ElementRef) {
        this.realmId = elm.nativeElement.getAttribute('realm-id');
    }
}