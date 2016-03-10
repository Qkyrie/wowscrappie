$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null){
        return null;
    }
    else{
        return results[1] || 0;
    }
};

$("#btnPrevious").click(function() {
    var previousPageId = $("#btnPrevious").data('previous_page_id');
    var sizeParam = $.urlParam('size');
    if(sizeParam !== 0 && sizeParam !== null) {
        window.location = '/questions?size=' + sizeParam + '&page=' + previousPageId;
    } else {
        window.location = '/questions?page=' + previousPageId;
    }
});
$("#btnNext").click(function() {
    var nextPageId = $("#btnNext").data('next_page_id');
    var sizeParam = $.urlParam('size');
    if(sizeParam !== 0 && sizeParam !== null) {
        window.location = '/questions?size=' + sizeParam + '&page=' + nextPageId;
    } else {
        window.location = '/questions?page=' + nextPageId;
    }
});

$('.score').each(function() {
    var self = $(this);
    var score = $(this).data('score');
    if(score !== undefined && score !== 0) {
        self.removeClass('btn-primary');
        if(score > 0) {
            self.addClass('btn-success');
        } else {
            self.addClass('btn-danger');
        }
    }
});