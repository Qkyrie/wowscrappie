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

var EditQuestionVM = function() {

    var self = this;

    self.question = ko.observable($("#actualQuestion").val());
    self.errorMessage = ko.observable('');
    self.hasErrors = ko.observable(false);
    self.posting = false;

    self.editit = function() {
        var questionObject = {};
        questionObject['question'] = self.question();
        var questionId = $("#questionId").val();
        questionObject['id'] = questionId;
        if(!self.posting) {
            self.errorMessage('');
            self.hasErrors(false);
            self.posting = true;

            $.postJSON('/questions/' + questionId + '/edit' , questionObject, function(data) {
                self.posting = false;
                if(data.hasErrors) {
                    self.errorMessage(data.errorMessage);
                    self.hasErrors(true);
                } else {
                    self.question('');
                    window.location = '/questions/' + data.id;
                }
            });
        }
    };

};

var editQuestionVM = new EditQuestionVM();
ko.applyBindings(editQuestionVM, $("#page-inner")[0]);
