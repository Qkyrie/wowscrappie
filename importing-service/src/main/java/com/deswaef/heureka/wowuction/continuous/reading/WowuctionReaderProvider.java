package com.deswaef.heureka.wowuction.continuous.reading;

import com.deswaef.heureka.wowuction.client.WowuctionResource;
import com.deswaef.heureka.wowuction.client.model.Entry;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WowuctionReaderProvider {

    @Bean(name = "import-wowuction-data-reader")
    @StepScope
    public ItemReader<? extends Entry> reader(final WowuctionResource dataFetcher, @Value("#{jobParameters['uri']}") String uri) {
        return new WowuctionDataReader(dataFetcher, uri);
    }
}
