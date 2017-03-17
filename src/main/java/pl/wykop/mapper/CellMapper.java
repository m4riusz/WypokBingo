package pl.wykop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.wykop.domain.Cell;
import pl.wykop.dto.CellDto;

/**
 * Created by mariusz on 17.03.17.
 */
@Mapper(componentModel = "spring", uses = BaseMapper.class)
public interface CellMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "createDate", target = "createDate")
    @Mapping(source = "confirmLink.url", target = "confirmUrl")
    @Mapping(source = "category.name", target = "category")
    CellDto cellToCellDto(Cell cell);
}
