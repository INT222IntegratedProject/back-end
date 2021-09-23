package sit.integrated.project.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import sit.integrated.project.models.Feedback;

public interface FeedbackRepositories extends JpaRepository<Feedback, String> {
}
