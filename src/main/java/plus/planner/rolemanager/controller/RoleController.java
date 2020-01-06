package plus.planner.rolemanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plus.planner.rolemanager.model.Role;
import plus.planner.rolemanager.repository.RoleRepository;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final Logger logger = LoggerFactory.getLogger(RoleController.class);
    private final RoleRepository roleRepo;

    @Autowired
    public RoleController(RoleRepository roleRepo) {
        this.roleRepo = roleRepo;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void createRole(@RequestBody Role role) {
        logger.info("saving role: " + role.getRoleid());
        roleRepo.save(role);
        logger.info("saved role");
    }

    @RequestMapping(value = "/read/{userid}", method = RequestMethod.GET)
    public List<Role> readRoles(@PathVariable("userid") String userid) {
        logger.info("getting roles for userid: " + userid);
        final List<Role> roles = roleRepo.findRolesByUserId(userid);
        logger.info("returning roles");
        return roles;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateRole(@RequestBody Role role) {
        logger.info("updating role: " + role.getRoleid());
        roleRepo.save(role);
        logger.info("updating role");
    }

    @RequestMapping("/delete")
    public void deleteRole(@PathVariable("roleid") String roleid) {
        logger.info("deleting role: " + roleid);
        roleRepo.deleteById(roleid);
        logger.info("deleting role");
    }
}
