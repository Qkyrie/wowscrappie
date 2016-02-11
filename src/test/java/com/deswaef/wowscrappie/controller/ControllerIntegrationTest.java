package com.deswaef.wowscrappie.controller;

import com.deswaef.WoWScrappieInitializer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * User: Quinten
 * Date: 2-8-2014
 * Time: 01:39
 *
 * @author Quinten De Swaef
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WoWScrappieInitializer.class)
@ActiveProfiles("integrationtest")
@IntegrationTest("server.port:0")
@WebAppConfiguration
public class ControllerIntegrationTest {

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void setupEnvironment() {
        //just setup the environment to see if everything runs
    }
}

