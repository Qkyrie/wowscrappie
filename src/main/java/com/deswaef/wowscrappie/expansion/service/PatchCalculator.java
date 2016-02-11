package com.deswaef.wowscrappie.expansion.service;

import com.deswaef.wowscrappie.expansion.domain.Patch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
public class PatchCalculator {

    @Autowired
    Optional<PatchService> patchService;

    public Optional<Patch> calculatePatch(Date updateDate) {
        if (patchService.isPresent()) {
            return patchService.get().findAll()
                    .stream()
                    .filter(patch -> patch.getStartDate().before(updateDate))
                    .sorted((patch1, patch2) -> patch2.getStartDate().compareTo(patch1.getStartDate()))
                    .findFirst();
        } else {
            return Optional.empty();
        }
    }
}
