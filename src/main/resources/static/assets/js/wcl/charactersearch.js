var ViewModel = function () {
    var self = this;

    self.realm = ko.observable({id: null, name: ''});
    self.realmname = ko.observable('');
    self.charname = ko.observable('');
    self.ranks = ko.observableArray([])

    self.explainIsVisible = function () {
        return self.realm().id !== null && self.charname() !== '';
    };

    self.showRankResults = function () {
        if (self.ranks().length > 0) {
            $("#resultRow").show();
            $("#resultTable").dataTable();
            $(".viewdetails").click(function () {
                console.log("opening that new window now");
                window.open('https://www.warcraftlogs.com/reports/' + $(this).data('report_id') + '#fight=' + $(this).data('fight_id'), '_blank');
            });
        } else {
            $("#resultRow").hide();
            $("#nothingfound").show();
        }
    };

    self.doSearch = function () {
        $("#searching").show();
        $("#resultRow").hide();
        $("#nothingfound").hide();
        self.ranks.removeAll();
        // Quickly and simply clear a table
        $('#resultTable').dataTable().fnClearTable();
        // Restore the table to it's original state in the DOM by removing all of DataTables enhancements, alterations to the DOM structure of the table and event listeners
        $('#resultTable').dataTable().fnDestroy();
        $.get('/warcraftlogs/character/' + self.charname() + '/' + self.realm().id, function (result) {
            self.ranks(result);
            self.showRankResults();
            $("#searching").hide();
        }).fail(function() {
            self.ranks([]);
            $("#searching").hide();
            self.showRankResults();
        })
    };
};
var viewModel = new ViewModel();
ko.applyBindings(viewModel);

var realms = [];
$.get("/rest/realms", function (allRealms) {
    allRealms.forEach(function (realm) {
        realms.push({
            value: realm.name + '-' + realm.locality,
            data: realm.id
        })
    });

    $("#serverName").autocomplete({
        lookup: realms,
        onSelect: function (suggestion) {
            viewModel.realm({id: suggestion.data, name: suggestion.value});
            viewModel.realmname(suggestion.value);
        }
    });
});
