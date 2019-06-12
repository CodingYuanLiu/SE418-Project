package com.parser;


import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import antlr.debug.ParserController;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonArray;
import com.parser.service.TongquActService;
import net.bytebuddy.asm.Advice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest(classes = ParserApplication.class)
public class ParserApplicationTests {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private TongquActService tongquActService;



	@Before
	public void init() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void contextLoads() {
		assertEquals(1,1);
	}

	@Test
	public void feignInterfaceTest () {
		String res = tongquActService.getAllActs();
		assertNotNull(res);
	}

	@Test
	public void getActTest() throws Exception{
		this.mockMvc.perform(
				get("/act/parse")
		).andExpect(status().isOk());
	}
}
