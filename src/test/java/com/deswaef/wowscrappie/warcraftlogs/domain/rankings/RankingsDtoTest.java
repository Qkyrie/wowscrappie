package com.deswaef.wowscrappie.warcraftlogs.domain.rankings;

import com.deswaef.wowscrappie.util.AbstractDtoTest;
import com.deswaef.wowscrappie.warcraftlogs.WarcraftlogsConversionException;
import org.junit.Test;

import java.io.IOException;

import static org.fest.assertions.Assertions.assertThat;

/**
 * User: Quinten
 * Date: 30-4-2015
 * Time: 00:17
 *
 * @author Quinten De Swaef
 */
public class RankingsDtoTest extends AbstractDtoTest {

    @Test
    public void testFromJson() throws IOException, WarcraftlogsConversionException {
        String result = readMarshalledFile("/warcraftlogs/rankings.json");
        Rankings rankings = Rankings.fromJson(result);
        assertThat(rankings.getTotal()).isEqualTo(449337L);
    }

    @Test
    public void getsTop200Rankings() throws IOException, WarcraftlogsConversionException{
        String result = readMarshalledFile("/warcraftlogs/rankings.json");
        Rankings rankings = Rankings.fromJson(result);
        assertThat(rankings.getRankings()).hasSize(200);
    }

    @Test
    public void rankingsHaveAllData() throws IOException, WarcraftlogsConversionException {
        String result = readMarshalledFile("/warcraftlogs/rankings.json");
        Rankings rankings = Rankings.fromJson(result);
        Ranking actual = rankings.getRankings().get(0);
        assertThat(actual.getClassName()).isEqualTo(2L);
        assertThat(actual.getSpec()).isEqualTo(1L);
        assertThat(actual.getTotal()).isEqualTo(73326.6);
        assertThat(actual.getDuration()).isEqualTo(139252);
        assertThat(actual.getStartTime()).isEqualTo(1430068979639L);
        assertThat(actual.getFightID()).isEqualTo(4);
        assertThat(actual.getReportID()).isEqualTo("hLGf3BA4Z1qDzWtH");
        assertThat(actual.getGuild()).isEqualTo("The Unnamed");
        assertThat(actual.getServer()).isEqualTo("Blackrock");
        assertThat(actual.getItemLevel()).isEqualTo(702);
        assertThat(actual.getSize()).isEqualTo(23);
    }
}