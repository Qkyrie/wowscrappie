package com.deswaef.wowscrappie.repository;

import com.deswaef.WowScrappieTestInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WowScrappieTestInitializer.class)
@ActiveProfiles("integrationtest")
@IntegrationTest("server.port:0")
@Transactional(readOnly = true)
public class RepositoryIntegrationTest {

    @Test
    public void emptyTest() {
        //just an empty statement
    }

}
