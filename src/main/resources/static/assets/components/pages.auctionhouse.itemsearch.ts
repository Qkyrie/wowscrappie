import {Component, AfterViewChecked, ElementRef} from 'angular2/core';
/// <reference path="../typings/jquery/jquery.d.ts" />
import { AuctionHouseItemSearchService } from './auctionhouse/services/AuctionhouseItemSearchService';
import {AuctionHouseSnapshot} from "./auctionhouse/entity/auctionhousesnapshot";

@Component({
    selector: 'single-search',
    providers: [AuctionHouseItemSearchService],
    template: `
                <div class="row">
                    <div class="col-md-12">
                        <h1>Search information about item on a realm.</h1>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <div class="col-md-5 col-md-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label" for="itemSelectSingle">Item</label>
                                <div id="itemSingle">
                                    <input id="itemSelectSingle" class="form-control input-lg typeahead" type="text"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="form-group label-floating">
                                <label class="control-label" for="realmSelectSingle">Realm</label>
                                <div id="realmSingle">
                                    <input id="realmSelectSingle" class="form-control input-lg typeahead" type="text"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-1">
                        <span (click)="doSearch()" class="btn btn-primary btn-lg" id="searchButtonSingle">
                            <i class="material-icons">search</i>
                        </span>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <h3>Last update date for {{itemName}} on {{realmName}}: {{lastSearchTerm?.exportTimePretty}}</h3>
                    </div>
                </div>
    `
})
export class SingleItemSearch {
    itemName:string;
    realmName:string;
    itemId: number;
    realmId: number;

    items = Bloodhound;
    realms = Bloodhound;

    myObject = this;
    typeaheadBound = false;

    lastSearchTerm:AuctionHouseSnapshot;

    initTypeAhead() {
        this.items = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            remote: {
                url: '/rest/items/query?search=%QUERY',
                wildcard: '%QUERY'
            }
        });

        this.realms = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            remote: {
                url: '/rest/realms/query?search=%QUERY',
                wildcard: '%QUERY'
            }
        });

        var currentObject = this;

        $('#realmSingle .typeahead').typeahead(null, {
            name: 'items',
            display: 'fullName',
            source: currentObject.realms
        });

        $('#itemSingle .typeahead').typeahead(null, {
            name: 'items',
            display: 'name',
            source: currentObject.items
        });

        $('#itemSelectSingle').bind('typeahead:selected', function (obj, datum, name) {
            console.log(datum);
            currentObject.itemId = datum.id;
            currentObject.itemName = datum.name;
        });

        $('#realmSelectSingle').bind('typeahead:selected', function (obj, datum, name) {
            console.log(datum);
            currentObject.realmId = datum.id;
            currentObject.realmName = datum.name;
        });

        return true;
    }

    constructor(public ahSearchService:AuctionHouseItemSearchService) {

    }

    public ngAfterViewChecked(): void {
        if(!this.typeaheadBound) {
            this.typeaheadBound = this.initTypeAhead();
        }
    }

    doSearch() {
        this.ahSearchService.doSearch(this.itemId, this.realmId)
            .subscribe(searchResult => {
                this.lastSearchTerm = searchResult;
            });
    }
}