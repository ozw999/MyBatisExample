package com.example.movie.auth.ctrl;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: Ou
 * @Date: 2020/4/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class AuthControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mvc;
    private MockHttpSession session;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//初始化MockMvc对象
        session = new MockHttpSession();
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findAuthById() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/user/findUserById")
                .accept(MediaType.APPLICATION_JSON)
                .session(session)
                .param("id", "1")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        List<String> list = new ArrayList<>();
        list.add("string");
        assertThat(list, hasItem("string"));
    }
}