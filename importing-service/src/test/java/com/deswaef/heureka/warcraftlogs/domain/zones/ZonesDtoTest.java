package com.deswaef.heureka.warcraftlogs.domain.zones;

import com.deswaef.heureka.util.AbstractDtoTest;
import com.deswaef.heureka.warcraftlogs.WarcraftlogsConversionException;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;


public class ZonesDtoTest extends AbstractDtoTest {

    @Test
    public void testFromJson() throws IOException, WarcraftlogsConversionException {
        String s = readMarshalledFile("/warcraftlogs/zones.json");
        Collection<Zone> zones = Zones.fromJson(s);
        Assertions.assertThat(zones).hasSize(6);
        zones.stream()
                .forEach(this::assertZoneValues);
    }

    private void assertZoneValues(Zone zone) {
        Assertions.assertThat(zone.getId()).isNotNull();
        Assertions.assertThat(zone.getName()).isNotEmpty();
        Assertions.assertThat(zone.getEncounters()).isNotEmpty();
    }

}