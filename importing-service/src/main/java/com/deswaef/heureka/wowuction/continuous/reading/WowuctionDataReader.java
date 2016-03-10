package com.deswaef.heureka.wowuction.continuous.reading;

import com.deswaef.heureka.wowuction.client.WowuctionResource;
import com.deswaef.heureka.wowuction.client.model.Entry;
import org.springframework.batch.item.ItemReader;

import java.util.ArrayList;
import java.util.List;

public class WowuctionDataReader implements ItemReader<Entry> {

    private WowuctionResource dataFetcher;

    private List<Entry> entries;

    public WowuctionDataReader(WowuctionResource dataFetcher, String uri) {
        this.dataFetcher = dataFetcher;
        try {
            this.entries = this.dataFetcher.readEntries(uri);
        } catch (Exception e) {
            entries = new ArrayList<>();
        }
    }

    @Override
    public Entry read() throws Exception {
        if (!entries.isEmpty()) {
            return entries.remove(0);
        }
        return null;
    }
}
