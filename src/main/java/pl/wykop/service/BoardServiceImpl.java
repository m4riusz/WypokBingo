package pl.wykop.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.wykop.domain.BingoImage;
import pl.wykop.domain.Board;
import pl.wykop.dto.BoardCreateForm;
import pl.wykop.exception.BoardCreateException;
import pl.wykop.repository.BoardRepository;

import javax.transaction.Transactional;

import static java.util.Collections.emptyList;

/**
 * Created by mariusz on 18.03.17.
 */
@Service
@Transactional
@AllArgsConstructor
public class BoardServiceImpl extends AbstractService implements BoardService {

    private final UserService userService;
    private final BoardRepository boardRepository;

    @Override
    public Board createBoard(BoardCreateForm boardCreateForm) throws BoardCreateException {
        Board board = Board.builder()
                .name(boardCreateForm.getName())
                .description(boardCreateForm.getDescription())
                .owner(userService.getCurrentUser())
                .bingoImage(getDefaultImage())
                .cells(emptyList())
                .build();
        return boardRepository.save(board);
    }


    private BingoImage getDefaultImage() {
        return BingoImage.builder().bytes(new byte[0]).fileName("wypok.jpg").build();
    }
}
