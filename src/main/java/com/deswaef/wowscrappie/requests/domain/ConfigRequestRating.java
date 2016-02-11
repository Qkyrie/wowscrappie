package com.deswaef.wowscrappie.requests.domain;

import com.deswaef.wowscrappie.ui.rating.domain.ConfigRating;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * User: Quinten
 * Date: 27-5-2015
 * Time: 21:20
 *
 * @author Quinten De Swaef
 */
@Entity
@DiscriminatorValue("REQUEST")
public class ConfigRequestRating extends ConfigRating {

    @OneToOne
    @JoinColumn(name = "configrequest_id")
    public ConfigRequest configRequest;

    public ConfigRequest getConfigRequest() {
        return configRequest;
    }

    public ConfigRequestRating setConfigRequest(ConfigRequest configRequest) {
        this.configRequest = configRequest;
        return this;
    }
}
