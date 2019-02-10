package com.vk;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public abstract class AbstractTest {
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webAppCtx;

    protected void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webAppCtx).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        return objMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> cls) throws IOException {
        ObjectMapper objMapper = new ObjectMapper();
        return objMapper.readValue(json, cls);

    }
}
