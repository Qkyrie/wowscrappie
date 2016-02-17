package com.deswaef.wowscrappie.auctionhouse.controller.dto;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class GoldDtoTest {

    @Test
    public void zeroAmountCreatesEmptyObject() throws Exception {
        GoldDto goldDto = GoldDto.fromDouble(0);

        assertThat(goldDto.getCoppers()).isEqualTo(0);
        assertThat(goldDto.getSilver()).isEqualTo(0);
        assertThat(goldDto.getGold()).isEqualTo(0);
        assertThat(goldDto.getFullCoppers()).isEqualTo(0);

        assertThat(goldDto.getPrinted())
                .isEqualTo("0g 0s 0c");
    }

    @Test
    public void onlyCoppersCreatesCorrectValue() throws Exception {
        GoldDto goldDto = GoldDto.fromDouble(20);

        assertThat(goldDto.getCoppers()).isEqualTo(20);
        assertThat(goldDto.getSilver()).isEqualTo(0);
        assertThat(goldDto.getGold()).isEqualTo(0);
        assertThat(goldDto.getFullCoppers()).isEqualTo(20);

        assertThat(goldDto.getPrinted())
                .isEqualTo("0g 0s 20c");
    }

    @Test
    public void coppersAndSilversHasCorrectValue() throws Exception {
        GoldDto goldDto = GoldDto.fromDouble(2050);

        assertThat(goldDto.getCoppers()).isEqualTo(50);
        assertThat(goldDto.getSilver()).isEqualTo(20);
        assertThat(goldDto.getGold()).isEqualTo(0);
        assertThat(goldDto.getFullCoppers()).isEqualTo(2050);

        assertThat(goldDto.getPrinted())
                .isEqualTo("0g 20s 50c");
    }

    @Test
    public void goldSilverAndCoppersHasCorrectValue() throws Exception {
        GoldDto goldDto = GoldDto.fromDouble(302050);

        assertThat(goldDto.getCoppers()).isEqualTo(50);
        assertThat(goldDto.getSilver()).isEqualTo(20);
        assertThat(goldDto.getGold()).isEqualTo(30);
        assertThat(goldDto.getFullCoppers()).isEqualTo(302050);

        assertThat(goldDto.getPrinted())
                .isEqualTo("30g 20s 50c");
    }

    @Test
    public void onlyGoldHasCorrectValue() throws Exception {
        GoldDto goldDto = GoldDto.fromDouble(10000000);

        assertThat(goldDto.getCoppers()).isEqualTo(0);
        assertThat(goldDto.getSilver()).isEqualTo(0);
        assertThat(goldDto.getGold()).isEqualTo(1000);
        assertThat(goldDto.getFullCoppers()).isEqualTo(10000000);

        assertThat(goldDto.getPrinted())
                .isEqualTo("1000g 0s 0c");
    }

    @Test
    public void commaValuesAreNeglected() throws Exception {
        GoldDto goldDto = GoldDto.fromDouble(302050.12364);

        assertThat(goldDto.getCoppers()).isEqualTo(50);
        assertThat(goldDto.getSilver()).isEqualTo(20);
        assertThat(goldDto.getGold()).isEqualTo(30);
        assertThat(goldDto.getFullCoppers()).isEqualTo(302050);

        assertThat(goldDto.getPrinted())
                .isEqualTo("30g 20s 50c");
    }
}