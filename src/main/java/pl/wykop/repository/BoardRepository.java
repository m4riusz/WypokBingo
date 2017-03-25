package pl.wykop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wykop.domain.Board;

import java.util.Optional;

/**
 * Created by mariusz on 17.03.17.
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {

    Optional<Board> findById(Long id);
}
