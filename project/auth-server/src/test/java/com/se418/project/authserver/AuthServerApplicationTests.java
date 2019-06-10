package com.se418.project.authserver;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Base64Utils;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = AuthServerApplication.class)
public class AuthServerApplicationTests {

    private Logger logger = LoggerFactory.getLogger(AuthServerApplicationTests.class);
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @Before
    public void init () {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).addFilter(filterChainProxy).build();
    }
    @Test
    public void contextLoads() {
    }

    @Test
    public void testRequestUnauthorized() throws Exception {
        String result = this.mockMvc.perform(get("/users/get"))
                .andExpect(status().isUnauthorized())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        logger.info(result);
    }

    @Test
    public void testAccessToken() throws Exception {
        String httpBasic = "summer855:123456";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic "+ Base64Utils.encodeToString(httpBasic.getBytes()));
        String result = this.mockMvc.perform(post("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .headers(headers)
                .param("grant_type", "password")
                .param("username", "summer855")
                .param("password", "123456")
                .param("scope", "all")
        ).andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        logger.info(result);
    }

    @Test
    public void testRequestWithToken() throws Exception{
        String httpBasic = "summer855:123456";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic "+ Base64Utils.encodeToString(httpBasic.getBytes()));
        String accessToken = this.mockMvc.perform(post("/oauth/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .headers(headers)
                .param("grant_type", "password")
                .param("username", "summer855")
                .param("password", "123456")
                .param("scope", "all")
        ).andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        String token = jsonParser.parseMap(accessToken).get("access_token").toString();
        logger.info("Token", token);
        logger.info(token);
        this.mockMvc.perform(get("/users/get")
                    .param("access_token", token)
                ).andExpect(status().isOk())
                 .andDo(print());
    }
}
