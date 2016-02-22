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
    var MultiItemSearch;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (AuctionhouseItemSearchService_1_1) {
                AuctionhouseItemSearchService_1 = AuctionhouseItemSearchService_1_1;
            }],
        execute: function() {
            MultiItemSearch = (function () {
                function MultiItemSearch(ahSearchService) {
                    this.ahSearchService = ahSearchService;
                    this.itemName = "item";
                    this.realmName = "a realm";
                    this.items = Bloodhound;
                    this.realms = Bloodhound;
                    this.myObject = this;
                    this.typeaheadBound = false;
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
                    console.log("loading into existing chart");
                    this.currentSnapshotChart.load({
                        columns: [
                            [this.lastSearchTerm.itemName,
                                this.lastSearchTerm.minimumBidCoppers,
                                this.lastSearchTerm.averageBidCoppers,
                                this.lastSearchTerm.maximumBidCoppers,
                                this.lastSearchTerm.minimumBuyoutCoppers,
                                this.lastSearchTerm.averageBuyoutCoppers,
                                this.lastSearchTerm.maximumBuyoutCoppers]
                        ]
                    });
                };
                MultiItemSearch.prototype.generateChart = function () {
                    console.log("generating chart from");
                    console.log(this.lastSearchTerm);
                    this.currentSnapshotChart = c3.generate({
                        bindto: '#resultChartMulti',
                        data: {
                            columns: [
                                [this.lastSearchTerm.itemName,
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
                MultiItemSearch.prototype.ngAfterViewChecked = function () {
                    if (!this.typeaheadBound) {
                        this.typeaheadBound = this.initTypeAhead();
                    }
                };
                MultiItemSearch.prototype.doSearch = function () {
                    var _this = this;
                    this.ahSearchService.doSearch(this.itemId, this.realmId)
                        .subscribe(function (searchResult) {
                        _this.lastSearchTerm = searchResult;
                        console.log("generating or loading");
                        _this.generateOrLoadChart();
                    });
                };
                MultiItemSearch = __decorate([
                    core_1.Component({
                        selector: 'multi-search',
                        providers: [AuctionhouseItemSearchService_1.AuctionHouseItemSearchService],
                        template: "\n                <div class=\"row\">\n                    <div class=\"col-md-12\">\n                        <h1>Search for multiple items on multiple realms.</h1>\n                    </div>\n                </div>\n\n                <div class=\"row\">\n                    <div class=\"col-md-8 col-md-offset-2\">\n                        <div class=\"col-md-5 col-md-offset-1\">\n                            <div class=\"form-group label-floating\">\n                                <label class=\"control-label\" for=\"itemSelectMulti\">Item</label>\n                                <div id=\"itemMulti\">\n                                    <input id=\"itemSelectMulti\" class=\"form-control input-lg typeahead\" type=\"text\"/>\n                                </div>\n                            </div>\n                        </div>\n                        <div class=\"col-md-5\">\n                            <div class=\"form-group label-floating\">\n                                <label class=\"control-label\" for=\"realmSelectMulti\">Realm</label>\n                                <div id=\"realmMulti\">\n                                    <input id=\"realmSelectMulti\" class=\"form-control input-lg typeahead\" type=\"text\"/>\n                                </div>\n                            </div>\n                        </div>\n                        <div class=\"col-md-1\">\n                        <span (click)=\"doSearch()\" class=\"btn btn-primary btn-lg\" id=\"searchButtonMulti\">\n                            <i class=\"material-icons\">search</i>\n                        </span>\n                        </div>\n                    </div>\n                </div>\n\n                <div  *ngIf=\"lastSearchTerm != null\" class=\"row\">\n                    <div class=\"col-md-10 col-md-offset-1\">\n                        <h3>Last update date for {{itemName}} on {{realmName}}: {{lastSearchTerm?.exportTimePretty}}</h3>\n                    </div>\n                </div>\n\n                <div class=\"row\">\n                    <div class=\"col-md-10 col-md-offset-1\">\n                        <div id=\"resultChartMulti\"></div>\n                    </div>\n                </div>\n    "
                    }), 
                    __metadata('design:paramtypes', [AuctionhouseItemSearchService_1.AuctionHouseItemSearchService])
                ], MultiItemSearch);
                return MultiItemSearch;
            })();
            exports_1("MultiItemSearch", MultiItemSearch);
        }
    }
});
//# sourceMappingURL=multiItemSearch.js.map