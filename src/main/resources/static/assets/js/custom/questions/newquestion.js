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

var NewQuestionVM = function() {

    var self = this;

    self.title = ko.observable('');
    self.question = ko.observable('');
    self.errorMessage = ko.observable('');
    self.hasErrors = ko.observable(false);
    self.posting = false;

    self.askit = function() {
        var questionObject = {};
        questionObject['title'] = self.title();
        questionObject['question'] = self.question();

        //TODO: do some validation
        if(!self.posting) {
            self.errorMessage('');
            self.hasErrors(false);
            self.posting = true;

            $.postJSON('/questions/new', questionObject, function(data) {
                self.posting = false;
                if(data.hasErrors) {
                    self.errorMessage(data.errorMessage);
                    self.hasErrors(true);
                } else {
                    self.title('');
                    self.question('');
                    window.location = '/questions/' + data.id;
                }
            });
        }
    };

};

var newQuestionVM = new NewQuestionVM();
ko.applyBindings(newQuestionVM, $("#page-inner")[0]);
