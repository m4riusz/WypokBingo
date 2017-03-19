package pl.wykop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.wykop.config.Route;
import pl.wykop.dto.BoardCreateForm;
import pl.wykop.dto.BoardDto;
import pl.wykop.exception.BoardCreateException;
import pl.wykop.mapper.BoardMapper;
import pl.wykop.service.BoardService;

/**
 * Created by mariusz on 18.03.17.
 */
@RestController
@AllArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;

    @RequestMapping(value = Route.BOARD, method = RequestMethod.POST)
    public BoardDto createBoard(@RequestBody BoardCreateForm boardCreateForm) throws BoardCreateException {
        return boardMapper.boardToBoardDto(boardService.createBoard(boardCreateForm));

    }
}
