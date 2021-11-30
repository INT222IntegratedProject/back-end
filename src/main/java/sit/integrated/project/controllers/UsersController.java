package sit.integrated.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import sit.integrated.project.exceptions.ExceptionResponse;
import sit.integrated.project.exceptions.ProductsException;
import sit.integrated.project.models.*;
import sit.integrated.project.repositories.FeedbackRepositories;
import sit.integrated.project.repositories.JwtUserRepositories;
import sit.integrated.project.repositories.RolesRepositories;
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

    @Autowired
    private FeedbackRepositories feedbackRepositories;

    @Autowired
    private RolesRepositories rolesRepositories;

    private BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

    @GetMapping("/GetUsers")
    public List<Users> ListAllUsers(){
        return usersRepositories.findAll();
    }

    @GetMapping("/GetUsers/{id}")
    public Users getUserById(@PathVariable int id){
    return usersRepositories.findById(id).orElseThrow(()->new RuntimeException());
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

    @PostMapping("/EditRole/{id}")
    public Users upgrateUsers(@PathVariable int id){
        Users users = usersRepositories.findById(id).orElseThrow(()->new ProductsException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST, "DOES NOT EXIST"));
        Roles role = rolesRepositories.findById(91919).orElseThrow(()->new ProductsException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST, "DOES NOT EXIST"));
        users.setRoleId(role);
        usersRepositories.save(users);
        return users;
    }

    @PutMapping("/Edit/{id}")
    public Users editUser(@RequestBody Users users, @PathVariable int id){

            if (hasFoundId(id) && (id == users.getUserId())) {
                usersRepositories.save(users);
                return users;
            } else throw new ProductsException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST, "DOES NOT EXIST");
    }

    @DeleteMapping("/Delete/{id}")
    public void deleteUsers(@PathVariable int id){
        if(hasFoundId(id)){
            usersRepositories.deleteById(id);}
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
