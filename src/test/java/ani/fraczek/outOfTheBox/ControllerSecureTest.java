package ani.fraczek.outOfTheBox;


import ani.fraczek.FooRepository;
import org.junit.After;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ControllerSecureTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    FooRepository fooRepository;

    @Before
    public void setUp(){}

    @After
    public void clean(){
        fooRepository.deleteAll();
    }

    @Test
    public void hello_shouldNotAuthorizeRequest() throws Exception {
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                            .get("/hello")
                            .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                        .andReturn();
    }

    @Test
    @WithMockUser(roles = "USER")
    public void hello_shouldAuthorizeRequestforUserOfRole_ROLE_USER() throws Exception {
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                            .get("/helloUser")
                            .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void hello_shouldAuthorizeRequest_forUserOfRole_ROLE_ADMIN() throws Exception {
        MvcResult mvcResult =
                mockMvc.perform(MockMvcRequestBuilders
                            .get("/helloAdmin")
                            .accept(MediaType.APPLICATION_JSON))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                        .andReturn();
    }



}
