package com.deswaef.weakauras.usermanagement.domain;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "scrappie_user")
public class ScrappieUser implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "facebook_id")
    private String facebookId;

    @Column(name = "twitter_id")
    private String twitterId;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "account_non_expired", nullable = false,
            columnDefinition = "boolean default true")
    private boolean accountNonExpired = true;

    @Column(name = "account_non_locked", nullable = false,
            columnDefinition = "boolean default true")
    private boolean accountNonLocked = true;

    @Column(name = "credentials_non_expired", nullable = false,
            columnDefinition = "boolean default true")
    private boolean credentialsNonExpired = true;

    @Column(name = "enabled", nullable = false,
            columnDefinition = "boolean default true")
    private boolean enabled = true;

    @Column(name = "generated_username", nullable = false,
        columnDefinition = "boolean default true")
    private boolean generatedUsername;

    @Column(name = "email")
    private String email;

    @Column(name = "activationcode", nullable = true)
    private String activationCode;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = {
                    @JoinColumn
                            (
                                    name = "user_id",
                                    referencedColumnName = "id"
                            )
            },
            inverseJoinColumns =
                    {
                            @JoinColumn
                                    (
                                            name = "role_id",
                                            referencedColumnName = "id"
                                    )
                    }
    )
    private Set<Role> authorities = new HashSet<Role>();

    public Long getId() {
        return id;
    }

    public ScrappieUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public ScrappieUser setUsername(String username) {
        this.username = username;
        return this;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public ScrappieUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public ScrappieUser setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
        return this;
    }

    public ScrappieUser setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
        return this;
    }

    public ScrappieUser setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
        return this;
    }

    public ScrappieUser setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public ScrappieUser setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public ScrappieUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public ScrappieUser setActivationCode(String activationCode) {
        this.activationCode = activationCode;
        return this;
    }

    public ScrappieUser setAccessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public ScrappieUser setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public String getTwitterId() {
        return twitterId;
    }

    public ScrappieUser setFacebookId(String facebookId) {
        this.facebookId = facebookId;
        return this;

    }

    public boolean isGeneratedUsername() {
        return generatedUsername;
    }

    public ScrappieUser setGeneratedUsername(boolean generatedUsername) {
        this.generatedUsername = generatedUsername;
        return this;
    }

    public void setTwitterId(String twitterId) {
        this.twitterId = twitterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScrappieUser that = (ScrappieUser) o;

        if (!id.equals(that.id)) return false;
        if (!username.equals(that.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + username.hashCode();
        return result;
    }
}
