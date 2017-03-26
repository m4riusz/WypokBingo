package pl.wykop.dto;

import lombok.Data;

/**
 * Created by mariusz on 26.03.17.
 */
@Data
public class ImageDto extends CellDto{

    private String fileName;
    private byte[] bytes;
}
