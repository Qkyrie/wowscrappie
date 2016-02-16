var OverviewPage = function() {


    var self = this;
    var selectedItem;
    var selectedRealm;



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

    $('#realm .typeahead').typeahead(null, {
        name: 'items',
        display: 'fullName',
        source: realms
    });

    $('#item .typeahead').typeahead(null, {
        name: 'items',
        display: 'name',
        source: items
    });

    $('#itemSelect').bind('typeahead:selected', function(obj, datum, name) {
        self.selectedItem = datum.id;

    });

    $('#realmSelect').bind('typeahead:selected', function(obj, datum, name) {
        self.selectedRealm = datum.id;

    });

    $("#searchButton").click(function() {
        console.log("do stuff with items realm: " + self.selectedRealm + " - item: " + self.selectedItem);
        $.get("/rest/auctionhouse/latest/item/" + self.selectedItem + "/realm/" + self.selectedRealm, function(data) {
            console.log(data);
        }).fail(function(which) {
            if(which.status == 404) {
                console.log("nothing found");
            }
        });
    });
};

new OverviewPage();

