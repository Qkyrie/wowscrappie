package com.deswaef.weakauras.contribution.controller.dto;

import com.deswaef.weakauras.classes.domain.Spec;

public class SelectSpecDto {
    private Long id;
    private String name;
    private String slug;

    public static SelectSpecDto fromSpec(Spec spec) {
        return new SelectSpecDto()
                .setId(spec.getId())
                .setName(spec.getName())
                .setSlug(spec.getSlug());
    }

    public Long getId() {
        return id;
    }

    public SelectSpecDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SelectSpecDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getSlug() {
        return slug;
    }

    public SelectSpecDto setSlug(String slug) {
        this.slug = slug;
        return this;
    }
}
