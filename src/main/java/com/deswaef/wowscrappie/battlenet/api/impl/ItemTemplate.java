package com.deswaef.wowscrappie.battlenet.api.impl;

import com.deswaef.wowscrappie.battlenet.api.AbstractBattlenetOperations;
import com.deswaef.wowscrappie.battlenet.api.ItemOperations;
import com.deswaef.wowscrappie.battlenet.api.domain.WowItem;
import org.springframework.web.client.RestTemplate;

public class ItemTemplate extends AbstractBattlenetOperations implements ItemOperations {
    private RestTemplate restTemplate;

    public ItemTemplate(RestTemplate restTemplate, boolean isAuthorized) {
        super(isAuthorized);
        this.restTemplate = restTemplate;
    }

    @Override
    public WowItem item(long itemId) {
        return null;
    }
}
