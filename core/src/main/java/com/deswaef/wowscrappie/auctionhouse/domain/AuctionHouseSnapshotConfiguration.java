package com.deswaef.wowscrappie.auctionhouse.domain;

import com.deswaef.wowscrappie.realm.domain.Realm;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "auctionhousesnapshot_realm_configuration")
public class AuctionHouseSnapshotConfiguration {

    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    @JoinColumn(name = "realm_id")
    private Realm realm;
    @Column(name = "needs_update")
    private boolean needsUpdate;
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;

    public Long getId() {
        return id;
    }

    public AuctionHouseSnapshotConfiguration setId(Long id) {
        this.id = id;
        return this;
    }

    public Realm getRealm() {
        return realm;
    }

    public AuctionHouseSnapshotConfiguration setRealm(Realm realm) {
        this.realm = realm;
        return this;
    }

    public boolean isNeedsUpdate() {
        return needsUpdate;
    }

    public AuctionHouseSnapshotConfiguration setNeedsUpdate(boolean needsUpdate) {
        this.needsUpdate = needsUpdate;
        return this;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public AuctionHouseSnapshotConfiguration setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
        return this;
    }

    @Override
    public String toString() {
        return "AuctionHouseSnapshotConfiguration{" +
                "id=" + id +
                ", realm=" + realm +
                ", needsUpdate=" + needsUpdate +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
