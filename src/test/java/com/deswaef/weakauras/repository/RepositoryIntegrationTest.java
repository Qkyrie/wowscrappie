package com.deswaef.weakauras.repository;

import com.deswaef.weakauras.WeakAuras;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WeakAuras.class)
@ActiveProfiles("integrationtest")
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class RepositoryIntegrationTest {

    @Test
    public void emptyTest() {
        //just an empty statement
    }

}
