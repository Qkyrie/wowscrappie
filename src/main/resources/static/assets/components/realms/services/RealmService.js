System.register(['rxjs/add/operator/map', 'rxjs/add/operator/filter', 'rxjs/add/operator/catch', 'angular2/core', 'angular2/http', '../entity/Realm'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, http_1, Realm_1;
    var RealmService;
    return {
        setters:[
            function (_1) {},
            function (_2) {},
            function (_3) {},
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (Realm_1_1) {
                Realm_1 = Realm_1_1;
            }],
        execute: function() {
            RealmService = (function () {
                function RealmService(http) {
                    this.http = http;
                }
                RealmService.prototype.findCurrent = function () {
                    return this.http.get("/realm/current")
                        .filter(function (responseData) {
                        return responseData.statusText == "Ok";
                    })
                        .map(function (responseData) {
                        return responseData.json();
                    })
                        .map(function (element) {
                        if (element) {
                            return new Realm_1.Realm(element.id, element.slug, element.name, element.locality);
                        }
                        else {
                            return null;
                        }
                    });
                };
                RealmService.prototype.chooseCurrent = function (realmId) {
                    return this.http.post("/realm/choose/" + realmId, null)
                        .filter(function (responseData) {
                        return responseData.statusText == "Ok";
                    })
                        .map(function (responseData) {
                        return responseData.json();
                    })
                        .map(function (element) {
                        if (element) {
                            return new Realm_1.Realm(element.id, element.slug, element.name, element.locality);
                        }
                    });
                };
                RealmService.prototype.findAll = function () {
                    return this.http.get('/rest/realms')
                        .filter(function (responseData) {
                        return responseData.statusText == "Ok";
                    })
                        .map(function (responseData) {
                        return responseData.json();
                    })
                        .map(function (elements) {
                        if (elements) {
                            return elements.map(function (element) {
                                return new Realm_1.Realm(element.id, element.slug, element.name, element.locality);
                            });
                        }
                        else {
                            return [];
                        }
                    });
                };
                RealmService = __decorate([
                    core_1.Injectable(), 
                    __metadata('design:paramtypes', [http_1.Http])
                ], RealmService);
                return RealmService;
            })();
            exports_1("RealmService", RealmService);
        }
    }
});
//# sourceMappingURL=RealmService.js.map