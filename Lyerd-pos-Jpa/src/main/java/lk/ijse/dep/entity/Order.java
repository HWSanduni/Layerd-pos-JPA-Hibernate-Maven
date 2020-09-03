package lk.ijse.dep.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "orderDetailList")
@Entity
@Table(name = "`Order`")
public class Order implements SuperEntity {

    @Id
    @Column(name = "id")
    private String orderId;
    @Column(name = "date")
    private Date orderDate;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "customerId",referencedColumnName = "id",nullable = false)
    private Customer customerId;

    @OneToMany(mappedBy = "order",cascade = {CascadeType.PERSIST,CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<OrderDetail> orderDetailList;


    public Order(String orderId, Date orderDate, Customer customerId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
    }


    public Order(String orderId, Date orderDate, Customer customerId, List<OrderDetail> orderDetailList) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customerId = customerId;
        for (OrderDetail orderDetail:orderDetailList){
            orderDetail.setOrder(this);
        }
        this.orderDetailList = orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        for (OrderDetail orderDetail:orderDetailList){
            orderDetail.setOrder(this);
        }
        this.orderDetailList = orderDetailList;
    }

    public void addOrderDetails(OrderDetail orderDetail){
        orderDetail.setOrder(this);
        this.getOrderDetailList().add(orderDetail);
    }

}
