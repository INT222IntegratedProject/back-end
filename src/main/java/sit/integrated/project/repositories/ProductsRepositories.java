package sit.integrated.project.repositories;

import org.springframework.data.jpa.repository.Query;
import sit.integrated.project.models.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import sit.integrated.project.models.Users;

import java.util.List;
import java.util.Optional;

public interface ProductsRepositories extends JpaRepository<Products,Integer> {
    @Query(value = "SELECT max(productId) from Products ")
     int productLatestId();

     List<Products> findAllByUserId (Optional<Users> userId);
}