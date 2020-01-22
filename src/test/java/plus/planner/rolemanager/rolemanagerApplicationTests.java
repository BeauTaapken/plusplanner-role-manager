package plus.planner.rolemanager;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import plus.planner.rolemanager.model.Role;
import plus.planner.rolemanager.model.User;

import javax.ws.rs.core.MediaType;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@TestPropertySource(locations="classpath:application-test.properties")
@SpringBootTest
public class rolemanagerApplicationTests {

    private final Gson gson = new Gson();

    private MockMvc mockMvc;

    private final Role role = new Role("1", "1", "1", "test");

    private final User user = new User("1", "test", "testimg");

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

        mockMvc.perform(MockMvcRequestBuilders.post("/role/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(role))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));

        mockMvc.perform(MockMvcRequestBuilders.post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(user))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void createRoleCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/role/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(role))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void readRoleCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/role/read/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void updateRoleCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/role/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(role))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

    @Test
    public void deleteRoleCorrectly() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/role/delete/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200));
    }

//    @Test
//    public void createChatCorrectly() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/chat/create")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(gson.toJson(chat))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(200));
//    }
//
//    @Test
//    public void updateChatCorrectly() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/chat/update")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(gson.toJson(chat))
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(200));
//    }
//
//    @Test
//    public void deleteChatCorrectly() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/chat/delete")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("1")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().is(200));
//    }

}
