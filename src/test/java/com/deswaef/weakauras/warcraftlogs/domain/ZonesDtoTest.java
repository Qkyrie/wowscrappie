package com.deswaef.weakauras.warcraftlogs.domain;

import com.deswaef.weakauras.util.AbstractDtoTest;
import com.deswaef.weakauras.warcraftlogs.WarcraftlogsConversionException;
import com.deswaef.weakauras.warcraftlogs.domain.zones.Zone;
import com.deswaef.weakauras.warcraftlogs.domain.zones.Zones;
import org.junit.Test;

import java.io.IOException;
import java.util.Collection;

import static org.fest.assertions.Assertions.assertThat;

public class ZonesDtoTest extends AbstractDtoTest {

    @Test
    public void testFromJson() throws IOException, WarcraftlogsConversionException {
        String s = readMarshalledFile("/warcraftlogs/zones.json");
        Collection<Zone> zones = Zones.fromJson(s);
        assertThat(zones).hasSize(6);
        zones.stream()
                .forEach(this::assertZoneValues);
    }

    private void assertZoneValues(Zone zone) {
        assertThat(zone.getId()).isNotNull();
        assertThat(zone.getName()).isNotEmpty();
        assertThat(zone.getEncounters()).isNotEmpty();
    }

}