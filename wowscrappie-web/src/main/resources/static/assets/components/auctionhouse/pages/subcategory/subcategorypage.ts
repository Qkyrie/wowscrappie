import {bootstrap}    from 'angular2/platform/browser'
import {SubCategory} from './subcategory'
import {HTTP_BINDINGS, HTTP_PROVIDERS, Headers, RequestOptions, BaseRequestOptions} from 'angular2/http';

bootstrap(SubCategory, HTTP_PROVIDERS);