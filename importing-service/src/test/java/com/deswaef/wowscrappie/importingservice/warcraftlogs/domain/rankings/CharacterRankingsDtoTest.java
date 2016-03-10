package com.deswaef.wowscrappie.importingservice.warcraftlogs.domain.rankings;

import com.deswaef.wowscrappie.importingservice.util.AbstractDtoTest;
import com.deswaef.wowscrappie.importingservice.warcraftlogs.WarcraftlogsConversionException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;

public class CharacterRankingsDtoTest extends AbstractDtoTest {
    @Test
    public void validUserContainsValidInformation() throws IOException, WarcraftlogsConversionException {
        String json = readMarshalledFile("/warcraftlogs/characterrankings.json");
        Collection<CharacterRanking> characterRankings = CharacterRankings.fromJson(json);
        Assertions.assertThat(characterRankings).isNotEmpty();
        characterRankings
                .stream()
                .forEach(this::validate);
    }

    private void validate(CharacterRanking characterRanking) {
        Assertions.assertThat(characterRanking.getClassId()).isEqualTo(1L);
        Assertions.assertThat(characterRanking.getSpec()).isEqualTo(2L);
        Assertions.assertThat(characterRanking.getDifficulty()).isPositive();
        Assertions.assertThat(characterRanking.getItemLevel()).isPositive();
        Assertions.assertThat(characterRanking.getSize()).isPositive();
        Assertions.assertThat(characterRanking.getDuration()).isPositive();
        Assertions.assertThat(characterRanking.getStartTime()).isPositive();
        Assertions.assertThat(characterRanking.getTotal()).isPositive();
    }
}