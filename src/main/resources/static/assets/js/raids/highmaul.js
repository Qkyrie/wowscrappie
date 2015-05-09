(function (window, undefined) {
    // Bind to StateChange Event
    History.Adapter.bind(window, 'statechange', function () { // Note: We are using statechange instead of popstate
        var State = History.getState(); // Note: We are using History.getState() instead of event.state
        console.log('configid: ' + State.data.configId);
        if (State.data.configId !== undefined) {
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
                    console.log("done showing stuff");
                }
            });
        } else {
            var bossid = State.data.bossId;
            var configtype = State.data.configType;
            if (configtype !== undefined && configtype !== 0 && configtype !== null && bossid !== undefined  && bossid !== 0 && bossid !== null) {
                console.log(bossid);
                console.log(configtype);
                $.get("/raids/1/bosses/" + bossid + "/" + configtype, function (data) {
                    $("#resultPane").html(data);
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

$.urlParam = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href.replace('?_escaped_fragment_=', ''));
    if (results == null) {
        return null;
    }
    else {
        return results[1] || 0;
    }
};

var bossid = $.urlParam("bossid");
var configtype = $.urlParam("configtype");
var configId = $.urlParam("configid");

if (configtype !== 0 && configtype !== null && bossid !== 0 && bossid !== null) {
    $.get("/raids/1/bosses/" + bossid + "/" + configtype, function (data) {
        $("#resultPane").html(data);
        $('#myTable')
            .on('init.dt', function () {
                if (slidingHeader.manualPreference !== true) {
                    slidingHeader.hideHeader();
                }
            })
            .dataTable();
        if (configId !== 0 && configId !== null) {
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

$(".btn-macro").click(function () {
    var bossId = $(this).data('boss-id');
    History.pushState(
        {
            bossId: bossId,
            configType: 'macro'
        },
        "Highmaul Macros - WowScrappie", "?bossid=" + bossId + '&configtype=macro'
    );
});

$(".btn-wa").click(function () {
    var bossId = $(this).data('boss-id');
    History.pushState(
        {
            bossId: bossId,
            configType: 'weakaura'
        },
        "Highmaul Weakauras - WowScrappie", "?bossid=" + bossId + '&configtype=weakaura'
    );
});

$(".btn-tmw").click(function () {
    var bossId = $(this).data('boss-id');
    History.pushState(
        {
            bossId: bossId,
            configType: 'tellmewhen'
        },
        "Highmaul TellMeWhens - WowScrappie", "?bossid=" + bossId + '&configtype=tellmewhen'
    );
});