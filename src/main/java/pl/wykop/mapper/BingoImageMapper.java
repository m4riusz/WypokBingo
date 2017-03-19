package pl.wykop.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.wykop.domain.BingoImage;
import pl.wykop.dto.BingoImageDto;

/**
 * Created by mariusz on 17.03.17.
 */
@Mapper(componentModel = "spring")
public interface BingoImageMapper {

    @Mapping(source = "fileName", target = "fileName")
    @Mapping(source = "bytes", target = "image")
    BingoImageDto bingoImageToBingoImageDto(BingoImage bingoImage);
}
