package pl.wykop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wykop.domain.Category;

import java.awt.print.Pageable;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by mariusz on 24.03.17.
 */
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    Stream<Category> findAll(Pageable pageable);

    Stream<Category> findByNameLike(String name, Pageable pageable);
}
