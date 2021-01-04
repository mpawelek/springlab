package com.github.mpawelek;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("default")
@AutoConfigureMockMvc
public class JpaIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        MvcResult reply = mockMvc.perform(get("/test")).andReturn();
        System.out.println(reply.getResponse().getContentAsString());
    }
}

