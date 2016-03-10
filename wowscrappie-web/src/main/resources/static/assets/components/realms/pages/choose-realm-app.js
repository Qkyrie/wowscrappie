System.register(['angular2/platform/browser', './realmChooser', 'angular2/http'], function(exports_1) {
    var browser_1, realmChooser_1, http_1;
    return {
        setters:[
            function (browser_1_1) {
                browser_1 = browser_1_1;
            },
            function (realmChooser_1_1) {
                realmChooser_1 = realmChooser_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            }],
        execute: function() {
            browser_1.bootstrap(realmChooser_1.RealmChooser, http_1.HTTP_PROVIDERS);
        }
    }
});
//# sourceMappingURL=choose-realm-app.js.map