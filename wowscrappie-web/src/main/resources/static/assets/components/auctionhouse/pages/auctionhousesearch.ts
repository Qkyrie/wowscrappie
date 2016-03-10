import {Component} from 'angular2/core';
import { SingleItemSearch } from './singleItemSearch'
import { MultiItemSearch } from './multiItemSearch'

@Component({
    selector: 'auctionhouse-search',
    template: `
               <div id="page-inner">

        <div class="row">
            <div class="col-md-12">
                <ul class="nav nav-pills" role="tablist">
                    <li role="presentation" id="homeTab" class="active"><a href="#singleItemSearch" aria-controls="home"
                                                                           role="tab" data-toggle="tab">Single Item
                        Search</a></li>
                    <li role="presentation" id="compareTab"><a href="#compareItemSearch" aria-controls="profile"
                                                               role="tab"
                                                               data-toggle="tab">Item Compare</a></li>
                </ul>
            </div>
        </div>

        <div class="tab-content">
            <div role="tabpanel" class="tab-pane fade in active" id="singleItemSearch">
                <single-search></single-search>
            </div>
            <div role="tabpanel" class="tab-pane fade in" id="compareItemSearch">
                <multi-search></multi-search>
            </div>
        </div>

    </div>
    `,
    directives: [SingleItemSearch, MultiItemSearch]
})
export class AuctionHouseSearch {

}