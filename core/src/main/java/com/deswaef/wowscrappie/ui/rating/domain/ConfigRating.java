package com.deswaef.wowscrappie.ui.rating.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;

@Entity
@Table(name = "rating")
@DiscriminatorColumn(name = "rating_type")
public class ConfigRating implements Serializable {

    public static final Predicate<RatingByUser> NEGATIVE_ONLY = x -> x.getRating().equals(Rating.NEGATIVE);
    public static final Predicate<RatingByUser> POSITIVE_ONLY = x -> x.getRating().equals(Rating.POSITIVE);
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "configRating", cascade = CascadeType.REMOVE)
    private List<RatingByUser> ratings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<RatingByUser> getRatings() {
        return ratings;
    }

    public ConfigRating setRatings(List<RatingByUser> ratings) {
        this.ratings = ratings;
        return this;
    }

    @Transient
    public long calculateEffectiveRating() {
        List<RatingByUser> ratings = getRatings();
        long downvoters = ratings
                .stream()
                .filter(NEGATIVE_ONLY)
                .count();
        long upvoters = ratings
                .stream()
                .filter(POSITIVE_ONLY)
                .count();
        return upvoters - downvoters;
    }
}
