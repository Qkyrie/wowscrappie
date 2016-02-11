package com.deswaef.wowscrappie.configuration.datacorrection.realms;

import com.deswaef.wowscrappie.configuration.datacorrection.DataCorrection;
import com.deswaef.wowscrappie.configuration.datacorrection.realms.json.RealmJsonObject;
import com.deswaef.wowscrappie.configuration.datacorrection.realms.json.RealmsJsonObject;
import com.deswaef.wowscrappie.realm.domain.Locality;
import com.deswaef.wowscrappie.realm.domain.Realm;
import com.deswaef.wowscrappie.realm.service.RealmService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

@Component
@ConditionalOnProperty("com.deswaef.scrappie.realms.autoimport")
public class RealmDataBootstrap  implements DataCorrection {

    public static final long NO_REALMS_YET = 0L;
    private Log logger = LogFactory.getLog(RealmDataBootstrap.class);

    @Value("${com.deswaef.scrappie.scriptstore}")
    private String scriptStore;

    private static final String realmsEUFile = "eu-realms.json";
    private static final String realmsUSFile = "us-realms.json";

    @Autowired
    private RealmService realmService;
    @Autowired
    private JsonToRealmMapper jsonToRealmMapper;

    @Override
    public void run() {
        try {
            if(realmService.count().equals(NO_REALMS_YET)) {
                importRealms(getFileAsString(realmsEUFile), Locality.EU);
                importRealms(getFileAsString(realmsUSFile), Locality.US);
            }
        } catch (MalformedURLException e) {
            logger.info("unable to create URL for realm.json file");
        }
    }

    private void importRealms(File usRealmsFilename, Locality locality) {
        try {
            RealmsJsonObject mappedRealms = jsonToRealmMapper.map(usRealmsFilename);
            List<Realm> resultedRealms = mappedRealms
                    .getRealms()
                    .stream()
                    .map(jsonRealmToRealm(locality))
                    .collect(toList());
            realmService.save(resultedRealms);
        } catch (JsonMappingException e) {
            logger.error("unable to import realms");
        }
    }

    private Function<RealmJsonObject, Realm> jsonRealmToRealm(Locality locality) {
        return realm -> new Realm()
                .setName(realm.getName())
                .setLocality(locality)
                .setSlug(realm.getSlug());
    }

    private File getFileAsString(String filename) throws MalformedURLException {
        return new File(scriptStore + File.separator + filename);
    }
}
