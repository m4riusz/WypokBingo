package pl.wykop.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by mariusz on 06.03.17.
 */
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime updateDate;

    @Version
    private long version;

}
