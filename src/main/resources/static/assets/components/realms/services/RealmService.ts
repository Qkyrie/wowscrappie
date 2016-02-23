import 'rxjs/add/operator/map';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/catch';
import { Injectable } from 'angular2/core';
import { Http, Response, Request } from 'angular2/http';
import { Realm } from '../entity/Realm';

@Injectable()
export class RealmService {
    constructor(public http:Http) {
    }

    findCurrent() {
        return this.http.get("/realm/current")
            .filter((responseData) => {
                return responseData.statusText == "Ok"
            })
            .map((responseData) => {
                return responseData.json();
            })
            .map((element:Object) => {
                if (element) {
                    return new Realm(
                        element.id,
                        element.slug,
                        element.name,
                        element.locality
                    )
                } else {
                    return null;
                }
            });
    }

    chooseCurrent(realmId:number) {
        return this.http.post("/realm/choose/" + realmId, null)
            .filter((responseData) => {
                return responseData.statusText == "Ok";
            })
            .map((responseData) => {
                return responseData.json();
            })
            .map((element:Object) => {
                if (element) {
                    return new Realm(
                        element.id,
                        element.slug,
                        element.name,
                        element.locality
                    )
                }
            });
    }

    findAll() {
        return this.http.get('/rest/realms')
            .filter((responseData) => {
                return responseData.statusText == "Ok";
            })
            .map((responseData) => {
                return responseData.json();
            })
            .map((elements:Object[]) => {
                if (elements) {
                    return elements.map(element => {
                        return new Realm(
                            element.id,
                            element.slug,
                            element.name,
                            element.locality
                        )
                    });
                } else {
                    return [];
                }
            });
    }

}