(function (window, undefined) {
    // Bind to StateChange Event
    History.Adapter.bind(window, 'statechange', function () { // Note: We are using statechange instead of popstate

        var State = History.getState(); // Note: We are using History.getState() instead of event.state
        if(State.data.configId !== undefined) {
            $.get("/interface/" + State.data.configType + "/" + State.data.configId, function (resultValue) {
                if (resultValue !== null) {
                    configModel.activeConfig(resultValue);
                    configModel.activeConfigId(State.data.configId);
                    configModel.activeString(resultValue.actualValue);
                    configModel.activeComment(resultValue.comment);
                    if (configModel.imageRefs !== undefined) {
                        configModel.imageRefs(resultValue.imageRefs);
                    }
                    configModel.activeName(resultValue.name);
                    Mdjs().on($("#commentBox"));
                    configModel.showDetails(true);
                    $("html, body").animate({scrollTop: $(document).height()}, 1000);
                    $(".fancybox").fancybox();
                }
            });
        } else {
            var configtype = State.data.configType;
            var wowspec = State.data.wowspec;
            if (configtype !== undefined && configtype !== 0 && configtype !== null && wowspec !== undefined && wowspec !== 0 && wowspec !== null) {
                $.get('/classes/' + $("#wowclass").val() + '/' + wowspec + '/' + configtype, function (result) {
                    $("#resultPane").html(result);
                    $('#myTable')
                        .on('init.dt', function () {
                            if (slidingHeader.manualPreference !== true) {
                                slidingHeader.hideHeader();
                            }
                        })
                        .dataTable();
                });
            } else {
                $("#resultPane").html('');
                slidingHeader.showHeader();
            }

        }
    });
})(window);
var ViewModel = function () {
    var self = this;
    self.doGet = function (wowspec, configType) {
        History.pushState(
            {
                wowspec: wowspec,
                configType: configType
            },
            wowspec + ' ' + $("#wowclass").val() + " Weakauras, Tellmewhens and Macros - WowScrappie", "?wowspec=" + wowspec + '&configtype=' + configType
        );
    };
    self.wowClassChosen = function (data, event) {
        var clickedElement = $(event.currentTarget);
        var stringType = clickedElement.data("string_type");
        var spec = "class_specific";
        self.doGet(spec, stringType);
    };
    self.specChosen = function (data, event) {
        var clickedElement = $(event.currentTarget);
        var spec = clickedElement.data("spec_slug");
        var stringType = clickedElement.data("string_type");
        self.doGet(spec, stringType);
    }
};
var viewModel = new ViewModel();
ko.applyBindings(viewModel);

$.urlParam = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results == null) {
        return null;
    }
    else {
        return results[1] || 0;
    }
};

var wowspec = $.urlParam("wowspec");
var configtype = $.urlParam("configtype");
var configId = $.urlParam("configid");

if (configtype !== 0 && configtype !== null && wowspec !== 0 && wowspec !== null) {
    $.get('/classes/' + $("#wowclass").val() + '/' + wowspec + '/' + configtype, function (result) {
        $("#resultPane").html(result);
        $('#myTable')
            .on('init.dt', function () {
                if (slidingHeader.manualPreference !== true) {
                    slidingHeader.hideHeader();
                }
            })
            .dataTable();
            if(configId !== 0 && configId !== null){
                $.get("/interface/" + configtype + "/" + configId, function (resultValue) {
                    if (resultValue !== null) {
                        configModel.activeConfig(resultValue);
                        configModel.activeConfigId(configId);
                        configModel.activeString(resultValue.actualValue);
                        configModel.activeComment(resultValue.comment);
                        if (configModel.imageRefs !== undefined) {
                            configModel.imageRefs(resultValue.imageRefs);
                        }
                        configModel.activeName(resultValue.name);
                        Mdjs().on($("#commentBox"));
                        configModel.showDetails(true);
                        $("html, body").animate({scrollTop: $(document).height()}, 1000);
                        $(".fancybox").fancybox();
                    }
                });
            }
    });
}