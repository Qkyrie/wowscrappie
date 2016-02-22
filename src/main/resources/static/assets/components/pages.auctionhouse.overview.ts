import {bootstrap}    from 'angular2/platform/browser'
import {SingleItemSearch} from './pages.auctionhouse.itemsearch'
import {HTTP_BINDINGS, HTTP_PROVIDERS, Headers, RequestOptions, BaseRequestOptions} from 'angular2/http';

bootstrap(SingleItemSearch, HTTP_PROVIDERS);