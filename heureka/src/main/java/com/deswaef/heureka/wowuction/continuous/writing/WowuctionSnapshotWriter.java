package com.deswaef.heureka.wowuction.continuous.writing;

import com.deswaef.heureka.wowuction.continuous.dto.AuctionHouseSnapshotHolder;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@StepScope
public class WowuctionSnapshotWriter implements ItemWriter<AuctionHouseSnapshotHolder> {

    @Value("#{jobParameters['realmId']}")
    private Long realmId;

    @Override
    public void write(List<? extends AuctionHouseSnapshotHolder> items) throws Exception {
        //not writing anything atm, don't really want to import from wowuction...;
    }
}
