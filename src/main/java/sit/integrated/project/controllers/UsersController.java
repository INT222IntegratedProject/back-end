package sit.integrated.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.integrated.project.models.Feedback;
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

    @PostMapping("/Create")
    public Users createUsers(@RequestBody Users user){
        List<Users> listuser = usersRepositories.findAll();
        Users[] userArrays = new Users[listuser.size()];
        listuser.toArray(userArrays);
        user.setUserId(usersRepositories.userLatestId() + 1);
        usersRepositories.save(user);
        return user;
    }
    @DeleteMapping("/Delete/{id}")
    public String deleteUsers(@PathVariable int id){
        usersRepositories.deleteById(id);
        String s = String.valueOf(id);
        return s+"has been deleted";
    }
}
