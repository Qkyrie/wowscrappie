System.register(['angular2/core', '../services/AuctionhouseItemSearchService', '../../realms/services/RealmService'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, AuctionhouseItemSearchService_1, RealmService_1;
    var SingleItemSearch;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (AuctionhouseItemSearchService_1_1) {
                AuctionhouseItemSearchService_1 = AuctionhouseItemSearchService_1_1;
            },
            function (RealmService_1_1) {
                RealmService_1 = RealmService_1_1;
            }],
        execute: function() {
            SingleItemSearch = (function () {
                function SingleItemSearch(ahSearchService, realmService) {
                    var _this = this;
                    this.ahSearchService = ahSearchService;
                    this.realmService = realmService;
                    this.items = Bloodhound;
                    this.noInfoFoundWarning = false;
                    this.myObject = this;
                    this.typeaheadBound = false;
                    realmService.findCurrent()
                        .subscribe(function (currentRealm) {
                        _this.myRealm = currentRealm;
                    }, function (error) {
                    });
                }
                SingleItemSearch.prototype.initTypeAhead = function () {
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
                };
                SingleItemSearch.prototype.generateOrLoadChart = function (data, type) {
                    if (this.currentSnapshotChart) {
                        this.loadIntoChart(data, type);
                    }
                    else {
                        this.generateChart(data, type);
                    }
                };
                SingleItemSearch.prototype.loadIntoChart = function (data, type) {
                    this.currentSnapshotChart.load({
                        columns: data,
                        unload: this.currentSnapshotChart.columns
                    });
                };
                SingleItemSearch.prototype.generateChart = function (columns, type) {
                    this.currentSnapshotChart = c3.generate({
                        bindto: '#resultChartSingle',
                        data: {
                            columns: columns,
                            type: type,
                            labels: {
                                format: function (v, id, i, j) {
                                    var coppers = v;
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
                };
                SingleItemSearch.prototype.transformPretty = function (amount) {
                    var actualCoppers = Math.floor(+amount % 100);
                    var silverAndCoppers = Math.floor(+amount / 100);
                    var silvers = Math.floor(silverAndCoppers % 100);
                    var gold = Math.floor(silverAndCoppers / 100);
                    return gold + 'g ' + silvers + 's ' + actualCoppers + 'c';
                };
                SingleItemSearch.prototype.ngAfterViewChecked = function () {
                    if (!this.typeaheadBound) {
                        this.typeaheadBound = this.initTypeAhead();
                    }
                };
                SingleItemSearch.prototype.doSearch = function () {
                    var _this = this;
                    this.noInfoFoundWarning = false;
                    this.ahSearchService.searchForItemAndRealm(this.itemId, this.myRealm.id)
                        .subscribe(function (searchResult) {
                        _this.lastSearchTerm = searchResult;
                        _this.ahSearchService.searchForItemAndLocality(_this.itemId, _this.myRealm.locality)
                            .subscribe(function (result) {
                            _this.lastRegionSearchTerm = result;
                            _this.generateOrLoadChart([
                                [_this.lastSearchTerm.itemName,
                                    _this.lastSearchTerm.averageBidCoppers,
                                    _this.lastSearchTerm.medianBidCoppers,
                                    _this.lastSearchTerm.averageBuyoutCoppers,
                                    _this.lastSearchTerm.medianBuyoutCoppers
                                ],
                                [_this.lastRegionSearchTerm.item.name + ' (' + _this.lastRegionSearchTerm.locality + ')',
                                    _this.lastRegionSearchTerm.averageBid,
                                    _this.lastRegionSearchTerm.medianBid,
                                    _this.lastRegionSearchTerm.averageBuyout,
                                    _this.lastRegionSearchTerm.medianBuyout
                                ]
                            ], 'bar');
                        }, function (err) {
                            console.log(err);
                        }, function () {
                        });
                    }, function (err) {
                        _this.noInfoFoundWarning = true;
                    }, function () { return console.log("Done"); });
                };
                SingleItemSearch = __decorate([
                    core_1.Component({
                        selector: 'single-search',
                        providers: [AuctionhouseItemSearchService_1.AuctionHouseItemSearchService, RealmService_1.RealmService],
                        template: "\n                <div *ngIf=\"itemName != null\" class=\"row\">\n                    <div class=\"col-md-12\">\n                        <h1>Search information about {{itemName}} on {{myRealm?.locality}}-{{myRealm?.name}}.</h1>\n                    </div>\n                </div>\n\n                <div class=\"row\">\n                    <div class=\"col-md-8 col-md-offset-2\">\n                        <div class=\"col-md-8 col-md-offset-2\">\n                            <div class=\"form-group label-floating\">\n                                <label class=\"control-label\" for=\"itemSelectSingle\">Item</label>\n                                <div id=\"itemSingle\">\n                                    <input id=\"itemSelectSingle\" class=\"form-control input-lg typeahead\" type=\"text\"/>\n                                </div>\n                            </div>\n                        </div>\n                        <div *ngIf=\"itemName != null\" class=\"col-md-1\">\n                        <span (click)=\"doSearch()\" class=\"btn btn-primary btn-lg\" id=\"searchButtonSingle\">\n                            <i class=\"material-icons\">search</i>\n                        </span>\n                        </div>\n                    </div>\n                </div>\n\n                <div *ngIf=\"noInfoFoundWarning\" class=\"alert alert-warning\">\n                    No Info was found for {{itemName}}\n                </div>\n\n\n                <div class=\"row\">\n                    <div class=\"col-md-10 col-md-offset-1\">\n                        <div id=\"resultChartSingle\"></div>\n                    </div>\n                </div>\n                <div class=\"row\">\n                    <div class=\"col-md-5 col-md-offset-1\">\n                        <h3 *ngIf=\"lastSearchTerm != null\">{{lastSearchTerm?.itemName}} @ {{lastSearchTerm?.realmName}}</h3>\n                    </div>\n                    <div class=\"col-md-5\">\n                        <h3 *ngIf=\"lastRegionSearchTerm != null\">{{lastRegionSearchTerm?.item?.name}} @ {{lastRegionSearchTerm?.locality}} servers</h3>\n                    </div>\n                </div>\n                <div class=\"row\">\n                    <div *ngIf=\"lastSearchTerm != null\" class=\"col-md-5 col-md-offset-1\">\n                        <table class=\"table table-striped table-hover \">\n                          <tbody>\n                              <tr>\n                                <td>Last Seen</td>\n                                <td>{{lastSearchTerm?.exportTimePretty}}</td>\n                              </tr>\n                               <tr>\n                                <td>Quantity</td>\n                                <td>{{lastSearchTerm?.quantity}}</td>\n                              </tr>\n                                  <tr>\n                                <td>Median Price</td>\n                                <td>{{transformPretty(lastSearchTerm?.medianBuyoutCoppers)}}</td>\n                              </tr>\n                              <tr>\n                                <td>Mean Price</td>\n                                <td>{{transformPretty(lastSearchTerm?.averageBuyoutCoppers)}}</td>\n                              </tr>\n                              <tr>\n                                <td>Standard Deviation</td>\n                                <td>{{transformPretty(lastSearchTerm?.stdevBuyoutCoppers)}}</td>\n                              </tr>\n                          </tbody>\n                        </table>\n                    </div>\n                    <div *ngIf=\"lastRegionSearchTerm != null\" class=\"col-md-5\">\n                      <table class=\"table table-striped table-hover \">\n                        <tbody>\n                            <tr>\n                              <td>EU Quantity</td>\n                              <td>{{lastRegionSearchTerm?.totalQuantity}}</td>\n                            </tr>\n                            <tr>\n                              <td>EU Median Price</td>\n                              <td>{{transformPretty(lastRegionSearchTerm?.medianBuyout)}}</td>\n                            </tr>\n                            <tr>\n                              <td>EU Mean Price</td>\n                              <td>{{transformPretty(lastRegionSearchTerm?.averageBuyout)}}</td>\n                            </tr>\n                        </tbody>\n                      </table>\n                    </div>\n                    <div class=\"col-md-1\"></div>\n                </div>\n    "
                    }), 
                    __metadata('design:paramtypes', [AuctionhouseItemSearchService_1.AuctionHouseItemSearchService, RealmService_1.RealmService])
                ], SingleItemSearch);
                return SingleItemSearch;
            })();
            exports_1("SingleItemSearch", SingleItemSearch);
        }
    }
});
//# sourceMappingURL=singleItemSearch.js.map