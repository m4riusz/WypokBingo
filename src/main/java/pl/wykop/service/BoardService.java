package pl.wykop.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import pl.wykop.domain.Board;
import pl.wykop.dto.BoardCreateForm;
import pl.wykop.dto.CellFieldCreateForm;
import pl.wykop.dto.CellImageCreateForm;
import pl.wykop.exception.BoardCreateException;
import pl.wykop.exception.BoardNotFoundException;
import pl.wykop.exception.CategoryNotFoundException;
import pl.wykop.exception.UserNotFoundException;

import java.util.stream.Stream;

/**
 * Created by mariusz on 17.03.17.
 */
@Service
public interface BoardService {

    @PreAuthorize("isAuthenticated()")
    Board createBoard(BoardCreateForm boardCreateForm) throws BoardCreateException;

    @PreAuthorize("hasPermission(#boardId,'board_add_cell')")
    Board addCell(Long boardId, CellImageCreateForm cellImageCreateForm) throws BoardNotFoundException;

    @PreAuthorize("hasPermission(#boardId,'board_add_cell')")
    Board addCell(Long boardId, CellFieldCreateForm cellFieldCreateForm) throws BoardNotFoundException, CategoryNotFoundException;

    Stream<Board> getAllFromUser(String username) throws UserNotFoundException;

    Board getByUsernameAndName(String username, String boardName) throws UserNotFoundException, BoardNotFoundException;
}
