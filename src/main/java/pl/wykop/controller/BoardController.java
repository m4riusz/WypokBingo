package pl.wykop.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.wykop.config.Route;
import pl.wykop.dto.BoardCreateForm;
import pl.wykop.dto.BoardDto;
import pl.wykop.exception.BoardCreateException;
import pl.wykop.exception.BoardNotFoundException;
import pl.wykop.exception.UserNotFoundException;
import pl.wykop.mapper.BoardMapper;
import pl.wykop.service.BoardService;

import javax.validation.Valid;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Created by mariusz on 18.03.17.
 */
@RestController
@AllArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardMapper boardMapper;

    @RequestMapping(value = Route.BOARD_URL, method = RequestMethod.POST)
    public BoardDto createBoard(@Valid @RequestBody BoardCreateForm boardCreateForm) throws BoardCreateException {
        return boardMapper.boardToBoardDto(boardService.createBoard(boardCreateForm));
    }

    @RequestMapping(value = Route.BOARD_BY_USERNAME, method = RequestMethod.GET)
    public List<BoardDto> getBoardsFromUser(@PathVariable String username) throws UserNotFoundException {
        return boardService.getAllFromUser(username).map(boardMapper::boardToBoardDto).collect(toList());
    }

    @RequestMapping(value = Route.BOARD_BY_USERNAME_AND_BOARD_NAME, method = RequestMethod.GET)
    public BoardDto getBoardFromUserByName(@PathVariable String username, @PathVariable String boardName) throws UserNotFoundException, BoardNotFoundException {
        return boardMapper.boardToBoardDto(boardService.getByUsernameAndName(username, boardName));
    }
}
