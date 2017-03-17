package pl.wykop.dto;

import lombok.Data;

/**
 * Created by mariusz on 17.03.17.
 */
@Data
public class CellDto {

    private Long id;
    private String createDate;
    private String confirmUrl;
    private String category;
}
