$('.shareButton').each(function () {
    $(this).click(function () {
        window.open($(this).data('share-url'), '_blank');
    });
});

$(".editButton").each(function () {
    $(this).click(function () {
        window.open($(this).data('edit-url'), '_blank');
    });
});


var WaModel = function () {
    var self = this;

    self.activeConfig = ko.observable();
    self.activeString = ko.observable();
    self.activeComment = ko.observable();
    self.showDetails = ko.observable(false);
    self.imageRefs = ko.observableArray([]);
    self.activeName = ko.observable();
    self.activeConfigId = ko.observable();
    self.activePatch = ko.observable();

    self.view = function (data, event) {
        var clickedElement = $(event.currentTarget);
        var tmw_id = clickedElement.data("wa_id");
        var state = History.getState();
        var wowclass = $("#wowclass").val();
        if (wowclass !== undefined) {
            var wowspec = $.urlParam("wowspec");
            History.pushState(
                {
                    wowspec: wowspec,
                    configType: 'weakaura',
                    configId: tmw_id
                },
                wowspec + ' ' + wowclass + " Weakauras - WowScrappie", "?wowspec=" + wowspec + '&configtype=weakaura&configid=' + tmw_id
            );
        } else {
            var bossid = $.urlParam("bossid");
            History.pushState(
                {
                    bossId: bossid,
                    configType: 'weakaura',
                    configId: tmw_id
                },
                "Raid Weakauras - WowScrappie", "?bossid=" + bossid + '&configtype=weakaura&configid=' + tmw_id
            );
        }
    };

    self.report = function (data, event) {
        var clickedElement = $(event.currentTarget);
        var wa_id = clickedElement.data("wa_id");
        $("#reported_wa").val(wa_id);
        $('#report-dialog').modal('show');
    };

    self.redirectShare = function () {
        window.open('/shared/wa/' + self.activeConfigId(), '_blank');
    };
};

var configModel = new WaModel();
ko.applyBindings(configModel, $("#wa-wrapper")[0]);


$(".deleteButton").click(function () {
    var self = $(this);
    $.get('/interface/weakaura/' + self.data('wa_id') + '/delete', function (data) {
        if (data == true) {
            self.removeClass('btn-danger')
            self.addClass('btn-success')
            self.html('deleted');
        } else {
            self.addClass('btn-danger')
            self.html('deletion failed');
        }
    });
});

$(".approveButton").click(function () {
    var self = $(this);
    $.get('/interface/weakaura/' + self.data('wa_id') + '/approve', function (data) {
        if (data == true) {
            self.removeClass('btn-info')
            self.addClass('btn-success')
            self.html('approved');
        } else {
            self.removeClass('btn-info')
            self.addClass('btn-danger')
            self.html('approve failed');
        }
    });
});

$(".disableButton").click(function () {
    var self = $(this);
    $.get('/interface/weakaura/' + self.data('wa_id') + '/disable', function (data) {
        if (data == true) {
            self.removeClass('btn-warning')
            self.addClass('btn-success')
            self.html('disabled');
        } else {
            self.removeClass('btn-warning')
            self.addClass('btn-danger')
            self.html('disable failed');
        }
    });
});

$("#doReport").click(function () {
    var message = $("#weakaura_report").val();
    if (message !== '') {
        var command = {};
        command['comment'] = message;
        command['interfaceId'] = $("#reported_wa").val();
        command['interfaceType'] = 'wa';
        var stringyfiedCommand = JSON.stringify(command);
        $.ajax({
            url: '/interface/reports/create',
            type: "POST",
            contentType: "application/json; charset=utf-8",
            data: stringyfiedCommand, //Stringified Json Object
            cache: false,    //This will force requested pages not to be cached by the browser
            processData: false, //To avoid making query String instead of JSON
            success: function (resposeJsonObject) {
                $('#report-dialog').modal('hide');
            }
        });
    }
});
