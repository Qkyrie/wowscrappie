package com.deswaef.wowscrappie.importingservice.warcraftlogs.domain.rankings;

import com.deswaef.wowscrappie.importingservice.util.AbstractDtoTest;
import com.deswaef.wowscrappie.importingservice.warcraftlogs.WarcraftlogsConversionException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;


public class RankingsDtoTest extends AbstractDtoTest {

    @Test
    public void testFromJson() throws IOException, WarcraftlogsConversionException {
        String result = readMarshalledFile("/warcraftlogs/rankings.json");
        Rankings rankings = Rankings.fromJson(result);
        Assertions.assertThat(rankings.getTotal()).isEqualTo(449337L);
    }

    @Test
    public void getsTop200Rankings() throws IOException, WarcraftlogsConversionException{
        String result = readMarshalledFile("/warcraftlogs/rankings.json");
        Rankings rankings = Rankings.fromJson(result);
        Assertions.assertThat(rankings.getRankings()).hasSize(200);
    }

    @Test
    public void rankingsHaveAllData() throws IOException, WarcraftlogsConversionException {
        String result = readMarshalledFile("/warcraftlogs/rankings.json");
        Rankings rankings = Rankings.fromJson(result);
        Ranking actual = rankings.getRankings().get(0);
        Assertions.assertThat(actual.getClassName()).isEqualTo(2L);
        Assertions.assertThat(actual.getSpec()).isEqualTo(1L);
        Assertions.assertThat(actual.getTotal()).isEqualTo(73326.6);
        Assertions.assertThat(actual.getDuration()).isEqualTo(139252);
        Assertions.assertThat(actual.getStartTime()).isEqualTo(1430068979639L);
        Assertions.assertThat(actual.getFightID()).isEqualTo(4);
        Assertions.assertThat(actual.getReportID()).isEqualTo("hLGf3BA4Z1qDzWtH");
        Assertions.assertThat(actual.getGuild()).isEqualTo("The Unnamed");
        Assertions.assertThat(actual.getServer()).isEqualTo("Blackrock");
        Assertions.assertThat(actual.getItemLevel()).isEqualTo(702);
        Assertions.assertThat(actual.getSize()).isEqualTo(23);
    }
}