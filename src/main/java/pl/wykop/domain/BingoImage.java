package pl.wykop.domain;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Created by mariusz on 09.03.17.
 */
@Data
@Builder
@Entity
public class BingoImage extends AbstractEntity {

    @Length(min = 1, max = 255)
    private String fileName;

    @Lob
    private byte[] bytes;

}
