import {bootstrap}    from 'angular2/platform/browser'
import {RealmChooser} from './realmChooser'
import {HTTP_BINDINGS, HTTP_PROVIDERS, Headers, RequestOptions, BaseRequestOptions} from 'angular2/http';

bootstrap(RealmChooser, HTTP_PROVIDERS);