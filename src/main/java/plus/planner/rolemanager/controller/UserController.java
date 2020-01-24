package plus.planner.rolemanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
import plus.planner.rolemanager.constructor.JsonConstructor;
=======
>>>>>>> 54c98ab3328e7b1490cae078eae1668c9134466f
import plus.planner.rolemanager.model.User;
import plus.planner.rolemanager.repository.UserRepository;

import javax.persistence.Tuple;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepo;
    private final ObjectMapper objectMapper;
    private final JsonConstructor jsonConstructor;

    @Autowired
    public UserController(UserRepository userRepo, ObjectMapper objectMapper, JsonConstructor jsonConstructor) {
        this.userRepo = userRepo;
        this.objectMapper = objectMapper;
        this.jsonConstructor = jsonConstructor;
    }

    @GetMapping("/read/{projectid}")
    public String getUsers(@PathVariable("projectid") String projectid) throws JsonProcessingException {
        logger.info("getting users for projectid: " + projectid);
        final List<Tuple> users = userRepo.findUsersByRole(projectid);
        logger.info("constructing json");
        final String json = jsonConstructor.constructJson(users);
        logger.info("returning users");
        return json;
    }

    @PostMapping("/save")
    public void updateUser(@RequestBody String usr) throws IOException {
        final User user = objectMapper.readValue(usr, User.class);
        logger.info("saving user: " + user.getUserid());
        userRepo.save(user);
        logger.info("saved user");
    }

    @PostMapping("/delete")
    public void deleteUser(@RequestBody String userid) {
        logger.info("deleting user: " + userid);
        userRepo.deleteById(userid);
        logger.info("deleted user");
    }
}
