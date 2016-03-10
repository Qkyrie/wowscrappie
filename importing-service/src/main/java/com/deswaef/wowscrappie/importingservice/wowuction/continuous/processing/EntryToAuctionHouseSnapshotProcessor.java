package com.deswaef.wowscrappie.importingservice.wowuction.continuous.processing;

import com.deswaef.wowscrappie.importingservice.wowuction.client.model.Entry;
import com.deswaef.wowscrappie.importingservice.wowuction.continuous.dto.AuctionHouseSnapshotHolder;
import com.deswaef.wowscrappie.item.domain.Item;
import com.deswaef.wowscrappie.item.service.ItemService;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class EntryToAuctionHouseSnapshotProcessor implements ItemProcessor<Entry, AuctionHouseSnapshotHolder> {


    @Value("#{jobParameters['uri']}")
    private String uri;
    @Value("#{jobParameters['realmId']}")
    private Long realmId;
    @Autowired
    private ItemService itemService;

    @Override
    public AuctionHouseSnapshotHolder process(Entry entry) throws Exception {
        Item item = saveItem(entry);
        return new AuctionHouseSnapshotHolder(realmId, item.getId());
    }

    private Item saveItem(Entry entry) {
        return itemService
                .save(
                        new Item()
                                .setId(entry.itemId())
                                .setName(entry.itemName())
                );
    }
}
