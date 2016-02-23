System.register(['angular2/core', '../services/AuctionhouseItemSearchService'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, AuctionhouseItemSearchService_1;
    var SingleItemSearch;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (AuctionhouseItemSearchService_1_1) {
                AuctionhouseItemSearchService_1 = AuctionhouseItemSearchService_1_1;
            }],
        execute: function() {
            SingleItemSearch = (function () {
                function SingleItemSearch(ahSearchService) {
                    this.ahSearchService = ahSearchService;
                    this.itemName = "item";
                    this.realmName = "a realm";
                    this.items = Bloodhound;
                    this.realms = Bloodhound;
                    this.myObject = this;
                    this.typeaheadBound = false;
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
                    console.log("loading into existing chart");
                    this.currentSnapshotChart.load({
                        columns: [
                            ['item',
                                this.lastSearchTerm.minimumBidCoppers,
                                this.lastSearchTerm.averageBidCoppers,
                                this.lastSearchTerm.maximumBidCoppers,
                                this.lastSearchTerm.minimumBuyoutCoppers,
                                this.lastSearchTerm.averageBuyoutCoppers,
                                this.lastSearchTerm.maximumBuyoutCoppers]
                        ]
                    });
                };
                SingleItemSearch.prototype.generateChart = function () {
                    console.log("generating chart from");
                    console.log(this.lastSearchTerm);
                    this.currentSnapshotChart = c3.generate({
                        bindto: '#resultChartSingle',
                        data: {
                            columns: [
                                ['item',
                                    this.lastSearchTerm.minimumBidCoppers,
                                    this.lastSearchTerm.averageBidCoppers,
                                    this.lastSearchTerm.maximumBidCoppers,
                                    this.lastSearchTerm.minimumBuyoutCoppers,
                                    this.lastSearchTerm.averageBuyoutCoppers,
                                    this.lastSearchTerm.maximumBuyoutCoppers
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
                                categories: ['Minimum Bid', 'Average Bid', 'Maximum Bid',
                                    'Minimum Buyout', 'Average Buyout', 'Maximum Buyout']
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
                    this.ahSearchService.doSearch(this.itemId, this.realmId)
                        .subscribe(function (searchResult) {
                        _this.lastSearchTerm = searchResult;
                        console.log("generating or loading");
                        _this.generateOrLoadChart();
                    });
                };
                SingleItemSearch = __decorate([
                    core_1.Component({
                        selector: 'single-search',
                        providers: [AuctionhouseItemSearchService_1.AuctionHouseItemSearchService],
                        template: "\n                <div class=\"row\">\n                    <div class=\"col-md-12\">\n                        <h1>Search information about {{itemName}} on a {{realmName}}.</h1>\n                    </div>\n                </div>\n\n                <div class=\"row\">\n                    <div class=\"col-md-8 col-md-offset-2\">\n                        <div class=\"col-md-5 col-md-offset-1\">\n                            <div class=\"form-group label-floating\">\n                                <label class=\"control-label\" for=\"itemSelectSingle\">Item</label>\n                                <div id=\"itemSingle\">\n                                    <input id=\"itemSelectSingle\" class=\"form-control input-lg typeahead\" type=\"text\"/>\n                                </div>\n                            </div>\n                        </div>\n                        <div class=\"col-md-5\">\n                            <div class=\"form-group label-floating\">\n                                <label class=\"control-label\" for=\"realmSelectSingle\">Realm</label>\n                                <div id=\"realmSingle\">\n                                    <input id=\"realmSelectSingle\" class=\"form-control input-lg typeahead\" type=\"text\"/>\n                                </div>\n                            </div>\n                        </div>\n                        <div class=\"col-md-1\">\n                        <span (click)=\"doSearch()\" class=\"btn btn-primary btn-lg\" id=\"searchButtonSingle\">\n                            <i class=\"material-icons\">search</i>\n                        </span>\n                        </div>\n                    </div>\n                </div>\n\n                <div  *ngIf=\"lastSearchTerm != null\" class=\"row\">\n                    <div class=\"col-md-10 col-md-offset-1\">\n                        <h3>Last update date for {{itemName}} on {{realmName}}: {{lastSearchTerm?.exportTimePretty}}</h3>\n                    </div>\n                </div>\n\n                <div class=\"row\">\n                    <div class=\"col-md-10 col-md-offset-1\">\n                        <div id=\"resultChartSingle\"></div>\n                    </div>\n                </div>\n    "
                    }), 
                    __metadata('design:paramtypes', [AuctionhouseItemSearchService_1.AuctionHouseItemSearchService])
                ], SingleItemSearch);
                return SingleItemSearch;
            })();
            exports_1("SingleItemSearch", SingleItemSearch);
        }
    }
});
//# sourceMappingURL=singleItemSearch.js.map