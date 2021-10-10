
package sit.integrated.project.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.integrated.project.models.Feedback;
import sit.integrated.project.models.Products;
import sit.integrated.project.repositories.FeedbackRepositories;
import java.util.List;

@RestController
@RequestMapping("/Feedback")
@CrossOrigin
public class FeedbackController {
    @Autowired
    private FeedbackRepositories feedbackRepositories;

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
            if(feedbackArray[i].getProductsId().getProductId()==pid){
                feedback = feedbackArray[i];
            }
        }
        return feedback;
    }

}
