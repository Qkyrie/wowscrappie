package com.deswaef.weakauras.battlenet.api.impl;

import com.deswaef.weakauras.battlenet.api.AbstractBattlenetOperations;
import com.deswaef.weakauras.battlenet.api.ItemOperations;
import com.deswaef.weakauras.battlenet.api.domain.WowItem;
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
