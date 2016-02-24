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
                SingleItemSearch.prototype.generateOrLoadChart = function () {
                    if (this.currentSnapshotChart) {
                        this.loadIntoChart();
                    }
                    else {
                        this.generateChart();
                    }
                };
                SingleItemSearch.prototype.loadIntoChart = function () {
                    this.currentSnapshotChart.load({
                        columns: [
                            ['item',
                                this.lastSearchTerm.minimumBidCoppers,
                                this.lastSearchTerm.averageBidCoppers,
                                this.lastSearchTerm.medianBidCoppers,
                                this.lastSearchTerm.minimumBuyoutCoppers,
                                this.lastSearchTerm.averageBuyoutCoppers,
                                this.lastSearchTerm.medianBuyoutCoppers]
                        ]
                    });
                };
                SingleItemSearch.prototype.generateChart = function () {
                    this.currentSnapshotChart = c3.generate({
                        bindto: '#resultChartSingle',
                        data: {
                            columns: [
                                ['item',
                                    this.lastSearchTerm.minimumBidCoppers,
                                    this.lastSearchTerm.averageBidCoppers,
                                    this.lastSearchTerm.medianBidCoppers,
                                    this.lastSearchTerm.minimumBuyoutCoppers,
                                    this.lastSearchTerm.averageBuyoutCoppers,
                                    this.lastSearchTerm.medianBuyoutCoppers
                                ]
                            ],
                            type: 'bar',
                            color: function (color, d) {
                                return '#009688';
                            }
                        },
                        axis: {
                            x: {
                                type: 'category',
                                categories: ['Minimum Bid', 'Average Bid', 'Median Bid',
                                    'Minimum Buyout', 'Average Buyout', 'Median Buyout']
                            }
                        },
                        tooltip: {
                            contents: function (d, defaultTitleFormat, defaultValueFormat, color) {
                                var coppers = d[0].value;
                                var actualCoppers = Math.floor(+coppers % 100);
                                var silverAndCoppers = Math.floor(+coppers / 100);
                                var silvers = Math.floor(silverAndCoppers % 100);
                                var gold = Math.floor(silverAndCoppers / 100);
                                return gold + 'g ' + silvers + 's ' + actualCoppers + 'c';
                            }
                        }
                    });
                };
                SingleItemSearch.prototype.ngAfterViewChecked = function () {
                    if (!this.typeaheadBound) {
                        this.typeaheadBound = this.initTypeAhead();
                    }
                };
                SingleItemSearch.prototype.doSearch = function () {
                    var _this = this;
                    this.noInfoFoundWarning = false;
                    this.ahSearchService.doSearch(this.itemId, this.myRealm.id)
                        .subscribe(function (searchResult) {
                        _this.lastSearchTerm = searchResult;
                        _this.generateOrLoadChart();
                    }, function (err) {
                        _this.noInfoFoundWarning = true;
                    }, function () { return console.log("Done"); });
                };
                SingleItemSearch = __decorate([
                    core_1.Component({
                        selector: 'single-search',
                        providers: [AuctionhouseItemSearchService_1.AuctionHouseItemSearchService, RealmService_1.RealmService],
                        template: "\n                <div *ngIf=\"itemName != null\" class=\"row\">\n                    <div class=\"col-md-12\">\n                        <h1>Search information about {{itemName}} on {{myRealm?.locality}}-{{myRealm?.name}}.</h1>\n                    </div>\n                </div>\n\n                <div class=\"row\">\n                    <div class=\"col-md-8 col-md-offset-2\">\n                        <div class=\"col-md-8 col-md-offset-2\">\n                            <div class=\"form-group label-floating\">\n                                <label class=\"control-label\" for=\"itemSelectSingle\">Item</label>\n                                <div id=\"itemSingle\">\n                                    <input id=\"itemSelectSingle\" class=\"form-control input-lg typeahead\" type=\"text\"/>\n                                </div>\n                            </div>\n                        </div>\n                        <div *ngIf=\"itemName != null\" class=\"col-md-1\">\n                        <span (click)=\"doSearch()\" class=\"btn btn-primary btn-lg\" id=\"searchButtonSingle\">\n                            <i class=\"material-icons\">search</i>\n                        </span>\n                        </div>\n                    </div>\n                </div>\n\n                <div *ngIf=\"noInfoFoundWarning\" class=\"alert alert-warning\">\n                    No Info was found for {{itemName}}\n                </div>\n\n                <div *ngIf=\"lastSearchTerm != null\" class=\"row\">\n                    <div class=\"col-md-10 col-md-offset-1\">\n                        <h3>Last update date for {{itemName}} on {{myRealm?.locality}}-{{myRealm?.name}}: {{lastSearchTerm?.exportTimePretty}}</h3>\n                    </div>\n                </div>\n\n                <div class=\"row\">\n                    <div class=\"col-md-10 col-md-offset-1\">\n                        <div id=\"resultChartSingle\"></div>\n                    </div>\n                </div>\n    "
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