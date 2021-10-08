
package sit.integrated.project.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.integrated.project.models.Feedback;
import sit.integrated.project.repositories.FeedbackRepositories;
import java.util.List;

@RestController
@RequestMapping("/Feedback")
@CrossOrigin
public class FeedbackController {
    @Autowired
    private FeedbackRepositories feedbackRepositories;

    @GetMapping("/GetFeedback")
    public List<Feedback> listAllBrands(){
        return feedbackRepositories.findAll();
    }

}
