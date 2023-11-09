package constant.entity;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@Getter
@Setter
public class BaseEntity {

    private Date createDate;
    private Date modifyDate;
}
