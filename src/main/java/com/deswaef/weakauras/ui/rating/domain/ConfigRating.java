package com.deswaef.weakauras.ui.rating.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "rating")
@DiscriminatorColumn(name="rating_type")
public class ConfigRating implements Serializable {

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
}
