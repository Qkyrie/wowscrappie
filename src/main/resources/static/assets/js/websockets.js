function ApplicationModel(stompClient) {
    var self = this;

    self.notificationCount = $("#notificationCount");

    self.setNotificationCount = function(amount) {
        if(amount == 0) {
            self.notificationCount.html("");
            self.notificationCount.hide();
        } else {
            self.notificationCount.html(amount);
            self.notificationCount.show();
            $.snackbar({content: 'You have new notifications!'});
        }
    };

    function addOneToNotificationCount() {
        var html = self.notificationCount.html();
        if(html == undefined) {
            self.notificationCount.html(1);
        } else {
            self.notificationCount.html(parseInt(html) + 1);
        }
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