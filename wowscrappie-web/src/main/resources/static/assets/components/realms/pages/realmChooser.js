System.register(['rxjs/add/operator/map', 'angular2/core', '../services/RealmService'], function(exports_1) {
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, RealmService_1;
    var RealmChooser;
    return {
        setters:[
            function (_1) {},
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (RealmService_1_1) {
                RealmService_1 = RealmService_1_1;
            }],
        execute: function() {
            RealmChooser = (function () {
                function RealmChooser(realmService) {
                    var _this = this;
                    this.realmService = realmService;
                    this.filter = "";
                    this.filteredRealms = [];
                    this.realms = [];
                    realmService.findAll()
                        .subscribe(function (realm) {
                        realm.forEach(function (element) {
                            _this.realms.push(element);
                            _this.filteredRealms.push(element);
                        });
                    });
                    realmService.findCurrent()
                        .subscribe(function (realm) {
                        _this.currentRealm = realm;
                    });
                }
                RealmChooser.prototype.iChoseOne = function (realm) {
                    var _this = this;
                    this.realmService.chooseCurrent(realm.id)
                        .subscribe(function (newRealm) {
                        _this.currentRealm = newRealm;
                    }, function (err) { return console.log(err); }, function () { return console.log("Done"); });
                };
                // without strong typing
                RealmChooser.prototype.onKey = function (event) {
                    if (this.filter != "") {
                        this.filteredRealms = [];
                        for (var i = 0; i < this.realms.length; i++) {
                            var currentRealm = this.realms[i];
                            if (currentRealm.name.indexOf(this.filter) > -1 || currentRealm.slug.indexOf(this.filter) > -1) {
                                this.filteredRealms.push(currentRealm);
                            }
                        }
                    }
                    else {
                        this.filteredRealms = this.realms;
                    }
                };
                RealmChooser = __decorate([
                    core_1.Component({
                        selector: 'choose-realm',
                        providers: [RealmService_1.RealmService],
                        template: "\n                    <div id=\"page-inner\">\n                        <div *ngIf=\"currentRealm == null\" class=\"row\">\n                            <div class=\"col-md-12\">\n                                <h2>\n                                    Please Pick your default realm\n                                </h2>\n                            </div>\n                        </div>\n                         <div *ngIf=\"currentRealm != null\" class=\"row\">\n                            <div class=\"col-md-12\">\n                                <h2>\n                                    Your Current Realm is {{currentRealm.locality}}-{{currentRealm.name}}\n                                    <small>Click a new one if you wish to change</small>\n                                </h2>\n                            </div>\n                        </div>\n                        <div class=\"row\">\n                            <div class=\"col-md-10 col-md-offset-1\">\n                                    <div class=\"form-group label-floating\">\n                                      <label class=\"control-label\" for=\"filter\">Search Realm</label>\n                                      <input (keyup)=\"onKey($event)\" [(ngModel)]=\"filter\" class=\"form-control\" id=\"filter\" type=\"text\" />\n                                      {{filter}}\n                                    </div>\n                            </div>\n                        </div>\n\n                       <div *ngFor=\"#realm of filteredRealms; var index=index\" class=\"col-md-2\">\n                            <span (click)=\"iChoseOne(realm)\" class=\"btn btn-block btn-raised btn-success btn-xs\">{{realm.locality}}-{{realm.name}}</span>\n                       </div>\n                     </div>\n    "
                    }), 
                    __metadata('design:paramtypes', [RealmService_1.RealmService])
                ], RealmChooser);
                return RealmChooser;
            })();
            exports_1("RealmChooser", RealmChooser);
        }
    }
});
//# sourceMappingURL=realmChooser.js.map