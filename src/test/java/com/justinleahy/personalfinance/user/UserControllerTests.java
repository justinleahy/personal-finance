package com.justinleahy.personalfinance.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPostUser() throws Exception {
        String userJson = "{\"firstName\": \"John\", \"lastName\" : \"Doe\", \"userName\" : \"johndoe100\", \"passwordHash\": \"hashedpassword\"}";

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void testGetUser() throws Exception {
        String userJson = "{\"firstName\": \"John\", \"lastName\" : \"Doe\", \"userName\" : \"johndoe101\", \"passwordHash\": \"hashedpassword\"}";

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mockMvc.perform(get("/user/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateUser() throws Exception {
        String userJson = "{\"firstName\": \"John\", \"lastName\" : \"Doe\", \"userName\" : \"johndoe102\", \"passwordHash\": \"hashedpassword\"}";

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        userJson = "{\"firstName\": \"Updated\", \"lastName\" : \"User\", \"userName\" : \"johndoe102\", \"passwordHash\": \"hashedpassword\"}";

        mockMvc.perform(patch("/user/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/user/{id}", 1))
                .andExpect(status().isNoContent());
    }
}
