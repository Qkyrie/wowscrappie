package com.deswaef.wowscrappie.auctionhouse.exporter;

import com.deswaef.wowscrappie.realm.domain.Realm;

public interface AuctionsExporter {

    void deleteOldSnapshotsFor(Realm realm);

}
