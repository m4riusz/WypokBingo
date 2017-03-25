package pl.wykop.dto;

import lombok.Data;
import pl.wykop.domain.annotations.CellImageFileName;

import javax.persistence.Lob;

/**
 * Created by mariusz on 24.03.17.
 */
@Data
public class CellImageCreateForm {

    @CellImageFileName
    private String fileName;
    @Lob
    private byte[] bytes;
}
