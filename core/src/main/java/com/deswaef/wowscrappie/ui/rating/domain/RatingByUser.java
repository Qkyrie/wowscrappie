package com.deswaef.wowscrappie.ui.rating.domain;

import com.deswaef.wowscrappie.usermanagement.domain.ScrappieUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rating_by_user")
public class RatingByUser {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "rating_id")
    private ConfigRating configRating;
    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    private Rating rating;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private ScrappieUser scrappieUser;

    public Long getId() {
        return id;
    }

    public RatingByUser setId(Long id) {
        this.id = id;
        return this;
    }

    public ConfigRating getConfigRating() {
        return configRating;
    }

    public RatingByUser setConfigRating(ConfigRating configRating) {
        this.configRating = configRating;
        return this;
    }

    public ScrappieUser getScrappieUser() {
        return scrappieUser;
    }

    public RatingByUser setScrappieUser(ScrappieUser scrappieUser) {
        this.scrappieUser = scrappieUser;
        return this;
    }

    public Rating getRating() {
        return rating;
    }

    public RatingByUser setRating(Rating rating) {
        this.rating = rating;
        return this;
    }
}
