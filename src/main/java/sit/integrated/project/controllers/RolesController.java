package sit.integrated.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sit.integrated.project.models.Roles;
import sit.integrated.project.repositories.RolesRepositories;

import java.util.List;

@RestController
@RequestMapping("/Roles")
@CrossOrigin
public class RolesController {
    @Autowired
    private RolesRepositories rolesRepositories;

    private Roles roles;



    @GetMapping("/GetRoles")
    public List<Roles> ListAllRoles(){return rolesRepositories.findAll(); }
}

