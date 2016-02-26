import {Component, AfterViewChecked, ElementRef} from 'angular2/core';
/// <reference path="../../../typings/jquery/jquery.d.ts" />
/// <reference path="../../../typings/typeahead/typeahead.d.ts" />
/// <reference path="../../../typings/c3/c3.d.ts" />
import { AuctionHouseItemSearchService } from '../services/AuctionhouseItemSearchService';
import {AuctionHouseSnapshot} from "../entity/auctionhousesnapshot";
import { RealmService } from '../../realms/services/RealmService';
import { Realm } from '../../realms/entity/Realm';

@Component({
    selector: 'multi-search',
    providers: [AuctionHouseItemSearchService, RealmService],
    template: `
                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <div class="col-md-8 col-md-offset-2">
                            <div class="form-group label-floating">
                                <label class="control-label" for="itemSelectMulti">Item</label>
                                <div id="itemMulti">
                                    <input id="itemSelectMulti" class="form-control input-lg typeahead" type="text"/>
                                </div>
                            </div>
                        </div>

                        <div *ngIf="itemName != null" class="col-md-1">
                        <span (click)="doSearch()" class="btn btn-primary btn-lg" id="searchButtonMulti">
                            <i class="material-icons">search</i>
                        </span>
                        </div>
                    </div>
                </div>

                <div *ngIf="noInfoFoundWarning" class="alert alert-warning">
                    No Info was found for {{itemName}}
                </div>




                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <div id="resultChartMulti"></div>
                    </div>
                </div>

                <div *ngIf="lastSearchTerm != null"  class="row">
                 <div *ngFor="#searchTerm of allSearchTerms" class="col-md-4">
                     <h4>{{searchTerm.itemName}} statistics</h4>
                         <table class="table table-striped table-hover ">
                         <tbody>
                         <tr>
                         <td>Last Seen</td>
                         <td>{{searchTerm?.exportTimePretty}}</td>
                         </tr>
                         <tr>
                         <td>Quantity</td>
                         <td>{{searchTerm?.quantity}}</td>
                         </tr>
                         <tr>
                         <td>Median Price</td>
                         <td>{{transformPretty(searchTerm?.medianBuyoutCoppers)}}</td>
                         </tr>
                         <tr>
                         <td>Mean Price</td>
                         <td>{{transformPretty(searchTerm?.averageBuyoutCoppers)}}</td>
                         </tr>
                         <tr>
                         <td>Standard Deviation</td>
                         <td>{{transformPretty(searchTerm?.stdevBuyoutCoppers)}}</td>
                         </tr>
                         </tbody>
                         </table>
                     </div>
                 </div>
    `
})
export class MultiItemSearch {
    itemName:string = "item";
    itemId:number;

    items = Bloodhound;

    myRealm:Realm;

    noInfoFoundWarning = false;

    myObject = this;
    typeaheadBound = false;

    currentSnapshotChart:ChartAPI;

    lastSearchTerm:AuctionHouseSnapshot;
    allSearchTerms:AuctionHouseSnapshot[] = [];

    initTypeAhead() {
        this.items = new Bloodhound({
            datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
            queryTokenizer: Bloodhound.tokenizers.whitespace,
            remote: {
                url: '/rest/items/query?search=%QUERY',
                wildcard: '%QUERY'
            }
        });

        var currentObject = this;


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

        return true;
    }

    constructor(public ahSearchService:AuctionHouseItemSearchService,
                public realmService:RealmService) {
        realmService.findCurrent()
            .subscribe((currentRealm) => {
                this.myRealm = currentRealm;
            }, (error) => {
            });
    }

    generateOrLoadChart() {
        if (this.currentSnapshotChart) {
            this.loadIntoChart();
        } else {
            this.generateChart();
        }
    }

    loadIntoChart() {
        this.currentSnapshotChart.load({
            columns: [
                [this.lastSearchTerm.itemName,
                    this.lastSearchTerm.averageBidCoppers,
                    this.lastSearchTerm.medianBidCoppers,
                    this.lastSearchTerm.averageBuyoutCoppers,
                    this.lastSearchTerm.medianBuyoutCoppers]
            ]
        });
    }

    generateChart() {
        this.currentSnapshotChart = c3.generate({
            bindto: '#resultChartMulti',
            data: {
                columns: [
                    [this.lastSearchTerm.itemName,
                        this.lastSearchTerm.averageBidCoppers,
                        this.lastSearchTerm.medianBidCoppers,
                        this.lastSearchTerm.averageBuyoutCoppers,
                        this.lastSearchTerm.medianBuyoutCoppers
                    ]
                ],
                type: 'bar',
                labels: {
                    format: function (v, id, i, j) {
                        var coppers:number = v;
                        var actualCoppers = Math.floor(+coppers % 100);
                        var silverAndCoppers = Math.floor(+coppers / 100);
                        var silvers = Math.floor(silverAndCoppers % 100);
                        var gold = Math.floor(silverAndCoppers / 100);
                        return gold + 'g ' + silvers + 's ' + actualCoppers + 'c';
                    }
                },
            },
            axis: {
                x: {
                    type: 'category',
                    categories: ['Average Bid', 'Median Bid',
                        'Average Buyout', 'Median Buyout']
                },
                y: {
                    tick: {
                        format: function (value) {
                            var actualCoppers = Math.floor(+value % 100);
                            var silverAndCoppers = Math.floor(+value / 100);
                            var silvers = Math.floor(silverAndCoppers % 100);
                            var gold = Math.floor(silverAndCoppers / 100);
                            return gold + 'g ' + silvers + 's ' + actualCoppers + 'c';
                        }
                    }
                }
            },
            tooltip: {
                show: false
            }

        });
    }

    public ngAfterViewChecked():void {
        if (!this.typeaheadBound) {
            this.typeaheadBound = this.initTypeAhead();
        }
    }

    transformPretty(amount) {
        var actualCoppers = Math.floor(+amount % 100);
        var silverAndCoppers = Math.floor(+amount / 100);
        var silvers = Math.floor(silverAndCoppers % 100);
        var gold = Math.floor(silverAndCoppers / 100);
        return gold + 'g ' + silvers + 's ' + actualCoppers + 'c';
    }

    doSearch() {
        this.noInfoFoundWarning = false;
        this.ahSearchService.searchForItemAndRealm(this.itemId, this.myRealm.id)
            .subscribe(searchResult => {
                    this.lastSearchTerm = searchResult;
                    this.allSearchTerms.push(searchResult);
                    this.generateOrLoadChart();
                },
                (err)=> {
                    this.noInfoFoundWarning = true;
                    console.log(err);
                },
                ()=> {
                }
            );
    }
}