(function(window,undefined){
    // Bind to StateChange Event
    History.Adapter.bind(window,'statechange',function(){ // Note: We are using statechange instead of popstate
        var State = History.getState(); // Note: We are using History.getState() instead of event.state
        $.get("/raids/1/bosses/" + State.data.bossId + "/" + State.data.configType, function(data) {
            $("#resultPane").html(data);
            $('#myTable')
                .on('init.dt', function () {
                    if (slidingHeader.manualPreference !== true) {
                        slidingHeader.hideHeader();
                    }
                })
                .dataTable();
        });
    });
})(window);

$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null){
        return null;
    }
    else{
        return results[1] || 0;
    }
};

var bossid = $.urlParam("bossid");
var configtype = $.urlParam("configtype");

if(configtype !== 0 && configtype !== null && bossid !== 0 && bossid !== null) {
    $.get("/raids/1/bosses/" + bossid + "/" + configtype, function(data) {
        $("#resultPane").html(data);
        $('#myTable')
            .on('init.dt', function () {
                if (slidingHeader.manualPreference !== true) {
                    slidingHeader.hideHeader();
                }
            })
            .dataTable();
    });
}


$(".btn-macro").click(function() {
    var bossId = $(this).data('boss-id');
    History.pushState(
        {
            bossId: bossId,
            configType: 'macro'
        },
        "Highmaul Macros - WowScrappie", "?bossid=" + bossId + '&configtype=macro'
    );
});

$(".btn-wa").click(function() {
    var bossId = $(this).data('boss-id');
    History.pushState(
        {
            bossId: bossId,
            configType: 'weakaura'
        },
        "Highmaul Weakauras - WowScrappie", "?bossid=" + bossId + '&configtype=weakaura'
    );
});

$(".btn-tmw").click(function() {
    var bossId = $(this).data('boss-id');
    History.pushState(
        {
            bossId: bossId,
            configType: 'tellmewhen'
        },
        "Highmaul TellMeWhens - WowScrappie", "?bossid=" + bossId + '&configtype=tellmewhen'
    );
});