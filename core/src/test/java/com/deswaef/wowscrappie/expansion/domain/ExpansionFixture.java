package com.deswaef.wowscrappie.expansion.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneOffset;


public class ExpansionFixture {

    public static Expansion vanilla() {
        return new Expansion()
                .setId(1L)
                .setName("Vanilla")
                .setSlug("vanilla")
                .setStartDate(
                        Date.from(
                                LocalDate.of(2000, 1, 1).atStartOfDay().atOffset(ZoneOffset.UTC).toInstant()
                        )
                );
    }

}