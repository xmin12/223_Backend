package com.example.demo.domain.mylistentry;

import com.example.demo.domain.mylistentry.MyListEntry;
import com.example.demo.domain.mylistentry.MyListEntryRepository;
import com.example.demo.domain.mylistentry.MyListEntryService;
import com.example.demo.domain.mylistentry.MyListEntryServiceImpl;
import com.example.demo.domain.mylistentry.dto.MyListEntryDTO;
import com.example.demo.domain.mylistentry.dto.MyListEntryMapper;
import com.example.demo.domain.mylistentry.dto.MyListEntryMinimalDTO;
import com.example.demo.domain.mylistentry.dto.MyListEntryMinimalMapper;
import com.example.demo.domain.user.User;

import com.example.demo.domain.user.UserRepository;
import com.example.demo.domain.user.UserService;
import com.example.demo.domain.user.UserServiceImpl;
import com.example.demo.domain.user.dto.UserMinimalDTO;
import com.example.demo.domain.user.dto.UserMinimalMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MyListEntryController.class)
public class MyListEntryControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MyListEntryServiceImpl myListEntryServiceImpl;

    @MockBean
    private UserService userService;

    @MockBean
    private MyListEntryMinimalMapper myListEntryMinimalMapper;

    @MockBean
    private MyListEntryMapper myListEntryMapper;

    @MockBean
    private UserMinimalMapper userMinimalMapper;


    @Autowired
    private ObjectMapper objectMapper;

    User admin;

    String token;

    @BeforeEach
    void setUp() {
        token = "Bearer " + "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJiYTgwNGNiOS1mYTE0LTQyYTUtYWZhZi1iZTQ4ODc0MmZjNTQiLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5IjoiVVNFUl9ERUxFVEUifSx7ImF1dGhvcml0eSI6Ik1ZTElTVEVOVFJZX0NSRUFURSJ9LHsiYXV0aG9yaXR5IjoiVVNFUl9ERUxFVEUifSx7ImF1dGhvcml0eSI6Ik1ZTElTVEVOVFJZX1VQREFURSJ9LHsiYXV0aG9yaXR5IjoiTVlMSVNURU5UUllfREVMRVRFIn0seyJhdXRob3JpdHkiOiJVU0VSX01PRElGWSJ9LHsiYXV0aG9yaXR5IjoiTVlMSVNURU5UUllfUkVBRCJ9LHsiYXV0aG9yaXR5IjoiREVGQVVMVCJ9XSwiaWF0IjoxNzA5MDU5NDMyLCJleHAiOjE3MDkxNTk0MzIsImlzcyI6InVrMjIzIn0.CuTr0ZVPg-0OXCWbGwIH0a4qiTCMkGS3IOhFrJbnPgw";
    }

    @Test
    void createMyListEntry_WithValidInput_Success() throws Exception {
        // given

        MyListEntryDTO myListEntry1 = new MyListEntryDTO();

        myListEntry1.setId(UUID.randomUUID());
        myListEntry1.setTitle("Test of Create");
        myListEntry1.setText("This is a Test!");
        myListEntry1.setCreationDate(new Date());
        myListEntry1.setImportance(1);
        myListEntry1.setUser(userMinimalMapper.toDTO(userService.findById((UUID.fromString("ba804cb9-fa14-42a5-afaf-be488742fc54")))));


        User user = userService.findById((UUID.fromString("ba804cb9-fa14-42a5-afaf-be488742fc54")));
        System.out.println(user.getRoles());
        given(myListEntryServiceImpl.createMyListEntry(any(MyListEntry.class))).willAnswer((invocation) -> invocation.getArgument(0));

        // when

        ResultActions response = mockMvc.perform(post("/api/v1/mylistentries")
                .contentType(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, token)
                .content(objectMapper.writeValueAsString(myListEntryMapper.fromDTO(myListEntry1))));

        // then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(myListEntry1.getTitle())))
                .andExpect(jsonPath("$.text", is(myListEntry1.getText())))
                .andExpect(jsonPath("$.creationDate", is(myListEntry1.getCreationDate().toString())))
                .andExpect(jsonPath("$.importance", is(myListEntry1.getImportance())))
                .andExpect(jsonPath("$.user.id", is(myListEntry1.getUser().getId().toString())));
    }
}
