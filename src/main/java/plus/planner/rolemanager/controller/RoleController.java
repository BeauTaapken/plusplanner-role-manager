package plus.planner.rolemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plus.planner.rolemanager.model.Role;
import plus.planner.rolemanager.repository.RoleRepository;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final Logger logger = LoggerFactory.getLogger(RoleController.class);
    private final ObjectMapper objectMapper;
    private final RoleRepository roleRepo;

    @Autowired
    public RoleController(ObjectMapper objectMapper, RoleRepository roleRepo) {
        this.objectMapper = objectMapper;
        this.roleRepo = roleRepo;
    }

    @PostMapping(value = "/create")
    public void createRole(@RequestBody String rle) throws IOException {
        final Role role = objectMapper.readValue(rle, Role.class);
        logger.info("saving role: {}", role.getRoleid());
        roleRepo.save(role);
        logger.info("saved role");
    }

    @GetMapping(value = "/read/{userid}")
    public List<Role> readRoles(@PathVariable("userid") String userid) {
        logger.info("getting roles for userid: {}", userid);
        final List<Role> roles = roleRepo.findRolesByUserId(userid);
        logger.info("returning roles");
        return roles;
    }

    @PostMapping(value = "/update")
    public void updateRole(@RequestBody String rle) throws IOException {
        final Role role = objectMapper.readValue(rle, Role.class);
        logger.info("updating role: {}", role.getRoleid());
        roleRepo.save(role);
        logger.info("updating role");
    }

    @PostMapping("/delete")
    public void deleteRole(@RequestBody String roleid) {
        logger.info("deleting role: {}", roleid);
        roleRepo.deleteById(roleid);
        logger.info("deleting role");
    }
}
