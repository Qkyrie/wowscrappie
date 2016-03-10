package com.deswaef.wowscrappie.importingservice.wowhead.client;

import com.deswaef.wowscrappie.importingservice.wowhead.client.dto.WowheadItemDto;
import com.deswaef.wowscrappie.importingservice.wowhead.client.resource.WowheadResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemFetchingService {

    @Autowired
    private WowheadResource apiClient;

    public Optional<WowheadItemDto> getItem(Long itemId) {
        String builder = String.format("/item=%s&xml", String.valueOf(itemId));
        return WowheadItemDto.fromXml(apiClient.getFromWowheadAPI(builder));
    }

}