package plus.planner.rolemanager.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.planner.rolemanager.model.Role;
import plus.planner.rolemanager.repository.RoleRepository;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleRepository roleRepo;
    private ObjectMapper mapper;

    public RoleController() {
        this.mapper = new ObjectMapper();
    }

    @RequestMapping("/create")
    public void createRole(@RequestBody String role){
        try {
            roleRepo.save(mapper.readValue(role, Role.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/read/{userid}")
    public List<Role> readRoles(@PathVariable("userid") String userid){
        return roleRepo.findRolesByUserId(userid);
    }

    @RequestMapping("/update/{role}")
    public void updateRole(@PathVariable("role") Role role){
        roleRepo.save(role);
    }

    @RequestMapping("/delete/{roleid}")
    public void deleteRole(@PathVariable("roleid") Long roleid){
        roleRepo.deleteById(roleid);
    }
}
