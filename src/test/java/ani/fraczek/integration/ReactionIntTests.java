package ani.fraczek.integration;


import ani.fraczek.domain.dto.ReactionDTO;
import ani.fraczek.domain.entity.Post;
import ani.fraczek.domain.entity.Role;
import ani.fraczek.domain.entity.User;
import ani.fraczek.repository.PostRepository;
import ani.fraczek.repository.RoleRepository;
import ani.fraczek.repository.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.HashSet;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
@RunWith(SpringRunner.class)
public class ReactionIntTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;


    @After
    public void clean(){
        postRepository.deleteAllByPoster(userRepository.findUserByLogin("user123"));
        userRepository.deleteByLogin("user123");
    }

    @Test
    public void testAddReaction(){
        User user = createAndSaveUser("user123", "ROLE_USER");
        Post post = createAndSavePost(user, "textttTest", "titleeeTest");

        ReactionDTO.builder()
                .authorLogin(user.getLogin())
                .content("TEST_CONTENT")
                .title("TEST_TITLE")
                .postId(post.getId())
                .reactionType("post_reaction_activity")
                .build();
    }




    private User createAndSaveUser(String login, String roleName) {
        return userRepository.save(createFakeUser(login, roleName));
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


    private Post createAndSavePost(User user, String text, String title){
        return postRepository.save(createFakePost(user, text, title));
    }

    private Post createFakePost(User user, String text, String title){
        return Post.builder()
                .poster(user)
                .text(text)
                .title(title)
                .build();
    }

}
