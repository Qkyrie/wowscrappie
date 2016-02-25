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
    var MultiItemSearch;
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
            MultiItemSearch = (function () {
                function MultiItemSearch(ahSearchService, realmService) {
                    var _this = this;
                    this.ahSearchService = ahSearchService;
                    this.realmService = realmService;
                    this.itemName = "item";
                    this.items = Bloodhound;
                    this.noInfoFoundWarning = false;
                    this.myObject = this;
                    this.typeaheadBound = false;
                    this.allSearchTerms = [];
                    realmService.findCurrent()
                        .subscribe(function (currentRealm) {
                        _this.myRealm = currentRealm;
                    }, function (error) {
                    });
                }
                MultiItemSearch.prototype.initTypeAhead = function () {
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
                };
                MultiItemSearch.prototype.generateOrLoadChart = function () {
                    if (this.currentSnapshotChart) {
                        this.loadIntoChart();
                    }
                    else {
                        this.generateChart();
                    }
                };
                MultiItemSearch.prototype.loadIntoChart = function () {
                    this.currentSnapshotChart.load({
                        columns: [
                            [this.lastSearchTerm.itemName,
                                this.lastSearchTerm.averageBidCoppers,
                                this.lastSearchTerm.medianBidCoppers,
                                this.lastSearchTerm.averageBuyoutCoppers,
                                this.lastSearchTerm.medianBuyoutCoppers]
                        ]
                    });
                };
                MultiItemSearch.prototype.generateChart = function () {
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
                                    var coppers = v;
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
                            }
                        },
                        tooltip: {
                            show: false
                        }
                    });
                };
                MultiItemSearch.prototype.ngAfterViewChecked = function () {
                    if (!this.typeaheadBound) {
                        this.typeaheadBound = this.initTypeAhead();
                    }
                };
                MultiItemSearch.prototype.transformPretty = function (amount) {
                    var actualCoppers = Math.floor(+amount % 100);
                    var silverAndCoppers = Math.floor(+amount / 100);
                    var silvers = Math.floor(silverAndCoppers % 100);
                    var gold = Math.floor(silverAndCoppers / 100);
                    return gold + 'g ' + silvers + 's ' + actualCoppers + 'c';
                };
                MultiItemSearch.prototype.doSearch = function () {
                    var _this = this;
                    this.noInfoFoundWarning = false;
                    this.ahSearchService.searchForItemAndRealm(this.itemId, this.myRealm.id)
                        .subscribe(function (searchResult) {
                        _this.lastSearchTerm = searchResult;
                        _this.allSearchTerms.push(searchResult);
                        _this.generateOrLoadChart();
                    }, function (err) {
                        _this.noInfoFoundWarning = true;
                        console.log(err);
                    }, function () {
                    });
                };
                MultiItemSearch = __decorate([
                    core_1.Component({
                        selector: 'multi-search',
                        providers: [AuctionhouseItemSearchService_1.AuctionHouseItemSearchService, RealmService_1.RealmService],
                        template: "\n                <div class=\"row\">\n                    <div class=\"col-md-12\">\n                        <h1>Search for multiple items on {{myRealm?.locality}}-{{myRealm?.name}}.</h1>\n                    </div>\n                </div>\n\n                <div class=\"row\">\n                    <div class=\"col-md-8 col-md-offset-2\">\n                        <div class=\"col-md-8 col-md-offset-2\">\n                            <div class=\"form-group label-floating\">\n                                <label class=\"control-label\" for=\"itemSelectMulti\">Item</label>\n                                <div id=\"itemMulti\">\n                                    <input id=\"itemSelectMulti\" class=\"form-control input-lg typeahead\" type=\"text\"/>\n                                </div>\n                            </div>\n                        </div>\n\n                        <div *ngIf=\"itemName != null\" class=\"col-md-1\">\n                        <span (click)=\"doSearch()\" class=\"btn btn-primary btn-lg\" id=\"searchButtonMulti\">\n                            <i class=\"material-icons\">search</i>\n                        </span>\n                        </div>\n                    </div>\n                </div>\n\n                <div *ngIf=\"noInfoFoundWarning\" class=\"alert alert-warning\">\n                    No Info was found for {{itemName}}\n                </div>\n\n\n\n\n                <div class=\"row\">\n                    <div class=\"col-md-10 col-md-offset-1\">\n                        <div id=\"resultChartMulti\"></div>\n                    </div>\n                </div>\n\n                <div *ngIf=\"lastSearchTerm != null\"  class=\"row\">\n                 <div *ngFor=\"#searchTerm of allSearchTerms\" class=\"col-md-4\">\n                     <h4>{{searchTerm.itemName}} statistics</h4>\n                         <table class=\"table table-striped table-hover \">\n                         <tbody>\n                         <tr>\n                         <td>Last Seen</td>\n                         <td>{{searchTerm?.exportTimePretty}}</td>\n                         </tr>\n                         <tr>\n                         <td>Quantity</td>\n                         <td>{{searchTerm?.quantity}}</td>\n                         </tr>\n                         <tr>\n                         <td>Median Price</td>\n                         <td>{{transformPretty(searchTerm?.medianBuyoutCoppers)}}</td>\n                         </tr>\n                         <tr>\n                         <td>Mean Price</td>\n                         <td>{{transformPretty(searchTerm?.averageBuyoutCoppers)}}</td>\n                         </tr>\n                         <tr>\n                         <td>Standard Deviation</td>\n                         <td>{{transformPretty(searchTerm?.stdevBuyoutCoppers)}}</td>\n                         </tr>\n                         </tbody>\n                         </table>\n                     </div>\n                 </div>\n    "
                    }), 
                    __metadata('design:paramtypes', [AuctionhouseItemSearchService_1.AuctionHouseItemSearchService, RealmService_1.RealmService])
                ], MultiItemSearch);
                return MultiItemSearch;
            })();
            exports_1("MultiItemSearch", MultiItemSearch);
        }
    }
});
//# sourceMappingURL=multiItemSearch.js.map