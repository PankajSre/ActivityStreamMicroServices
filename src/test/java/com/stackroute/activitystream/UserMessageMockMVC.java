package com.stackroute.activitystream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.activitystream.controller.UserMessageRestController;
import com.stackroute.activitystream.dao.UserMessageDAO;
import com.stackroute.activitystream.model.Message;
import com.stackroute.activitystream.model.UserMessage;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserMessageMockMVC {

    

    private MockMvc mockMvc;
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),Charset.forName("utf8"));      

    @Mock
    private UserMessageDAO userMessageDAO;

    @InjectMocks
    private UserMessageRestController userMessageRestController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userMessageRestController)
                .addFilters(new CORSFilter())
                .build();
    }

    // =========================================== Get All User Messages ==========================================
    @Ignore
    @Test
    public void test_get_all_success() throws Exception {
    	List<UserMessage> messages = Arrays.asList(
				new UserMessage(1, "This is Good", "ravi@gmail.com", new Date(), 230, 10000, "Text", "jagan@gmail.com",
						"general"),
				new UserMessage(2, "Hello dear", "krishna@gmail.com", new Date(), 521, 10000, "Text", "ravi@gmail.com",
						"general"));

        when(userMessageDAO.getMyMessages(messages.get(0).getReceiverEmailId())).thenReturn(messages);

        mockMvc.perform(get("/api/userMessage/getAllMessagesByCircleName/general"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                
                .andExpect(jsonPath("$[0].messageId", is(1)))
                 .andExpect(jsonPath("$[1].messageId", is(2)));

        verify(userMessageDAO, times(1)).getMyMessages(messages.get(0).getReceiverEmailId());
        verifyNoMoreInteractions(userMessageDAO);
    }

    // =========================================== Get User By ID =========================================

    @Test
    public void test_get_user_message_by_id_success() throws Exception {
    	 UserMessage message = new UserMessage(1, "Hi", "advik@gmail.com", new Date(), 30, 30000, "Text", "deepak@gmail.com", "general");
         

        when(userMessageDAO.getMessageByMessageId(message.getMessageId())).thenReturn(message);

        mockMvc.perform(get("/api/userMessage/userMessageById/{messageId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.messageId", is(1)))
                .andExpect(jsonPath("$.messageText", is("Hi")));
    }
    /*
    @Test
    public void test_get_by_id_fail_404_not_found() throws Exception {

        when(userService.findById(1)).thenReturn(null);

        mockMvc.perform(get("/users/{id}", 1))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).findById(1);
        verifyNoMoreInteractions(userService);
    }
    */

    // =========================================== Create New User ========================================
   @Ignore
    @Test
    public void test_create_user_success() throws Exception {
       UserMessage message = new UserMessage(1, "Hi", "advik@gmail.com", new Date(), 30, 30000, "Text", "deepak@gmail.com", "general");
     
        mockMvc.perform(
                post("/api/userMessage/sendUserMessage")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(message)))
                .andExpect(status().isNoContent());
        verify(userMessageDAO, times(1)).sendMessage(message);
        verifyNoMoreInteractions(userMessageDAO);
    }
   @Ignore
    @Test
    public void test_create_user_fail_409_conflict() throws Exception {
    	 UserMessage message = new UserMessage(1, "Hi", "advik@gmail.com", new Date(), 30, 30000, "Text", "deepak@gmail.com", "general");
         
     
        mockMvc.perform(
                post("/api/userMessage/sendUserMessage")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(asJsonString(message)))
                .andExpect(status().isNoContent());
        verifyNoMoreInteractions(userMessageDAO);
    }

    // =========================================== Update Existing Messages ===================================
   
   
    @Test
    public void test_delete_user_message_success() throws Exception {
    	UserMessage message = new UserMessage(1, "Hi", "advik@gmail.com", new Date(), 30, 30000, "Text", "advik@gmail.com", "general");
       
       mockMvc.perform(
                delete("/api/userMessage/deleteUserMessage",message))
                .andExpect(status().isMethodNotAllowed());
        verifyNoMoreInteractions(userMessageDAO);
    }

    @Test(expected=AssertionError.class)
    public void test_delete_user_fail_404_not_found() throws Exception {
    	UserMessage message = new UserMessage(1, "Hi", "advik@gmail.com", new Date(), 30, 30000, "Text", "advik@gmail.com", "general");
        
        mockMvc.perform(
                 delete("/api/userMessage/deleteUserMessage",message))
                 .andExpect(status().isOk());
         verifyNoMoreInteractions(userMessageDAO);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}