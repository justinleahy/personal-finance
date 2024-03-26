package com.justinleahy.personalfinance.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;
    private static final Logger log = LoggerFactory.getLogger(UserControllerTests.class);
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @Test
    void testPostUser() throws Exception {
        User newUser = new User("John", "Doe", "johndoe100", "hashedpassword");

        MvcResult postResult = mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUser.toJSON())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        User userPost = objectMapper.readValue(postResult.getResponse().getContentAsString(), User.class);
        log.info("POST User response: {}", userPost);

        assertEquals(newUser.getFirstName(), userPost.getFirstName());
        assertEquals(newUser.getLastName(), userPost.getLastName());
        assertEquals(newUser.getUserName(), userPost.getUserName());
        assertEquals(newUser.getPasswordHash(), userPost.getPasswordHash());
    }

    @Test
    void testGetUser() throws Exception {
        User newUser = new User("John", "Doe", "johndoe101", "hashedpassword");

        MvcResult postResult = mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUser.toJSON())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        User userPost = objectMapper.readValue(postResult.getResponse().getContentAsString(), User.class);
        log.info("POST user response: {}", userPost);

        MvcResult getResult = mockMvc.perform(get("/user/{id}", userPost.getId()))
                .andExpect(status().isOk()).andReturn();

        User userGet = objectMapper.readValue(getResult.getResponse().getContentAsString(), User.class);
        log.info("GET user response: {}", userGet);

        assertEquals(userPost.getFirstName(), userGet.getFirstName());
        assertEquals(userPost.getLastName(), userGet.getLastName());
        assertEquals(userPost.getUserName(), userGet.getUserName());
        assertEquals(userPost.getPasswordHash(), userGet.getPasswordHash());
    }

    @Test
    void testUpdateUser() throws Exception {
        User newUser = new User("John", "Doe", "johndoe102", "hashedpassword");

        MvcResult postResult = mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUser.toJSON())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        User userPost = objectMapper.readValue(postResult.getResponse().getContentAsString(), User.class);
        log.info("POST user response: {}", userPost);

        newUser.setFirstName("Updated");
        newUser.setLastName("User");

        MvcResult patchResult = mockMvc.perform(patch("/user/{id}", userPost.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUser.toJSON())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        User userPatch = objectMapper.readValue(patchResult.getResponse().getContentAsString(), User.class);
        log.info("PATCH user response: {}", userPatch);

        assertNotEquals(userPost.getFirstName(), userPatch.getFirstName());
        assertNotEquals(userPost.getLastName(), userPatch.getLastName());
        assertEquals(newUser.getUserName(), userPatch.getUserName());
        assertEquals(newUser.getPasswordHash(), userPatch.getPasswordHash());
    }

    @Test
    void testDeleteUser() throws Exception {
        User newUser = new User("John", "Doe", "johndoe103", "hashedpassword");

        MvcResult postResult = mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newUser.toJSON())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        User userPost = objectMapper.readValue(postResult.getResponse().getContentAsString(), User.class);
        log.info("POST user response: {}", userPost);

        mockMvc.perform(delete("/user/{id}", userPost.getId()))
                .andExpect(status().isNoContent());
    }
}
