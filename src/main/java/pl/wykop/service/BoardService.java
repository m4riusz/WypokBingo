package pl.wykop.service;

import org.springframework.stereotype.Service;
import pl.wykop.domain.Board;
import pl.wykop.dto.BoardCreateForm;
import pl.wykop.exception.BoardCreateException;

/**
 * Created by mariusz on 17.03.17.
 */
@Service
public interface BoardService {

    Board createBoard(BoardCreateForm boardCreateForm) throws BoardCreateException;
}
