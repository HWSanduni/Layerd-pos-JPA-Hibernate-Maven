package lk.ijse.dep.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderDetail implements SuperEntity {


    @EmbeddedId
    private OrderDetailPK orderDetailPK;
    @Column(name = "qty")
    private int orderQty;
    @Column(name = "unitPrice")
    private BigDecimal unitprice;

    @ManyToOne
    @JoinColumn(name = "itemCode",referencedColumnName = "code",insertable = false,updatable = false)
    private Item item;

    @ManyToOne
    @JoinColumn(name = "orderId",referencedColumnName = "id",insertable = false,updatable = false)
    private Order order;


    public OrderDetail(String orderId, String itemCode, int orderQty, BigDecimal unitprice) {
        this.orderDetailPK = new OrderDetailPK(orderId,itemCode);
        this.orderQty = orderQty;
        this.unitprice = unitprice;
    }

    public OrderDetail(OrderDetailPK orderDetailPK, int orderQty, BigDecimal unitprice) {
        this.orderDetailPK = orderDetailPK;
        this.orderQty = orderQty;
        this.unitprice = unitprice;
    }
}
