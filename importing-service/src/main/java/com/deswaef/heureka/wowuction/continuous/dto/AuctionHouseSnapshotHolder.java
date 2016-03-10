package com.deswaef.heureka.wowuction.continuous.dto;

public class AuctionHouseSnapshotHolder {
    private Long realmId;
    private Long itemId;

    public AuctionHouseSnapshotHolder(Long realmId, Long itemId) {
        this.realmId = realmId;
        this.itemId = itemId;
    }

    public Long realmId() {
        return realmId;
    }
    public Long itemId() {
        return itemId;
    }
}