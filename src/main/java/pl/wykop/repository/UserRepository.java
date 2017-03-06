package pl.wykop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wykop.domain.User;

import java.util.Optional;

/**
 * Created by mariusz on 06.03.17.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
