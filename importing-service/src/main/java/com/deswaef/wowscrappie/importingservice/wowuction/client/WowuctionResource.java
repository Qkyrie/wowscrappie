package com.deswaef.wowscrappie.importingservice.wowuction.client;

import au.com.bytecode.opencsv.CSVReader;
import com.deswaef.wowscrappie.importingservice.wowuction.client.model.Entry;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class WowuctionResource {

    public static final char SEPARATOR = '\t';

    public List<Entry> readEntries(final String url) throws Exception {
        URL targetUrl = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(targetUrl.openStream()));
        CSVReader reader = new CSVReader(in, SEPARATOR);
        return reader.readAll()
                .stream()
                .skip(1)
                .map(entryMapper())
                .collect(Collectors.toList());
    }

    private Function<String[], Entry> entryMapper() {
        return entry -> Entry.createEntry()
                .realm(entry[0])
                .exportTime(entry[1])
                .pMktPriceDate(entry[2])
                .reserved(entry[3])
                .itemId(new Long(entry[4]))
                .itemName(entry[5])
                .ahMarketPriceCoppers(new Long(entry[6]))
                .quantity(new Long(entry[7]))
                .ahMarketPrice(new Double(entry[8]))
                .ahMinimumPrice(new Double(entry[9]))
                .medianMarketPrice(new Double(entry[10]))
                .marketPriceStdev(new Double(entry[11]))
                .todaysPMktPrice(new Double(entry[12]))
                .pmktPriceStdev(new Double(entry[13]))
                .dailyPriceChange(new Double(entry[14]))
                .dailyAveragePosted(new Double(entry[15]))
                .dailyAverageSold(new Double(entry[16]))
                .estimatedDemand(new Double(entry[17]));
    }

    public String[] readHeader(final String url) throws Exception {
        URL targetUrl = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(targetUrl.openStream()));
        CSVReader reader = new CSVReader(in, SEPARATOR);
        return reader.readAll()
                .stream()
                .findFirst()
                .get();
    }


}
