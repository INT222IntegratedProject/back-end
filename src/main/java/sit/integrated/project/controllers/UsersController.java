package sit.integrated.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sit.integrated.project.exceptions.ExceptionResponse;
import sit.integrated.project.exceptions.ProductsException;
import sit.integrated.project.models.Feedback;
import sit.integrated.project.models.JwtUser;
import sit.integrated.project.models.Products;
import sit.integrated.project.models.Users;
import sit.integrated.project.repositories.FeedbackRepositories;
import sit.integrated.project.repositories.JwtUserRepositories;
import sit.integrated.project.repositories.UsersRepositories;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Users")
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersRepositories usersRepositories;

    @Autowired
    private JwtUserRepositories jwtusersRepositories;

    private FeedbackRepositories feedbackRepositories;

    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    @GetMapping("/GetUsers")
    public List<Users> ListAllUsers(){
        return usersRepositories.findAll();
    }

    @GetMapping("/GetUsers/{id}")
    public Users getUserById(@PathVariable int id){
    return usersRepositories.findById(id).orElseThrow(()->new RuntimeException());
    }


    @GetMapping("/Login")
    public Users login(@RequestParam String username){
        Users  users = usersRepositories.findByUserName(username);
        return users;
    }



    @PostMapping("/Create")
    public Users createUsers(@RequestBody Users user){
            String username = user.getUserName();
            String password = bcrypt.encode(user.getUserPassword());
            JwtUser userJwt = new JwtUser();
            userJwt.setUsername(username);
            userJwt.setPassword(password);
            jwtusersRepositories.save(userJwt);
            List<Users> listuser = usersRepositories.findAll();
            Users[] userArrays = new Users[listuser.size()];
            listuser.toArray(userArrays);
            user.setUserId(usersRepositories.userLatestId() + 1);
            usersRepositories.save(user);
            return user;
    }



    @PutMapping("/Edit/{id}")
    public Users editUser(@RequestBody Users users, @PathVariable int id){

            if (hasFoundId(id) && (id == users.getUserId())) {
                usersRepositories.save(users);
                return users;
            } else throw new ProductsException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST, "DOES NOT EXIST");
    }

    @DeleteMapping("/Delete/{id}")
    public String deleteUsers(@PathVariable int id){
            usersRepositories.deleteById(id);
            String s = String.valueOf(id);
            return s + "has been deleted";
    }

    public boolean hasFoundId(int id){
        List<Users> users = usersRepositories.findAll();
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getUserId() == id){
                return true;
            }
        }
        return false;
    }
}
