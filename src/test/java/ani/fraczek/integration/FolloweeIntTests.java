package ani.fraczek.integration;


import ani.fraczek.domain.entity.FollowerFollowee;
import ani.fraczek.domain.entity.User;
import ani.fraczek.repository.FollowerRepository;
import ani.fraczek.repository.UserRepository;
import ani.fraczek.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Set;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class FolloweeIntTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    FollowerRepository followerRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() {
        createAndSaveUser("CurrentUser");
        createAndSaveUser("Followee1");
        createAndSaveUser("Followee2");
    }

    @After
    public void celan() {
        followerRepository.deleteAll();
        userRepository.deleteByLogin("CurrentUser");
        userRepository.deleteByLogin("Followee1");
        userRepository.deleteByLogin("Followee2");
    }


    @Test
    @WithMockUser(username = "CurrentUser")
    public void test_addFolloweeForCurrentUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .patch("/users/current/followee/Followee1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
        mockMvc.perform(MockMvcRequestBuilders
                .patch("/users/current/followee/Followee2"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        User currentUser = userRepository.findUserByLogin("CurrentUser");
        Set<String> currentUserFollowees = followerRepository.findUserFolloweesLoginsByUserId(currentUser.getId());
        Assert.assertThat(currentUserFollowees, CoreMatchers.hasItems("Followee1", "Followee2"));
    }

//    TODO delete followee

    @Test
    @WithMockUser(username = "CurrentUser")
    public void test_getCurrentUserFollowees_givenUserWIthoutFollowees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/current/followees")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @WithMockUser(username = "CurrentUser")
    public void test_getCurrentUserFollowees() throws Exception {
        Long followe1Id = userService.getUserByLogin("Followee1").getId();
        Long followe2Id = userService.getUserByLogin("Followee2").getId();
        followerRepository.save(new FollowerFollowee(userService.getCurrentUserId(), followe1Id));
        followerRepository.save(new FollowerFollowee(userService.getCurrentUserId(), followe2Id));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/current/followees")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    Set<String> followees = objectMapper.readValue(result.getResponse().getContentAsString(), Set.class);
                    Assert.assertThat(followees, CoreMatchers.hasItems("Followee1", "Followee1"));
                });
    }


    private User createAndSaveUser(String userLogin) {
        return userRepository.save(User.builder()
                .login(userLogin)
                .email(userLogin + "@t.t")
                .encryptedPassword("b2xasdfghjkl12345678")
                .build());
    }

}
