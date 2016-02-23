import {bootstrap}    from 'angular2/platform/browser'
import {AuctionHouseSearch} from './auctionhousesearch'
import {HTTP_BINDINGS, HTTP_PROVIDERS, Headers, RequestOptions, BaseRequestOptions} from 'angular2/http';

bootstrap(AuctionHouseSearch, HTTP_PROVIDERS);