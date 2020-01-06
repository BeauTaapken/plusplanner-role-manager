package plus.planner.rolemanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.planner.rolemanager.model.User;
import plus.planner.rolemanager.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userrepo;

    @Autowired
    public UserController(UserRepository userrepo) {
        this.userrepo = userrepo;
    }

    @RequestMapping("/create")
    public void createUser(@RequestBody User user) {
        logger.info("saving user: " + user.getUserid());
        userrepo.save(user);
        logger.info("saved user");
    }

    @RequestMapping("/read/{projectid}")
    public List<User> getUsers(@PathVariable("projectid") String projectid) {
        logger.info("getting users for projectid: " + projectid);
        final List<User> users = userrepo.findUsersByRole(projectid);
        logger.info("returning users");
        return users;
    }

    @RequestMapping("/update")
    public void updateUser(@RequestBody User user) {
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
