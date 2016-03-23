System.register([], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var Item;
    return {
        setters:[],
        execute: function() {
            Item = (function () {
                function Item(name, id) {
                    this.name = name;
                    this.id = id;
                }
                return Item;
            }());
            exports_1("Item", Item);
        }
    }
});
//# sourceMappingURL=item.js.map