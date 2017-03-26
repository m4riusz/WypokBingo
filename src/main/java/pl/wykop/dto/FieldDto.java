package pl.wykop.dto;

import lombok.Data;

/**
 * Created by mariusz on 26.03.17.
 */
@Data
public class FieldDto extends CellDto {

    private String confirmUrl;
    private String category;
}
