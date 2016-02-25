import {Component, AfterViewChecked, ElementRef} from 'angular2/core';
/// <reference path="../../../typings/jquery/jquery.d.ts" />
/// <reference path="../../../typings/typeahead/typeahead.d.ts" />
/// <reference path="../../../typings/c3/c3.d.ts" />
import { AuctionHouseItemSearchService } from '../services/AuctionhouseItemSearchService';
import { RealmService } from '../../realms/services/RealmService';
import { Realm } from '../../realms/entity/Realm';
import {AuctionHouseSnapshot} from "../entity/auctionhousesnapshot";
import {AuctionHouseRegionSnapshotStatistic} from "../entity/auctionhouseregionstatistic";

@Component({
    selector: 'single-search',
    providers: [AuctionHouseItemSearchService, RealmService],
    template: `
                <div *ngIf="itemName != null" class="row">
                    <div class="col-md-12">
                        <h1>Search information about {{itemName}} on {{myRealm?.locality}}-{{myRealm?.name}}.</h1>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-8 col-md-offset-2">
                        <div class="col-md-8 col-md-offset-2">
                            <div class="form-group label-floating">
                                <label class="control-label" for="itemSelectSingle">Item</label>
                                <div id="itemSingle">
                                    <input id="itemSelectSingle" class="form-control input-lg typeahead" type="text"/>
                                </div>
                            </div>
                        </div>
                        <div *ngIf="itemName != null" class="col-md-1">
                        <span (click)="doSearch()" class="btn btn-primary btn-lg" id="searchButtonSingle">
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
                        <div id="resultChartSingle"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5 col-md-offset-1">
                        <h3 *ngIf="lastSearchTerm != null">{{lastSearchTerm?.itemName}} @ {{lastSearchTerm?.realmName}}</h3>
                    </div>
                    <div class="col-md-5">
                        <h3 *ngIf="lastRegionSearchTerm != null">{{lastRegionSearchTerm?.item?.name}} @ {{lastRegionSearchTerm?.locality}} servers</h3>
                    </div>
                </div>
                <div class="row">
                    <div *ngIf="lastSearchTerm != null" class="col-md-5 col-md-offset-1">
                        <table class="table table-striped table-hover ">
                          <tbody>
                              <tr>
                                <td>Last Seen</td>
                                <td>{{lastSearchTerm?.exportTimePretty}}</td>
                              </tr>
                               <tr>
                                <td>Quantity</td>
                                <td>{{lastSearchTerm?.quantity}}</td>
                              </tr>
                                  <tr>
                                <td>Median Price</td>
                                <td>{{transformPretty(lastSearchTerm?.medianBuyoutCoppers)}}</td>
                              </tr>
                              <tr>
                                <td>Mean Price</td>
                                <td>{{transformPretty(lastSearchTerm?.averageBuyoutCoppers)}}</td>
                              </tr>
                              <tr>
                                <td>Standard Deviation</td>
                                <td>{{transformPretty(lastSearchTerm?.stdevBuyoutCoppers)}}</td>
                              </tr>
                          </tbody>
                        </table>
                    </div>
                    <div *ngIf="lastRegionSearchTerm != null" class="col-md-5">
                      <table class="table table-striped table-hover ">
                        <tbody>
                            <tr>
                              <td>EU Quantity</td>
                              <td>{{lastRegionSearchTerm?.totalQuantity}}</td>
                            </tr>
                            <tr>
                              <td>EU Median Price</td>
                              <td>{{transformPretty(lastRegionSearchTerm?.medianBuyout)}}</td>
                            </tr>
                            <tr>
                              <td>EU Mean Price</td>
                              <td>{{transformPretty(lastRegionSearchTerm?.averageBuyout)}}</td>
                            </tr>
                        </tbody>
                      </table>
                    </div>
                    <div class="col-md-1"></div>
                </div>
    `
})
export class SingleItemSearch {
    itemName:string;
    itemId:number;

    myRealm:Realm;

    items = Bloodhound;

    noInfoFoundWarning = false;

    myObject = this;
    typeaheadBound = false;

    currentSnapshotChart:ChartAPI;

    lastSearchTerm:AuctionHouseSnapshot;
    lastRegionSearchTerm:AuctionHouseRegionSnapshotStatistic;

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

        $('#itemSingle .typeahead').typeahead(null, {
            name: 'items',
            display: 'name',
            source: currentObject.items
        });

        $('#itemSelectSingle').bind('typeahead:selected', function (obj, datum, name) {
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

    generateOrLoadChart(data, type) {
        if (this.currentSnapshotChart) {
            this.loadIntoChart(data, type);
        } else {
            this.generateChart(data, type);
        }
    }

    loadIntoChart(data, type) {
        this.currentSnapshotChart.load({
            columns: data,
            unload: this.currentSnapshotChart.columns
        });
    }

    generateChart(columns, type) {
        this.currentSnapshotChart = c3.generate({
            bindto: '#resultChartSingle',
            data: {
                columns: columns,
                type: type,
                labels: {
                    format: function (v, id, i, j) {
                        var coppers:number = v;
                        var actualCoppers = Math.floor(+coppers % 100);
                        var silverAndCoppers = Math.floor(+coppers / 100);
                        var silvers = Math.floor(silverAndCoppers % 100);
                        var gold = Math.floor(silverAndCoppers / 100);
                        return gold + 'g ' + silvers + 's ' + actualCoppers + 'c';
                    }
                }
            },
            tooltip: {
                show: false
            },
            axis: {
                x: {
                    type: 'category',
                    categories: ['Average Bid', 'Median Bid', 'Average Buyout', 'Median Buyout']
                }
            }
        });
    }

    transformPretty(amount  ) {
        var actualCoppers = Math.floor(+amount % 100);
        var silverAndCoppers = Math.floor(+amount / 100);
        var silvers = Math.floor(silverAndCoppers % 100);
        var gold = Math.floor(silverAndCoppers / 100);
        return gold + 'g ' + silvers + 's ' + actualCoppers + 'c';
    }

    public ngAfterViewChecked():void {
        if (!this.typeaheadBound) {
            this.typeaheadBound = this.initTypeAhead();
        }
    }

    doSearch() {
        this.noInfoFoundWarning = false;
        this.ahSearchService.searchForItemAndRealm(this.itemId, this.myRealm.id)
            .subscribe(searchResult => {
                    this.lastSearchTerm = searchResult;
                    this.ahSearchService.searchForItemAndLocality(this.itemId, this.myRealm.locality)
                        .subscribe(result => {
                                this.lastRegionSearchTerm = result;
                                this.generateOrLoadChart([
                                    [this.lastSearchTerm.itemName,
                                        this.lastSearchTerm.averageBidCoppers,
                                        this.lastSearchTerm.medianBidCoppers,
                                        this.lastSearchTerm.averageBuyoutCoppers,
                                        this.lastSearchTerm.medianBuyoutCoppers
                                    ],
                                    [this.lastRegionSearchTerm.item.name + ' (' + this.lastRegionSearchTerm.locality + ')',
                                        this.lastRegionSearchTerm.averageBid,
                                        this.lastRegionSearchTerm.medianBid,
                                        this.lastRegionSearchTerm.averageBuyout,
                                        this.lastRegionSearchTerm.medianBuyout
                                    ]
                                ], 'bar');
                            },
                            (err)=> {
                                console.log(err);
                            },
                            ()=> {
                            }
                        );
                },
                (err)=> {
                    this.noInfoFoundWarning = true;
                },
                ()=>console.log("Done")
            );
    }
}
