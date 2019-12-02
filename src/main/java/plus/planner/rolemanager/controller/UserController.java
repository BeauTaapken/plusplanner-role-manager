package plus.planner.rolemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.planner.rolemanager.model.User;
import plus.planner.rolemanager.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userrepo;

    @RequestMapping("/create/{user}")
    public void createUser(@PathVariable("user")User user){
        userrepo.save(user);
    }

    @RequestMapping("/read/{projectid}")
    public List<User> getUsers(@PathVariable("projectid") Long projectid){
        return userrepo.findUsersByRole(projectid);
    }

    @RequestMapping("/update/{user}")
    public void updateUser(@PathVariable("user") User user){
        userrepo.save(user);
    }

    @RequestMapping("/delete/{userid}")
    public void deleteUser(@PathVariable("userid") Long userid){
        userrepo.deleteById(userid);
    }
}
