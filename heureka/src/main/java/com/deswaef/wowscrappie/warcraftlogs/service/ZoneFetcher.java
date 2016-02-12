package com.deswaef.wowscrappie.warcraftlogs.service;

import com.deswaef.wowscrappie.warcraftlogs.WarcraftlogsAPIClient;
import com.deswaef.wowscrappie.warcraftlogs.WarcraftlogsAPIException;
import com.deswaef.wowscrappie.warcraftlogs.domain.zones.Zone;
import com.deswaef.wowscrappie.warcraftlogs.domain.zones.Zones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ZoneFetcher {

    public static final String API_ZONES = "/zones";

    @Autowired
    private WarcraftlogsAPIClient warcraftlogsAPIClient;

    public Collection<Zone> getZones() throws WarcraftlogsAPIException {
        return Zones.fromJson(warcraftlogsAPIClient.getFromAPI(API_ZONES));
    }

}
