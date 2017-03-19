package pl.wykop.service;

import lombok.Data;
import org.springframework.stereotype.Service;
import pl.wykop.domain.BingoImage;
import pl.wykop.domain.Board;
import pl.wykop.domain.User;
import pl.wykop.dto.BoardCreateForm;
import pl.wykop.exception.BoardCreateException;
import pl.wykop.repository.BoardRepository;

import javax.transaction.Transactional;

import static java.util.Collections.emptyList;

/**
 * Created by mariusz on 18.03.17.
 */
@Data
@Service
@Transactional
public class BoardServiceImpl extends AbstractService implements BoardService {

    private final UserService userService;
    private final BoardRepository boardRepository;

    @Override
    public Board createBoard(BoardCreateForm boardCreateForm) throws BoardCreateException {
        User currentUser = userService.getCurrentUser();

        if (boardWithNameAlreadyExistsForUser(boardCreateForm, currentUser)) {
            throw new BoardCreateException(getMessage("board.create.error.name"));
        } else if (isMaxBoardCount(currentUser)) {
            throw new BoardCreateException(getMessage("board.create.error.count"));
        }
        Board board = Board.builder()
                .name(boardCreateForm.getName())
                .description(boardCreateForm.getDescription())
                .owner(currentUser)
                .bingoImage(getDefaultImage())
                .cells(emptyList())
                .build();
        return boardRepository.save(board);
    }

    private boolean isMaxBoardCount(User currentUser) {
        return currentUser.getBoards().size() > getEnvironment("user.max.board.count", Long.class);
    }

    private boolean boardWithNameAlreadyExistsForUser(BoardCreateForm boardCreateForm, User currentUser) {
        return currentUser.getBoards().stream().anyMatch(board -> board.getName().equals(boardCreateForm.getName()));
    }


    private BingoImage getDefaultImage() {
        return BingoImage.builder().bytes(new byte[0]).fileName(getEnvironment("image.default.name")).build();
    }
}
