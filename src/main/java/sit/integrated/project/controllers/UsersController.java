package sit.integrated.project.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.integrated.project.exceptions.ExceptionResponse;
import sit.integrated.project.exceptions.ProductsException;
import sit.integrated.project.models.Feedback;
import sit.integrated.project.models.JwtUser;
import sit.integrated.project.models.Products;
import sit.integrated.project.models.Users;
import sit.integrated.project.repositories.JwtUserRepositories;
import sit.integrated.project.repositories.UsersRepositories;
import java.util.List;

@RestController
@RequestMapping("/Users")
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersRepositories usersRepositories;

    @Autowired
    private JwtUserRepositories jwtusersRepositories;

    @GetMapping("/GetUsers")
    public List<Users> ListAllUsers(){return usersRepositories.findAll(); }

    @GetMapping("/GetUsers/{id}")
    public Users getUserById(@PathVariable int id){ return  usersRepositories.findById(id).orElse(null); }
    
    @GetMapping("/Login/{username}")
    public Object[] getUserByUsername(@PathVariable String username){
        List<Users> usersList = usersRepositories.findAll();
        Users[] usersArray = new Users[usersList.size()];
        usersList.toArray(usersArray);
        Object[] user = Arrays.stream(usersArray).filter(x -> x.getUserName().contains(username)).toArray();
        return  user;
         }

    @PostMapping("/Create")
    public Users createUsers(@RequestBody Users user){
        String username = user.getUserName();
        String password = user.getUserPassword();
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
        }
        else throw  new ProductsException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST , "DOES NOT EXIST");

    }
    
    @PostMapping("/Login")
    public Users login(@RequestBody JwtUser user){
        List<Users>  usersList = usersRepositories.findAll();
        Users[] userArray = new Users[usersList.size()];
        usersList.toArray(userArray);
        String checkUser;
        String checkPass;
        for(int i = 0 ; i < userArray.length; i++ ){
            checkUser = userArray[i].getUserName();
            checkPass = userArray[i].getUserPassword();
            if(checkUser.contains(user.getUsername())  && checkPass.contains(user.getPassword()) ){
                return userArray[i];
            }
        }
        return null;
    }
    
    @DeleteMapping("/Delete/{id}")
    public String deleteUsers(@PathVariable int id){
        usersRepositories.deleteById(id);
        String s = String.valueOf(id);
        return s+"has been deleted";
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
