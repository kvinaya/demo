package com.vk;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserControllerTest extends AbstractTest {
    String uri = "/users";
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void createUser() throws Exception{
        User user = new User("Adam", "Lang", "Addam.lang@test.com");
        String inputJson = super.mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        User createdUser = super.mapFromJson(content, User.class);
        assertEquals(createdUser.getGivenName(), user.getGivenName());
        assertEquals(createdUser.getFamilyName(), user.getFamilyName());
        assertEquals(createdUser.getEmail(), user.getEmail());

    }

    @Test
    public void createUser_GivenName_Invalid() throws Exception{
        User user = new User("<Script>alert('hello')>/script>", "Lang", "Addam.lang@test.com");
        String inputJson = super.mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
    }

    @Test
    public void createUser_FamilyName_Invalid() throws Exception{
        User user = new User("Adam", "<script></script>", "Adam.lang@test.com");
        String inputJson = super.mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
    }


    @Test
    public void createUser_Email_Invalid() throws Exception{
        User user = new User("Adam", "Gregor", "Adam.lang.com");
        String inputJson = super.mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(400, status);
    }

    @Test
    public void GetUser_User_Doesnot_Exist() throws Exception{
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + "/5")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "The user with id 5 does not exist");

    }


    @Test
    public void updateUser() throws Exception {
        User user = new User("Carl", "Gregor", "carl.gregor@test.com");
        String inputJson = super.mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri + "/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        User updatedUser = super.mapFromJson(content, User.class);
        assertEquals(updatedUser.getFamilyName(), user.getFamilyName());
        assertEquals(updatedUser.getGivenName(), user.getGivenName());
        assertEquals(updatedUser.getEmail(), user.getEmail());
        assertEquals(updatedUser.getId(), (long) 1);

    }

    @Test
    public void getAllUsers() throws Exception{
        //String uri = "/users";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
        .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        User[] users = super.mapFromJson(content, User[].class);
        assertTrue(users.length > 0);
    }

    @Test
    public void getUser() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri + "/1")
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        User user = super.mapFromJson(content, User.class);
        assertTrue(user != null);
        assertEquals(user.getId(), (long) 1);

    }

    @Test
    public void deleteUser() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri + "/1")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "");

    }

    @Test
    public void deleteUser_User_Doesnot_Exist() throws Exception {
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri + "/6")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "The user with id 6 does not exist");

    }
}
