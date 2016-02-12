package com.deswaef.wowscrappie.auctionhouse.importer;

import com.deswaef.wowscrappie.realm.domain.Realm;

public interface AuctionsImporter {
    void importAuctions(long realmId);
    void importAuctions(Realm realm);
}
