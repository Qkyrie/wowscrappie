$.postJSON = function (url, data, callback) {
    return jQuery.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        'type': 'POST',
        'url': url,
        'data': JSON.stringify(data),
        'dataType': 'json',
        'success': callback
    });
};

var SpecificQuestion = function() {
    var self = this;

    self.commenting = false;
    self.userComment = ko.observable("");
    self.errorMessage = ko.observable("");
    self.hasErrors = ko.observable(false);
    self.thisQuestion = $("#questionId").val();
    self.originalCommentId = ko.observable('');

    self.commentSection = $("#commentSection");

    self.loadComments = function() {
        $.get('/questions/' + self.thisQuestion + '/responses', function(data) {
            $("#responsesHolder").html(data);
            $(".commentOnComment").click(self.commentOnComment);
            $(".deleteComment").click(self.deleteComment);
        });
    };

    self.deleteComment = function(event) {
        var clickedElement = $(event.currentTarget);
        var commentToDeleteId = clickedElement.data("comment_id");
        $.get('/questions/' + self.thisQuestion + '/comment/' + commentToDeleteId + '/delete', function() {
            self.loadComments();
        });
    };

    self.commentOnComment = function(event) {
        var clickedElement = $(event.currentTarget);
        self.originalCommentId(clickedElement.data("comment_id"));
        self.userComment('');
        self.commentSection.show();
        $("html, body").animate({scrollTop: $(document).height()}, 1000);
        $("#placeComment").focus();
    };

    self.commentOnQuestion = function() {
        self.originalCommentId('');
        self.userComment('');
        self.commentSection.show();
        $("html, body").animate({scrollTop: $(document).height()}, 1000);
        $("#placeComment").focus();
    };

    self.makeComment = function() {
        var thisQuestion = $("#questionId").val();
        var commentObject = {};
        commentObject['userComment'] = self.userComment();
        commentObject['questionId'] = thisQuestion;

        if(self.originalCommentId() !== null && self.originalCommentId() != '') {
            if(!self.commenting) {
                $.postJSON('/questions/' + thisQuestion + '/comment/' + self.originalCommentId() + '/respond' , commentObject, function(data) {
                    self.commenting = false;
                    if(data.hasErrors) {
                        self.errorMessage(data.errorMessage);
                        self.hasErrors(true);
                        $("#generalAlert").show();
                    } else {
                        self.commentSection.hide();
                        self.userComment('');
                        self.hasErrors(false);
                        self.errorMessage('');
                        self.loadComments();
                    }
                });
            }
        } else {
            console.log('it was a new comment');
            if(!self.commenting) {
                $.postJSON('/questions/' + thisQuestion + '/comment' , commentObject, function(data) {
                    self.commenting = false;
                    if(data.hasErrors) {
                        self.errorMessage(data.errorMessage);
                        self.hasErrors(true);
                        $("#generalAlert").show();
                    } else {
                        self.commentSection.hide();
                        self.userComment('');
                        self.hasErrors(false);
                        self.errorMessage('');
                        self.loadComments();
                    }
                });
            }
        }


    };
    self.loadComments();
};

new Mdjs().on($('#contentText'));
var specificQuestion = new SpecificQuestion();
ko.applyBindings(specificQuestion, $("#page-inner")[0]);
