package plus.planner.rolemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plus.planner.rolemanager.model.Role;
import plus.planner.rolemanager.repository.RoleRepository;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleRepository roleRepo;

    @RequestMapping("/create/{role}")
    public void createRole(@PathVariable("role") Role role){
        roleRepo.save(role);
    }

    @RequestMapping("/read/{userid}")
    public List<Role> readRoles(@PathVariable("userid") Long userid){
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
