package com.deswaef.wowscrappie.usermanagement.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;
    @Column(name = "authority")
    private String authority;

    public Role(final Long id, final String authority) {
        this.id = id;
        this.authority = authority;
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return authority;
    }

    public Role setAuthority(final String authority) {
        this.authority = authority;
        return this;
    }

    public Long getId() {
        return id;
    }

    public Role setId(final Long id) {
        this.id = id;
        return this;
    }
}
