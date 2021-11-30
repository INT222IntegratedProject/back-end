
package sit.integrated.project.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.integrated.project.models.Feedback;
import sit.integrated.project.models.Products;
import sit.integrated.project.models.Roles;
import sit.integrated.project.models.Users;
import sit.integrated.project.repositories.FeedbackRepositories;
import sit.integrated.project.repositories.UsersRepositories;

import java.util.List;

@RestController
@RequestMapping("/Feedback")
@CrossOrigin
public class FeedbackController {
    @Autowired
    private FeedbackRepositories feedbackRepositories;

    @Autowired
    private UsersRepositories usersRepositories;
    @GetMapping("/GetFeedback")
    public List<Feedback> listAllFeedback(){
        return feedbackRepositories.findAll();
    }

     @GetMapping("/GetFeedback/{pid}")
    public Feedback listFeedbackByProductsId(@PathVariable int pid){
        List<Feedback> feedbackList = feedbackRepositories.findAll();
        Feedback[] feedbackArray = new Feedback[feedbackList.size()];
        feedbackList.toArray(feedbackArray);
        Feedback feedback = new Feedback();
        for(int i=0;i< feedbackArray.length;i++){
            if(feedbackArray[i].getProductsId()==pid){
                feedback = feedbackArray[i];
            }
        }
        return feedback;
    }

    @GetMapping("/GetUserFeedback/{id}")
    public String getUserFeedback(@PathVariable int id){
        List<Users> UsersList = usersRepositories.findAll();
        Users[] usersArray = new Users[UsersList.size()];
        UsersList.toArray(usersArray);
        String username = "";
        for(int i = 0 ; i < usersArray.length; i++ ) {
            if(usersArray[i].getUserId()==id){
                username = usersArray[i].getUserName();
            }
        }

        return username;
    }

    @PostMapping("/Create")
    public Feedback createFeedback(@RequestBody Feedback feedback){
        List<Feedback> listfeed = feedbackRepositories.findAll();
        Feedback[] feedArrays = new Feedback[listfeed.size()];
        listfeed.toArray(feedArrays);
        feedback.setFeedbackId(feedbackRepositories.feedbackLatestId() + 1);
        feedbackRepositories.save(feedback);
        return feedback;
    }

    @DeleteMapping("/Delete/{id}")
    public void deleteRole(@PathVariable int id){

        feedbackRepositories.deleteById(id);

    }

}
