package com.deswaef.wowscrappie.ui.mvc;

import com.deswaef.wowscrappie.infrastructure.service.OnRoleDependable;
import com.deswaef.wowscrappie.ui.rating.domain.ConfigRating;

import java.util.Optional;

public interface ShareController extends OnRoleDependable {
    public static final String PENDING_APPROVAL_URL = "shared/pending-approval";
    public static final String NOT_FOUND_URL = "shared/not-found";
    public static final String INDEX_URL = "shared/index";

    public static final String DEFAULT_RATING = "0 points";

    default String getRating(Optional<? extends ConfigRating> configRating) {
        if (configRating.isPresent()) {
            long effectiveRating = configRating.get().calculateEffectiveRating();
            return String.format("%s %s", effectiveRating, effectiveRating == 1 || effectiveRating == -1 ? "point" : "points");
        } else {
            return DEFAULT_RATING;
        }
    }
}
