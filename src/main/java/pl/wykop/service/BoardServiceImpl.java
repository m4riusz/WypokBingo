package pl.wykop.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import pl.wykop.domain.Board;
import pl.wykop.domain.Field;
import pl.wykop.domain.Image;
import pl.wykop.domain.User;
import pl.wykop.dto.BoardCreateForm;
import pl.wykop.dto.CellImageCreateForm;
import pl.wykop.exception.BoardCreateException;
import pl.wykop.exception.BoardNotFoundException;
import pl.wykop.exception.CategoryNotFoundException;
import pl.wykop.exception.UserNotFoundException;
import pl.wykop.repository.BoardRepository;
import pl.wykop.service.dto.CellFieldCreateForm;
import pl.wykop.util.ConfigSource;
import pl.wykop.util.LocaleMessageSource;

import javax.transaction.Transactional;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

/**
 * Created by mariusz on 18.03.17.
 */
@Data
@Service
@Transactional
public class BoardServiceImpl implements BoardService {

    private final UserService userService;
    private final CategoryService categoryService;
    private final BoardRepository boardRepository;
    private final ConfigSource configSource;
    private final LocaleMessageSource localeMessageSource;

    @Override
    public Board createBoard(BoardCreateForm boardCreateForm) throws BoardCreateException {
        User currentUser = userService.getCurrentUser();

        if (boardWithNameAlreadyExistsForUser(boardCreateForm, currentUser)) {
            throw new BoardCreateException(localeMessageSource.getMessage("board.create.error.name"));
        } else if (isMaxBoardCount(currentUser)) {
            throw new BoardCreateException(localeMessageSource.getMessage("board.create.error.count"));
        }
        Board board = Board.builder()
                .name(boardCreateForm.getName())
                .description(boardCreateForm.getDescription())
                .owner(currentUser)
                .cells(emptyList())
                .build();
        return boardRepository.save(board);
    }

    @Override
    public Board addCell(Long boardId, CellImageCreateForm cellImageCreateForm) throws BoardNotFoundException {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoundException(localeMessageSource.getMessage("board.find.error.name")));
        Image cellImage = Image.builder()
                .fileName(cellImageCreateForm.getFileName())
                .bytes(cellImageCreateForm.getBytes())
                .build();
        board.getCells().add(cellImage);
        return board;
    }

    @Override
    public Board addCell(Long boardId, CellFieldCreateForm cellFieldCreateForm) throws BoardNotFoundException, CategoryNotFoundException {
        Board board = boardRepository.findById(boardId).orElseThrow(() -> new BoardNotFoundException(localeMessageSource.getMessage("board.find.error.name")));
        Field cellImage = Field.builder()
                .category(categoryService.findByName(cellFieldCreateForm.getCategoryName()))
                .build();
        board.getCells().add(cellImage);
        return board;
    }

    @Override
    public Stream<Board> getAllFromUser(String username) throws UserNotFoundException {
        return userService.findByUsername(username).getBoards().stream();

    }

    @Override
    public Board getByUsernameAndName(String username, String boardName) throws UserNotFoundException, BoardNotFoundException {
        return getAllFromUser(username).filter(board -> board.getName().equals(boardName))
                .findFirst()
                .orElseThrow(() ->
                        new BoardNotFoundException(localeMessageSource.getMessage("board.find.error.name"))
                );
    }

    private boolean isMaxBoardCount(User currentUser) {
        return currentUser.getBoards().size() > configSource.getEnv("user.max.board.count", Long.class);
    }

    private boolean boardWithNameAlreadyExistsForUser(BoardCreateForm boardCreateForm, User currentUser) {
        return currentUser.getBoards().stream().anyMatch(board -> board.getName().equals(boardCreateForm.getName()));
    }
}
