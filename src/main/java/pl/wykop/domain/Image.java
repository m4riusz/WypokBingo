package pl.wykop.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Created by mariusz on 20.03.17.
 */
@Data
@Entity
@Builder
public class Image extends Cell {

    @Length(min = 1, max = 255)
    private String fileName;

    @Lob
    private byte[] bytes;

}
