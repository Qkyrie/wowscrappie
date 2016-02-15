package com.deswaef.heureka.warcraftlogs.domain.rankings;

import com.deswaef.wowscrappie.util.AbstractDtoTest;
import com.deswaef.heureka.warcraftlogs.WarcraftlogsConversionException;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;

public class CharacterRankingsDtoTest extends AbstractDtoTest {
    @Test
    public void validUserContainsValidInformation() throws IOException, WarcraftlogsConversionException {
        String json = readMarshalledFile("/warcraftlogs/characterrankings.json");
        Collection<CharacterRanking> characterRankings = CharacterRankings.fromJson(json);
        assertThat(characterRankings).isNotEmpty();
        characterRankings
                .stream()
                .forEach(this::validate);
    }

    private void validate(CharacterRanking characterRanking) {
        assertThat(characterRanking.getClassId()).isEqualTo(1L);
        assertThat(characterRanking.getSpec()).isEqualTo(2L);
        assertThat(characterRanking.getDifficulty()).isPositive();
        assertThat(characterRanking.getItemLevel()).isPositive();
        assertThat(characterRanking.getSize()).isPositive();
        assertThat(characterRanking.getDuration()).isPositive();
        assertThat(characterRanking.getStartTime()).isPositive();
        assertThat(characterRanking.getTotal()).isPositive();
    }
}