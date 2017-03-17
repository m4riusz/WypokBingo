package pl.wykop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.wykop.domain.Board;
import pl.wykop.dto.BoardDto;

/**
 * Created by mariusz on 17.03.17.
 */
@Mapper(componentModel = "spring", uses = {UserMapper.class, CellMapper.class, BingoImageMapper.class, BaseMapper.class})
public interface BoardMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "updateDate", target = "modifyDate")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "owner", target = "owner")
    @Mapping(source = "bingoImage", target = "image")
    @Mapping(source = "cells", target = "cells")
    BoardDto boardToBoardDto(Board board);
}
