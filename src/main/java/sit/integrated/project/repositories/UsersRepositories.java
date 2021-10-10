package sit.integrated.project.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import sit.integrated.project.models.Users;

public interface UsersRepositories extends JpaRepository<Users, Integer> {
}
