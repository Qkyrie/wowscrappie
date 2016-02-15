package com.deswaef.wowscrappie.raids.domain;

import com.deswaef.wowscrappie.ui.weakauras.domain.BossFightWeakAura;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boss")
@DiscriminatorColumn(name = "boss_type")
public class Boss {

    @Id
    private Long id;
    @Column(name = "wcl_encounter_id")
    private Long warcraftlogsEncounterId;
    @Column(name = "name")
    private String name;
    @Column(name = "slug")
    private String slug;

    @OneToMany(mappedBy = "boss")
    private List<BossFightWeakAura> bossFightWeakAura = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Boss setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Boss setName(String name) {
        this.name = name;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public Boss setSlug(String slug) {
        this.slug = slug;
        return this;
    }

    public List<BossFightWeakAura> getBossFightWeakAura() {
        return bossFightWeakAura;
    }

    public void setBossFightWeakAura(List<BossFightWeakAura> bossFightWeakAura) {
        this.bossFightWeakAura = bossFightWeakAura;
    }

    public Long getWarcraftlogsEncounterId() {
        return warcraftlogsEncounterId;
    }

    public Boss setWarcraftlogsEncounterId(Long warcraftlogsEncounterId) {
        this.warcraftlogsEncounterId = warcraftlogsEncounterId;
        return this;
    }
}
