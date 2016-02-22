System.register(['angular2/platform/browser', './template.page.title'], function(exports_1) {
    var browser_1, template_page_title_1;
    return {
        setters:[
            function (browser_1_1) {
                browser_1 = browser_1_1;
            },
            function (template_page_title_1_1) {
                template_page_title_1 = template_page_title_1_1;
            }],
        execute: function() {
            browser_1.bootstrap(template_page_title_1.AppTitle);
        }
    }
});
//# sourceMappingURL=main.js.map