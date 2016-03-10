package com.deswaef.wowscrappie.importingservice.battlenet.wow.auctions.client.domain;

import com.deswaef.wowscrappie.importingservice.battlenet.wow.domain.AuctionSnapshot;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class AuctionResponseFileTest {

    public static final long WITH_MODIFIERS_COUNT = 4791L;
    private String responseAsJson;
    private AuctionSnapshot auctionResponse;

    @Before
    public void setUp() throws Exception {
        responseAsJson = readMarshalledFile("/battlenet/auctions/auctions2.json");
        auctionResponse = AuctionSnapshot.fromJson(responseAsJson);
    }

    @Test
    public void getAuctionResponse() throws Exception {
        assertThat(auctionResponse).isNotNull();
    }

    @Test
    public void getAuctionResponseHasRealms() throws Exception {
        List<RealmItem> realms = auctionResponse.realms();

        assertThat(
                realms
        ).isNotEmpty();

        realms
                .stream()
                .forEach(
                        x -> {
                            assertThat(x.name()).isNotEmpty();
                            assertThat(x.slug()).isNotEmpty();
                        }
                );
    }

    @Test
    public void mandatoryAuctionValues() throws Exception {
        List<AuctionItem> auctions = auctionResponse.auctions();
        assertThat(auctions).isNotEmpty();
        auctions
                .stream()
                .forEach(
                        x -> {
                            assertThat(x.auc()).isGreaterThan(0);
                            assertThat(x.item()).isGreaterThan(0);
                            assertThat(x.owner()).isNotEmpty();
                            assertThat(x.ownerRealm()).isEqualTo("Silvermoon");
                        }
                );
    }

    @Test
    public void itemModifiersMappedCorrectly() throws Exception {
        List<AuctionItem> auctions = auctionResponse.auctions();
        Stream<AuctionItem> auctionItemStream = auctions
                .stream()
                .filter(x -> !x.modifiers().isEmpty());

        auctionItemStream
                .map(AuctionItem::modifiers)
                .flatMap(Collection::stream)
                .forEach(
                        modifier -> {
                            assertThat(modifier.type()).isGreaterThan(0);
                            assertThat(modifier.value()).isGreaterThan(0);
                        }
                );
    }

    @Test
    public void petSpecificIds() throws Exception {

        List<AuctionItem> auctions = auctionResponse.auctions();

        auctions.stream()
                .filter(x -> x.petSpeciesId() > 0)
                .forEach(
                        x -> {
                            assertThat(x.petBreedId() > 0);
                            assertThat(x.petLevel() > 0);
                            assertThat(x.petLevel() < 20);
                            assertThat(x.petQualityId() > 0);
                        }
                );

    }

    @Test
    public void bonusListsMappedCorrectly() throws Exception {
        List<AuctionItem> auctions = auctionResponse.auctions();

        auctions.stream()
                .flatMap(x -> x.bonusLists().stream())
                .forEach(
                        bonusList -> {
                            assertThat(bonusList.bonusListId() > 0);
                        }
                );
    }

    public static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    public String readMarshalledFile(String s) throws IOException {
        URL url = this.getClass().getResource(s);
        File testProperties = new File(url.getFile());
        return readFile(URLDecoder.decode(testProperties.getPath()), Charset.defaultCharset());
    }
}