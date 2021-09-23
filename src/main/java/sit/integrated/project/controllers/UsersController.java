package sit.integrated.project.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.integrated.project.models.Users;
import sit.integrated.project.repositories.UsersRepositories;
import java.util.List;

@RestController
@RequestMapping("/Users")
@CrossOrigin
public class UsersController {
    private UsersRepositories usersRepositories;
    private Users users;

    @GetMapping("/GetUsers")
    public List<Users> ListAllUsers(){return usersRepositories.findAll(); }
}
