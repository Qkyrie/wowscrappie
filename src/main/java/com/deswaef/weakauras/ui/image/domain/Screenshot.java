package com.deswaef.weakauras.ui.image.domain;

import javax.persistence.*;

@Entity
@Table(name = "screenshot")
@DiscriminatorColumn(name="ss_type")
public class Screenshot {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "caption")
    private String caption;

    @Column(name = "reference")
    private String reference;

    public Long getId() {
        return id;
    }

    public Screenshot setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCaption() {
        return caption;
    }

    public Screenshot setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public String getReference() {
        return reference;
    }

    public Screenshot setReference(String reference) {
        this.reference = reference;
        return this;
    }
}
