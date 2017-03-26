package pl.wykop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.wykop.domain.Cell;
import pl.wykop.domain.Field;
import pl.wykop.domain.Image;
import pl.wykop.dto.CellDto;
import pl.wykop.dto.FieldDto;
import pl.wykop.dto.ImageDto;

/**
 * Created by mariusz on 26.03.17.
 */
@Mapper(componentModel = "spring", uses = {BaseMapper.class})
public interface CellMapper {

    default CellDto cellToCellDto(Cell cell) {
        if (cell instanceof Field) {
            return fieldToFieldDto((Field) cell);
        } else if (cell instanceof Image) {
            return imageToImageDto((Image) cell);
        }
        return null;
    }

    @Mapping(source = "category.name", target = "category")
    @Mapping(source = "confirmLink.url", target = "confirmUrl")
    FieldDto fieldToFieldDto(Field field);

    @Mapping(source = "fileName", target = "fileName")
    @Mapping(source = "bytes", target = "bytes")
    ImageDto imageToImageDto(Image image);
}
