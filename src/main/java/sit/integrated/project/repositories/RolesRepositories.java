package sit.integrated.project.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import sit.integrated.project.models.Roles;

public interface RolesRepositories extends JpaRepository<Roles, String> {
}
