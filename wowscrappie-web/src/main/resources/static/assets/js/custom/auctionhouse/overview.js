var SingleItemSearchModel = function () {

    var self = this;
    self.selectedItem = ko.observable(0);
    self.selectedItemName = ko.observable('???');
    self.selectedRealm = ko.observable(0);
    self.selectedRealmName = ko.observable('???');
    self.chart = null;
    self.lastUpdateDatePretty = ko.observable();
    self.lastUpdateDate = ko.observable();

    var items = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: {
            url: '/rest/items/query?search=%QUERY',
            wildcard: '%QUERY'
        }
    });

    var realms = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: {
            url: '/rest/realms/query?search=%QUERY',
            wildcard: '%QUERY'
        }
    });

    $('#realmSingle .typeahead').typeahead(null, {
        name: 'items',
        display: 'fullName',
        source: realms
    });

    $('#itemSingle .typeahead').typeahead(null, {
        name: 'items',
        display: 'name',
        source: items
    });

    $('#itemSelectSingle').bind('typeahead:selected', function (obj, datum, name) {
        self.selectedItem(datum.id);
        self.selectedItemName(datum.name)
        $("#chooseItemOptionSingle").alert('close');
    });

    $('#realmSelectSingle').bind('typeahead:selected', function (obj, datum, name) {
        self.selectedRealm(datum.id);
        self.selectedRealmName(datum.name);
        $("#chooseRealmOptionSingle").alert('close');
    });

    $("#searchButtonSingle").click(function () {
        if (self.selectedItem() == 0) {
            $("#chooseItemOptionSingle").show();
            return;
        }
        if (self.selectedRealm() == 0) {
            $("#chooseRealmOptionSingle").show();
            return;
        }

        $("#chooseItemOptionSingle").alert('close');
        $("#chooseRealmOptionSingle").alert('close');

        $.get("/rest/auctionhouse/latest/item/" + self.selectedItem() + "/realm/" + self.selectedRealm(), function (data) {

            self.lastUpdateDatePretty(data.exportTimePretty);
            self.lastUpdateDate(data.actualExportTime);

            if (self.chart == null) {
                self.chart = c3.generate({
                    bindto: '#resultChartSingle',
                    data: {
                        columns: [
                            ['item', data.minimumBidCoppers, data.averageBidCoppers, data.maximumBidCoppers,
                                data.minimumBuyoutCoppers, data.averageBuyoutCoppers, data.maximumBuyoutCoppers]
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
                            var actualCoppers = parseInt(coppers % 100);
                            var silverAndCoppers = parseInt(coppers / 100);
                            var silvers = parseInt(silverAndCoppers % 100);
                            var gold = parseInt(silverAndCoppers / 100);

                            return gold + 'g ' + silvers + 's ' + actualCoppers + 'c';
                        }
                    }
                });
            } else {
                self.chart.load({
                    columns: [
                        ['item', data.minimumBidCoppers, data.averageBidCoppers, data.maximumBidCoppers,
                            data.minimumBuyoutCoppers, data.averageBuyoutCoppers, data.maximumBuyoutCoppers]
                    ]
                });
            }
        }).fail(function (which) {
            if (which.status == 404) {
                console.log("nothing found");
            }
        });
    });
};
var singleItemSearchModel = new SingleItemSearchModel();
ko.applyBindings(singleItemSearchModel, document.getElementById("singleItemSearch"));

var CompareItemSearchModel = function() {
    var self = this;
    self.selectedItem = ko.observable(0);
    self.selectedItemName = ko.observable('???');
    self.selectedRealm = ko.observable(0);
    self.selectedRealmName = ko.observable('???');
    self.chart = null;

    var items = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: {
            url: '/rest/items/query?search=%QUERY',
            wildcard: '%QUERY'
        }
    });

    var realms = new Bloodhound({
        datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
        queryTokenizer: Bloodhound.tokenizers.whitespace,
        remote: {
            url: '/rest/realms/query?search=%QUERY',
            wildcard: '%QUERY'
        }
    });

    $('#realmCompare .typeahead').typeahead(null, {
        name: 'items',
        display: 'fullName',
        source: realms
    });

    $('#itemCompare .typeahead').typeahead(null, {
        name: 'items',
        display: 'name',
        source: items
    });

    $('#itemSelectCompare').bind('typeahead:selected', function (obj, datum, name) {
        self.selectedItem(datum.id);
        self.selectedItemName(datum.name)
        $("#chooseItemOptionSingle").alert('close');
    });

    $('#realmSelectCompare').bind('typeahead:selected', function (obj, datum, name) {
        self.selectedRealm(datum.id);
        self.selectedRealmName(datum.name);
        $("#chooseRealmOptionCompare").alert('close');
    });

    $("#searchButtonCompare").click(function () {
        if (self.selectedItem() == 0) {
            $("#chooseItemOptionCompare").show();
            return;
        }
        if (self.selectedRealm() == 0) {
            $("#chooseRealmOptionCompare").show();
            return;
        }

        $("#chooseItemOptionCompare").alert('close');
        $("#chooseRealmOptionCompare").alert('close');

        $.get("/rest/auctionhouse/latest/item/" + self.selectedItem() + "/realm/" + self.selectedRealm(), function (data) {
            if (self.chart == null) {
                self.chart = c3.generate({
                    bindto: '#resultChartCompare',
                    data: {
                        columns: [
                            [data.itemName + '-' + data.realmName, data.minimumBidCoppers, data.averageBidCoppers, data.maximumBidCoppers,
                                data.minimumBuyoutCoppers, data.averageBuyoutCoppers, data.maximumBuyoutCoppers]
                        ],
                        type: 'bar',
                        labels: {
                            format: function (value, ratio, id) {
                                var coppers = value;
                                var actualCoppers = parseInt(coppers % 100);
                                var silverAndCoppers = parseInt(coppers / 100);
                                var silvers = parseInt(silverAndCoppers % 100);
                                var gold = parseInt(silverAndCoppers / 100);
                                return gold + 'g ' + silvers + 's ' + actualCoppers + 'c';
                            }
                        }
                    },
                    axis: {
                        x: {
                            type: 'category',
                            categories: ['Minimum Bid', 'Average Bid', 'Maximum Bid',
                                'Minimum Buyout', 'Average Buyout', 'Maximum Buyout']
                        }
                    }
                });
            } else {
                self.chart.load({
                    columns: [
                        [data.itemName + '-' + data.realmName, data.minimumBidCoppers, data.averageBidCoppers, data.maximumBidCoppers,
                            data.minimumBuyoutCoppers, data.averageBuyoutCoppers, data.maximumBuyoutCoppers]
                    ]
                });
            }
        }).fail(function (which) {
            if (which.status == 404) {
                console.log("nothing found");
            }
        });
    });
};

var compareItemSearchModel = new CompareItemSearchModel();
ko.applyBindings(compareItemSearchModel, document.getElementById("compareItemSearch"));


$("#homeTab a").click(function (e) {
    e.preventDefault();
    $(this).tab('show')
});

$("#compareTab a").click(function (e) {
    e.preventDefault();
    $(this).tab('show')
});