package pl.wykop.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import pl.wykop.domain.annotations.BoardDimension;
import pl.wykop.domain.annotations.BoardName;

/**
 * Created by mariusz on 17.03.17.
 */
@Data
public class BoardCreateForm {

    @BoardName
    private String name;

    @Length(max = 255)
    private String description;

    @BoardDimension(message = "{validator.board.width}")
    private short width;

    @BoardDimension(message = "{validator.board.height}")
    private short height;

}
