package com.deswaef.weakauras.ui.rating.domain;

import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rating")
@DiscriminatorColumn(name="rating_type")
public class ConfigRating implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToMany
    @JoinTable(name = "positive_rating_user",
            joinColumns = {   @JoinColumn(name = "rating_id", referencedColumnName = "id") } ,
            inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id")
                        })
    private List<ScrappieUser> upvoters = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "negative_rating_user",
            joinColumns = {   @JoinColumn(name = "rating_id", referencedColumnName = "id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "id")
            })
    private List<ScrappieUser> downvoters = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ScrappieUser> getUpvoters() {
        return upvoters;
    }

    public void setUpvoters(List<ScrappieUser> upvoters) {
        this.upvoters = upvoters;
    }

    public List<ScrappieUser> getDownvoters() {
        return downvoters;
    }

    public void setDownvoters(List<ScrappieUser> downvoters) {
        this.downvoters = downvoters;
    }
}
