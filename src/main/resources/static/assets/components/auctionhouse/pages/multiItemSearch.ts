import {Component, AfterViewChecked, ElementRef} from 'angular2/core';
/// <reference path="../../../typings/jquery/jquery.d.ts" />
/// <reference path="../../../typings/typeahead/typeahead.d.ts" />
/// <reference path="../../../typings/c3/c3.d.ts" />
import { AuctionHouseItemSearchService } from '../services/AuctionhouseItemSearchService';
import {AuctionHouseSnapshot} from "../entity/auctionhousesnapshot";

@Component({
    selector: 'multi-search',
    providers: [AuctionHouseItemSearchService],
    template: `
                <div class="row">
                    <div class="col-md-12">
                        <h1>Search for multiple items on multiple realms.</h1>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <div class="col-md-5 col-md-offset-1">
                            <div class="form-group label-floating">
                                <label class="control-label" for="itemSelectMulti">Item</label>
                                <div id="itemMulti">
                                    <input id="itemSelectMulti" class="form-control input-lg typeahead" type="text"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-5">
                            <div class="form-group label-floating">
                                <label class="control-label" for="realmSelectMulti">Realm</label>
                                <div id="realmMulti">
                                    <input id="realmSelectMulti" class="form-control input-lg typeahead" type="text"/>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-1">
                        <span (click)="doSearch()" class="btn btn-primary btn-lg" id="searchButtonMulti">
                            <i class="material-icons">search</i>
                        </span>
                        </div>
                    </div>
                </div>

                <div  *ngIf="lastSearchTerm != null" class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <h3>Last update date for {{itemName}} on {{realmName}}: {{lastSearchTerm?.exportTimePretty}}</h3>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <div id="resultChartMulti"></div>
                    </div>
                </div>
    `
})
export class MultiItemSearch {
    itemName:string = "item";
    realmName:string = "a realm";
    itemId: number;
    realmId: number;

    items = Bloodhound;
    realms = Bloodhound;

    myObject = this;
    typeaheadBound = false;

    currentSnapshotChart:ChartAPI;

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

        $('#realmMulti .typeahead').typeahead(null, {
            name: 'items',
            display: 'fullName',
            source: currentObject.realms
        });

        $('#itemMulti .typeahead').typeahead(null, {
            name: 'items',
            display: 'name',
            source: currentObject.items
        });

        $('#itemSelectMulti').bind('typeahead:selected', function (obj, datum, name) {
            console.log(datum);
            currentObject.itemId = datum.id;
            currentObject.itemName = datum.name;
        });

        $('#realmSelectMulti').bind('typeahead:selected', function (obj, datum, name) {
            console.log(datum);
            currentObject.realmId = datum.id;
            currentObject.realmName = datum.name;
        });

        return true;
    }

    constructor(public ahSearchService:AuctionHouseItemSearchService) {

    }

    generateOrLoadChart() {
        if(this.currentSnapshotChart) {
            this.loadIntoChart();
        } else {
            this.generateChart();
        }
    }

    loadIntoChart(){
        console.log("loading into existing chart");
        this.currentSnapshotChart.load({
            columns: [
                [   this.lastSearchTerm.itemName,
                    this.lastSearchTerm.minimumBidCoppers,
                    this.lastSearchTerm.averageBidCoppers,
                    this.lastSearchTerm.maximumBidCoppers,
                    this.lastSearchTerm.minimumBuyoutCoppers,
                    this.lastSearchTerm.averageBuyoutCoppers,
                    this.lastSearchTerm.maximumBuyoutCoppers]
            ]
        });
    }

    generateChart() {
        console.log("generating chart from");
        console.log(this.lastSearchTerm);
        this.currentSnapshotChart = c3.generate({
            bindto: '#resultChartMulti',
            data: {
                columns: [
                    [   this.lastSearchTerm.itemName,
                        this.lastSearchTerm.minimumBidCoppers,
                        this.lastSearchTerm.averageBidCoppers,
                        this.lastSearchTerm.maximumBidCoppers,
                        this.lastSearchTerm.minimumBuyoutCoppers,
                        this.lastSearchTerm.averageBuyoutCoppers,
                        this.lastSearchTerm.maximumBuyoutCoppers
                    ]
                ],
                type: 'bar'
            },
            axis: {
                x: {
                    type: 'category',
                    categories: ['Minimum Bid', 'Average Bid', 'Maximum Bid',
                        'Minimum Buyout', 'Average Buyout', 'Maximum Buyout']
                }
            },
            tooltip: {
                contents: function (d, defaultTitleFormat, defaultValueFormat, color) {
                    var coppers: string = d[0].value;
                    var actualCoppers = Math.floor(+coppers % 100);
                    var silverAndCoppers = Math.floor(+coppers / 100);
                    var silvers = Math.floor(silverAndCoppers % 100);
                    var gold = Math.floor(silverAndCoppers / 100);

                    return gold + 'g ' + silvers + 's ' + actualCoppers + 'c';
                }
            }
        });
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
                console.log("generating or loading");
                this.generateOrLoadChart();
            });
    }
}