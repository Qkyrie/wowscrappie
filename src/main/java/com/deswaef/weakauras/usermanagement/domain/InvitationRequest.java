package com.deswaef.weakauras.usermanagement.domain;

import javax.persistence.*;

@Entity
@Table(name = "invitation_request")
public class InvitationRequest {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "email")
    private String email;
    @Column(name = "reason")
    private String reason;

    public Long getId() {
        return id;
    }

    public InvitationRequest setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public InvitationRequest setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public InvitationRequest setReason(String reason) {
        this.reason = reason;
        return this;
    }
}
