package pl.wykop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wykop.domain.Category;

/**
 * Created by mariusz on 24.03.17.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
