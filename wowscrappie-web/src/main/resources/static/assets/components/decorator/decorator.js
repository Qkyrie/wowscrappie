var DecoratorModel = function () {
    var self = this;
    self.realmSelectButton = $("#realmSelectButton");

    var init = function () {
        $.get("/realm/current", function (realm) {
            self.realmSelectButton.html(realm.locality + '-' + realm.name);
            self.realmSelectButton.show();
        }).error(function () {
            self.realmSelectButton.html("no realm selected");
            self.realmSelectButton.show();
        });
    };
    init();
};

$(document).ready(function () {
    new DecoratorModel();
});
