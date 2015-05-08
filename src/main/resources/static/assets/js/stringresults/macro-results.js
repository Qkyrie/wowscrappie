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

var MacroModel = function () {
    var self = this;
    self.activeName = ko.observable();
    self.activeConfig = ko.observable();
    self.activeString = ko.observable();
    self.activeComment = ko.observable();
    self.showDetails = ko.observable(false);
    self.activeConfigId = ko.observable();
    self.view = function (data, event) {
        var clickedElement = $(event.currentTarget);
        var tmw_id = clickedElement.data("macro_id");
        var state = History.getState();
        var wowclass = $("#wowclass").val();
        if(wowclass !== undefined) {
            var wowspec = $.urlParam("wowspec");
            History.pushState(
                {
                    wowspec: wowspec,
                    configType: 'macro',
                    configId: tmw_id
                },
                wowspec + ' ' + wowclass + " Macros - WowScrappie", "?wowspec=" + wowspec + '&configtype=macro&configid=' + tmw_id
            );
        } else {
            var bossid = $.urlParam("bossid");
            History.pushState(
                {
                    bossId: bossid,
                    configType: 'macro',
                    configId: tmw_id
                },
                "Raid Macros - WowScrappie", "?bossid=" + state.data.bossId + '&configtype=macro&configid=' + tmw_id
            );
        }
    };

    self.report = function (data, event) {
        var clickedElement = $(event.currentTarget);
        var macro_id = clickedElement.data("macro_id");
        $("#reported_macro").val(macro_id);
        $('#report-dialog').modal('show');
    };

    self.redirectShare = function () {
        window.open('/shared/macro/' + self.activeConfigId(), '_blank');
    };
};

var configModel = new MacroModel();
ko.applyBindings(configModel, $("#macro-stuff")[0]);

$(".deleteButton").click(function () {
    var self = $(this);
    $.get('/interface/macro/' + self.data('macro_id') + '/delete', function (data) {
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
    $.get('/interface/macro/' + self.data('macro_id') + '/approve', function (data) {
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
    $.get('/interface/macro/' + self.data('macro_id') + '/disable', function (data) {
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
    var message = $("#macro_report").val();
    if (message !== '') {
        var command = {};
        command['comment'] = message;
        command['interfaceId'] = $("#reported_macro").val();
        command['interfaceType'] = 'macro';
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