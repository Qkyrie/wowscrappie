System.register([], function(exports_1) {
    var Realm;
    return {
        setters:[],
        execute: function() {
            Realm = (function () {
                function Realm(id, name, locality) {
                    this.id = id;
                    this.name = name;
                    this.locality = locality;
                }
                return Realm;
            })();
            exports_1("Realm", Realm);
        }
    }
});
//# sourceMappingURL=realm.js.map