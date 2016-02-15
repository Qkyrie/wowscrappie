package com.deswaef.wowscrappie.configuration.datacorrection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataCorrectionRunner {

    @Autowired
    private List<DataCorrection> datacorrectors;

    @PostConstruct
    public void init() {
        if (datacorrectors != null) {
            datacorrectors
                    .stream()
                    .forEach(DataCorrection::run);
        }
    }
}
