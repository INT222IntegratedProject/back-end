package sit.integrated.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.integrated.project.models.Users;
import sit.integrated.project.repositories.UsersRepositories;
import java.util.List;

@RestController
@RequestMapping("/Users")
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersRepositories usersRepositories;


    @GetMapping("/GetUsers")
    public List<Users> ListAllUsers(){return usersRepositories.findAll(); }

    @GetMapping("/GetUsers/{id}")
    public Users getUserById(@PathVariable int id){ return  usersRepositories.findById(id).orElse(null); }

}
