package com.se418.project.servicediscovery;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceDiscoveryApplicationTests {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;
    private Logger logger = LoggerFactory.getLogger(ServiceDiscoveryApplication.class);

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void contextLoads() {
    }

    @Test
    public void testEurekaStatus() throws Exception {
        this.mockMvc.perform(
                get("/eureka")
        ).andExpect(status().isOk());
    }

}
