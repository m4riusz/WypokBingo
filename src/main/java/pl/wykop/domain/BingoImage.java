package pl.wykop.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * Created by mariusz on 09.03.17.
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BingoImage extends AbstractEntity {

    @Length(min = 1, max = 255)
    private String fileName;

    @Lob
    private byte[] bytes;

}
