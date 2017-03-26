package pl.wykop.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by mariusz on 17.03.17.
 */
@Data
public class BoardDto {

    private Long id;
    private String createDate;
    private String modifyDate;
    private String name;
    private String description;
    private UserDto owner;
    private List<CellDto> cells;
}
