package lk.ijse.dep.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Item implements SuperEntity {

    @Id
    @Column(name = "code")
    private String itemCode;
    private String description;
    @Column(name = "unitPrice")
    private BigDecimal unitprice;
    @Column(name = "qtyOnHand")
    private int qtyOnHand;


}
