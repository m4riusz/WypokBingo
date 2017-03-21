package pl.wykop.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.wykop.domain.Board;
import pl.wykop.dto.BoardCreateForm;
import pl.wykop.exception.BoardCreateException;
import pl.wykop.exception.BoardNotFoundException;
import pl.wykop.exception.UserNotFoundException;

import java.util.stream.Stream;

/**
 * Created by mariusz on 17.03.17.
 */
@Service
public interface BoardService {

    @PreAuthorize("isAuthenticated()")
    Board createBoard(BoardCreateForm boardCreateForm) throws BoardCreateException;

    Stream<Board> getAllFromUser(String username) throws UserNotFoundException;

    Board getByUsernameAndName(String username, String boardName) throws UserNotFoundException, BoardNotFoundException;
}
