package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.domain.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", authorities = {"ADMIN"})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest
{
    private int id = 0;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    public void init() {
        User user = new User();
        user.setUsername("user2");
        user.setFullname("user22");
        user.setPassword("password1B$");
        user.setRole("USER");
        userRepository.save(user);
        for (User user1 : userRepository.findAll()) {
            if (user1.getUsername().equals("user2")) {
                id = user1.getId();
                break;
            }
        }
    }

    @AfterAll
    public void deleteUser() {
        User user = userRepository.findByUsername("user2").get();
        userRepository.delete(user);
    }

    @Test
    public void findAll() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk());
    }

    @Test
    public void addUser() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(status().isOk());
    }

    @Test
    public void validateUser() throws Exception {
        mockMvc.perform(post("/user/validate")
                        .param("username", "user2")
                        .param("fullname", "user22")
                        .param("password", "password1B$")
                        .param("role", "USER"))
                .andExpect(redirectedUrl("/user/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void updateById() throws Exception {
        mockMvc.perform(get("/user/update/{id}", id)
                        .accept(MediaType.ALL))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUser() throws Exception {
        mockMvc.perform(post("/user/update/{id}", id)
                        .param("username", "user3")
                        .param("fullname", "user33")
                        .param("password", "password1B$")
                        .param("role", "USER"))
                .andExpect(redirectedUrl("/user/list"))
                .andExpect(status().isFound())
                .andReturn();
    }

    @Test
    public void deleteById() throws Exception {
        mockMvc.perform(get("/user/delete/{id}", id))
                .andDo(print())
                .andExpect(redirectedUrl("/user/list"))
                .andExpect(status().isFound())
                .andReturn();
    }
}