package plus.planner.rolemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plus.planner.rolemanager.model.User;
import plus.planner.rolemanager.repository.UserRepository;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userrepo;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserController(UserRepository userrepo, ObjectMapper objectMapper) {
        this.userrepo = userrepo;
        this.objectMapper = objectMapper;
    }

    @RequestMapping("/read/{projectid}")
    public List<User> getUsers(@PathVariable("projectid") String projectid) {
        logger.info("getting users for projectid: " + projectid);
        final List<User> users = userrepo.findUsersByRole(projectid);
        logger.info("returning users");
        return users;
    }

    @PostMapping("/save")
    public void updateUser(@RequestBody String usr) throws IOException {
        final User user = objectMapper.readValue(usr, User.class);
        logger.info("saving user: " + user.getUserid());
        userrepo.save(user);
        logger.info("saved user");
    }

    @RequestMapping("/delete")
    public void deleteUser(@RequestBody String userid) {
        logger.info("deleting user: " + userid);
        userrepo.deleteById(userid);
        logger.info("deleted user");
    }
}
