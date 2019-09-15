package ani.fraczek.integration;

import ani.fraczek.domain.dto.PostDTO;
import ani.fraczek.domain.entity.Post;
import ani.fraczek.domain.entity.Role;
import ani.fraczek.domain.entity.User;
import ani.fraczek.repository.PostRepository;
import ani.fraczek.repository.RoleRepository;
import ani.fraczek.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.validator.internal.util.Contracts;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class PostIntegrationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    RoleRepository roleRepository;

    @Before
    public void setUp(){
        User testUser1 = userRepository.save(createFakeUser("testWithoutPosts", "ROLE_USER"));
        User testUser2 = userRepository.save(createFakeUser("testWith2Posts", "ROLE_USER"));
        User testUser3 = userRepository.save(createFakeUser("testUserToCreatePost", "ROLE_USER"));
        postRepository.save(createFakePost(testUser2, "text of fakePost1", "fake1Title"));
        postRepository.save(createFakePost(testUser2, "text of fakePost2", "fake2Title"));
    }

    @After
    public void clean(){
        postRepository.deleteAllByPoster(userRepository.findUsersByLogin("testWith2Posts"));
        postRepository.deleteAllByPoster(userRepository.findUsersByLogin("testUserToCreatePost"));

        userRepository.deleteByLogin("testWithoutPosts");
        userRepository.deleteByLogin("testWith2Posts");
        userRepository.deleteByLogin("testUserToCreatePost");
    }


    @Test
    @WithMockUser(username = "testWith2Posts")
    public void test_getAllCurrentUserPosts_givenCurrentUser_With2Posts() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/posts/user")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    byte[] contentAsByteArray = result.getResponse().getContentAsByteArray();
                    final Set postDTOS = objectMapper.readValue(contentAsByteArray, Set.class);
                    assertEquals(2L, postDTOS.size());
                })
                .andReturn();
    }

    @Test
    @WithMockUser(username = "testWithoutPosts")
    public void test_getAllCurrentUserPosts_givenCurrentUser_WithoutAnyPosts() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/posts/user")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();
    }

    @Test
    @WithMockUser(roles = {"USER"})
    public void test_getAllPostsOfUser_givenUserWith2Posts() throws Exception {

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/users/testWith2Posts/posts")
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
    @WithMockUser(username = "testUserToCreatePost")
    public void test_addPostForCurrentUser() throws Exception {
        PostDTO fakePostDTO = createFakePostDTO("testUserToCreatePost");

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .post("/posts")
                .headers(getRequestHeaders())
                .content(objectMapper.writeValueAsBytes(fakePostDTO))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        PostDTO postDTO = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), PostDTO.class);
        assertEquals(fakePostDTO, postDTO);

        List<Post> allPosts = postRepository.findAllByPoster(userRepository.findUsersByLogin("testUserToCreatePost"));
        Assert.assertFalse(allPosts.isEmpty());
        assertEquals(1L, allPosts.size());
    }





    private Post createFakePost(User user, String text, String title){
        return Post.builder()
                .poster(user)
                .text(text)
                .title(title)
                .build();
    }

    private User createFakeUser(String login, String roleName){
        Role role_user = roleRepository.findByName(roleName);
        return User.builder()
                .login(login)
                .email(login + "@test.text")
                .roles(new HashSet<Role>(Collections.singletonList(role_user)))
                .encryptedPassword("a")
                .encryptedPassword("adfghjklertyui23456789")
                .firstName(login + "Name")
                .build();
    }

    private PostDTO createFakePostDTO(String login) {
        return PostDTO.builder()
                .text("test text user_" + login)
                .title("testTitle")
                .posterLogin(login)
                .build();
    }

    private HttpHeaders getRequestHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}