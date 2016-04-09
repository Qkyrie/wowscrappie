package com.deswaef.wowscrappie.expansion.domain;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneOffset;

public class PatchFixture {

    public static Patch fistPatch() {
        return new Patch()
                .setId(1L)
                .setName("Vanilla")
                .setMainFeatures("no features")
                .setExpansion(ExpansionFixture.vanilla())
                .setStartDate(
                        Date.from(
                                LocalDate.of(2000, 1, 1).atStartOfDay().atOffset(ZoneOffset.UTC).toInstant()
                        )
                );
    }

}