import 'rxjs/add/operator/map';
import {Component, AfterViewChecked, ElementRef} from 'angular2/core';
import {RealmService} from '../services/RealmService'
import {Realm} from '../entity/Realm'

@Component({
    selector: 'choose-realm',
    providers: [RealmService],
    template: `
                    <div id="page-inner">
                        <div *ngIf="currentRealm == null" class="row">
                            <div class="col-md-12">
                                <h2>
                                    Please Pick your default realm
                                </h2>
                            </div>
                        </div>
                         <div *ngIf="currentRealm != null" class="row">
                            <div class="col-md-12">
                                <h2>
                                    Your Current Realm is {{currentRealm.locality}}-{{currentRealm.name}}
                                    <small>Click a new one if you wish to change</small>
                                </h2>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-10 col-md-offset-1">
                                    <div class="form-group label-floating">
                                      <label class="control-label" for="filter">Search Realm</label>
                                      <input (keyup)="onKey($event)" [(ngModel)]="filter" class="form-control" id="filter" type="text" />
                                      {{filter}}
                                    </div>
                            </div>
                        </div>

                       <div *ngFor="#realm of filteredRealms; var index=index" class="col-md-2">
                            <span (click)="iChoseOne(realm)" class="btn btn-block btn-raised btn-success btn-xs">{{realm.locality}}-{{realm.name}}</span>
                       </div>
                     </div>
    `
})
export class RealmChooser {

    filter:string = "";
    filteredRealms:Realm[] = [];
    realms:Realm[] = [];
    currentRealm:Realm;

    constructor(public realmService:RealmService) {
        realmService.findAll()
            .subscribe(realm => {
                realm.forEach((element) => {
                        this.realms.push(element);
                        this.filteredRealms.push(element);
                    }
                );
            });

        realmService.findCurrent()
            .subscribe(realm => {
                this.currentRealm = realm;
            });
    }

    iChoseOne(realm:Realm) {
        this.realmService.chooseCurrent(realm.id)
            .subscribe((newRealm) => {
                this.currentRealm = newRealm;
            },
                (err)=>console.log(err),
                ()=>console.log("Done")
            );
    }

    // without strong typing
    onKey(event:any) {
        if (this.filter != "") {
            this.filteredRealms = [];
            for (var i = 0; i < this.realms.length; i++) {
                var currentRealm = this.realms[i];
                if (currentRealm.name.indexOf(this.filter) > -1 || currentRealm.slug.indexOf(this.filter) > -1) {
                    this.filteredRealms.push(currentRealm);
                }
            }
        } else {
            this.filteredRealms = this.realms;
        }
    }

}