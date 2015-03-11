package com.deswaef.weakauras.ui.mvc;

import com.deswaef.weakauras.infrastructure.service.OnRoleDependable;
import com.deswaef.weakauras.ui.rating.domain.ConfigRating;

import java.util.Optional;

public interface ShareController extends OnRoleDependable {
    public static final String PENDING_APPROVAL_URL = "shared/pending-approval";
    public static final String NOT_FOUND_URL = "shared/not-found";
    public static final String INDEX_URL = "shared/index";

    public static final String DEFAULT_RATING = "0%";
    public static final String MAX_RATING = "100%";


    default String getRating(Optional<? extends ConfigRating> configRating) {
        if (configRating.isPresent()) {
            ConfigRating actualRating = configRating.get();
            int downvoters = actualRating.getDownvoters().size();
            int upvoters = actualRating.getUpvoters().size();
            if (upvoters == 0) {
                return DEFAULT_RATING;
            } else if (downvoters == 0 && upvoters > 0) {
                return MAX_RATING;
            } else {
                int result = upvoters / (upvoters + downvoters);
                return result + "%";
            }
        } else {
            return DEFAULT_RATING;
        }
    }
}
