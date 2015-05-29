package com.deswaef.weakauras.requests.domain;

import com.deswaef.weakauras.usermanagement.domain.ScrappieUser;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * User: Quinten
 * Date: 27-5-2015
 * Time: 21:22
 *
 * @author Quinten De Swaef
 */

@Entity
@Table(name = "configrequest_response")
public class ConfigRequestResponse {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "response")
    @Lob
    private String response;

    @ManyToOne
    @JoinColumn(name = "configrequest_id")
    private ConfigRequest configRequest;

    @ManyToOne
    @JoinColumn(name = "in_response_to")
    private ConfigRequestResponse inResponseTo;

    @ManyToOne
    @JoinColumn(name = "responder_id")
    private ScrappieUser responder;

    @Column(name = "original_postdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date originalPostDate;

    @Column(name = "lastEditDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastEditDate;

    @Column(name = "deleted")
    private boolean deleted = false;

    public Long getId() {
        return id;
    }

    public ConfigRequestResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public String getResponse() {
        return response;
    }

    public ConfigRequestResponse setResponse(String question) {
        this.response = question;
        return this;
    }

    public ScrappieUser getResponder() {
        return responder;
    }

    public ConfigRequestResponse setResponder(ScrappieUser responder) {
        this.responder = responder;
        return this;
    }

    public Date getOriginalPostDate() {
        return originalPostDate;
    }

    public ConfigRequestResponse setOriginalPostDate(Date originalPostDate) {
        this.originalPostDate = originalPostDate;
        return this;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public ConfigRequestResponse setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public ConfigRequestResponse setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public ConfigRequest getConfigRequest() {
        return configRequest;
    }

    public ConfigRequestResponse setConfigRequest(ConfigRequest configRequest) {
        this.configRequest = configRequest;
        return this;
    }

    public ConfigRequestResponse getInResponseTo() {
        return inResponseTo;
    }

    public ConfigRequestResponse setInResponseTo(ConfigRequestResponse inResponseTo) {
        this.inResponseTo = inResponseTo;
        return this;
    }
}
