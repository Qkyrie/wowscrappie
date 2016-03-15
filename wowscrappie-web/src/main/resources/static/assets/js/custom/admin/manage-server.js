var ManageServerVM  = function() {

    var self = this;
    self.overallHealth = ko.observable('');
    self.diskspaceHealth = ko.observable('');
    self.redisHealth = ko.observable('');
    self.dbHealth = ko.observable('');

    self.getHealth = function() {
        $.get('/manage/health', function(data) {
            self.overallHealth(data.status);
            self.diskspaceHealth(data.diskSpace);
            self.redisHealth(data.redis);
            self.dbHealth(data.db);
        });
    };
    self.getHealth();
};

var manageServerVM = new ManageServerVM();
ko.applyBindings(manageServerVM);
