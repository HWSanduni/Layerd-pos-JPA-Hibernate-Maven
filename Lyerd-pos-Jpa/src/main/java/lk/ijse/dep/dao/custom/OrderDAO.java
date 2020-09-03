package lk.ijse.dep.dao.custom;

import lk.ijse.dep.entity.Order;

public interface OrderDAO extends CrudDAO<Order,String> {
    public String getLastOrderId()throws Exception;
/*    public List<Order> findAllOrders();
    public Order findOrder(String orderId);
    public boolean saveOrder(Order order);
    public boolean updateOrder(Order order);
    public boolean deleteOrder(String orderId);*/

    }
