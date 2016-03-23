import {bootstrap}    from 'angular2/platform/browser'
import {AuctionHouseRealm} from './auctionhouserealm'
import {HTTP_BINDINGS, HTTP_PROVIDERS, Headers, RequestOptions, BaseRequestOptions} from 'angular2/http';

bootstrap(AuctionHouseRealm, HTTP_PROVIDERS);