package pl.wykop.domain;

import lombok.Builder;
import lombok.Data;
import pl.wykop.domain.annotations.CellImageFileName;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Created by mariusz on 20.03.17.
 */
@Data
@Entity
@Builder
public class Image extends Cell {

    @CellImageFileName
    private String fileName;

    @Lob
    private byte[] bytes;

}
