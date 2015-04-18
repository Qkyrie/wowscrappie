function ApplicationModel(stompClient) {
    var self = this;

    self.notificationCount = $("#notificationCount");
    self.notificationMenu = $("#dropdown-menu-frag");

    self.setNotificationCount = function(amount) {
        $.get('/notifications/unread', function(response) {
            self.notificationMenu.html(response);
        });
        if(amount == 0) {
            self.notificationCount.html("");
            self.notificationCount.hide();
        } else {
            self.notificationCount.html(amount);
            self.notificationCount.show();
        }
    };

    function playNotificationSound() {
        console.log("")
        if ($("#Mp3Me") !== undefined && $("#Mp3Me").length != 0) {
            $("#Mp3Me").bind("ended", function() {
                $("#Mp3Me")[0].src = '/shared/sound/notification';
            });
            $("#Mp3Me")[0].play();
        }
    }

    function addOneToNotificationCount() {
        var html = self.notificationCount.html();
        if(html == undefined || html.trim() == "") {
            self.setNotificationCount(1);
        } else {
            self.setNotificationCount(parseInt(html) + 1);
        }
        playNotificationSound();
    }

    self.connect = function() {
        stompClient.connect({}, function(frame) {
            stompClient.subscribe('/user/topic/notifications', function(message) {
                var notification = JSON.parse(message.body);
                $.snackbar({content: notification.message});
                addOneToNotificationCount();
            });
            stompClient.subscribe('/user/queue/notifications', function(message) {
                var notification = JSON.parse(message.body);
                $.snackbar({content: notification.message});
                addOneToNotificationCount();
            });
            stompClient.subscribe('/app/notifications/amount', function(message) {
                self.setNotificationCount(message.body)
            });
        }, function(error){
            console.log("STOMP protocol error " + error);
        });
    };

}