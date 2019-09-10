package ani.fraczek.integration;

import ani.fraczek.domain.dto.PostDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.internal.util.Contracts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Set;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class PostIntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    @WithMockUser(roles = {"USER"}, username = "mockUser1")
    public void test_getAllCurrentUserPosts() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/posts/user")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    byte[] contentAsByteArray = result.getResponse().getContentAsByteArray();
                    final Set postDTOS = objectMapper.readValue(contentAsByteArray, Set.class);
                    Contracts.assertNotEmpty(postDTOS, "Posts should not be empty.");
                })
                .andReturn();
    }

    @Test
    @WithMockUser(roles = {"USER"}, username = "mockUser1")
    public void test_getAllPostsOfUser() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/users/1112/posts")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    byte[] contentAsByteArray = result.getResponse().getContentAsByteArray();
                    final Set postDTOS = objectMapper.readValue(contentAsByteArray, Set.class);
                    Contracts.assertNotEmpty(postDTOS, "Posts should not be empty.");
                })
                .andReturn();
    }

    @Test
    @WithMockUser(roles = {"USER"}, username = "mockUser1")
    public void test_addPost() throws Exception {

        PostDTO postDTO = PostDTO.builder()
                .text("test text")
                .title("testTitle")
                .posterId(1112L)
                .build();

        byte[] payload = objectMapper.writeValueAsBytes(postDTO);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/posts")
                .content(payload)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

    }

}