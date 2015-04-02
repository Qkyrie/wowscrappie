var SlidingHeader = function() {
    var self = this;

    self.manualPreference = false;

    self.goingUp = $("#arrowUpBtn");
    self.goingDown = $("#arrowDownBtn");

    self.hideHeader_proxy = function(afterHide) {
        $("#top-wrapper").slideUp('fast', function() {
            self.goingUp.hide();
            self.goingDown.show();
            console.log(afterHide);
            if(afterHide !== undefined) {
                afterHide();
            }
        });
    };

    self.showHeader_proxy = function(afterShow) {
        $("#top-wrapper").slideDown('fast', function() {
            self.goingUp.show();
            self.goingDown.hide();
            console.log(afterShow);
            if(afterShow !== undefined) {
                afterShow();
            }
        });
    };

    self.goingDown.click(function () {
        self.manualPreference = true;
        self.showHeader_proxy();
    });

    self.goingUp.click(function () {
        self.hideHeader_proxy();
    });

    self.showHeader = function(afterShow) {
        self.showHeader_proxy(afterShow);
    };

    self.hideHeader = function(afterHide) {
        self.hideHeader_proxy(afterHide);
    };
};

var slidingHeader = new SlidingHeader();