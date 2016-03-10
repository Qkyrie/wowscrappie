System.register([], function(exports_1) {
    var Realm;
    return {
        setters:[],
        execute: function() {
            Realm = (function () {
                function Realm(id, slug, name, locality) {
                    this.id = id;
                    this.slug = slug;
                    this.name = name;
                    this.locality = locality;
                }
                return Realm;
            })();
            exports_1("Realm", Realm);
        }
    }
});
//# sourceMappingURL=Realm.js.map