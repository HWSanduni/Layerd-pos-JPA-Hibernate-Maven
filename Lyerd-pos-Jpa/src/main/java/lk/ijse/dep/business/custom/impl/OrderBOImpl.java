package lk.ijse.dep.business.custom.impl;

import lk.ijse.dep.business.custom.OrderBO;
import lk.ijse.dep.dao.DAOFactory;
import lk.ijse.dep.dao.DAOType;
import lk.ijse.dep.dao.custom.CustomerDAO;
import lk.ijse.dep.dao.custom.ItemDAO;
import lk.ijse.dep.dao.custom.OrderDAO;
import lk.ijse.dep.dao.custom.OrderDetailDAO;
import lk.ijse.dep.db.JPAUtil;
import lk.ijse.dep.entity.Item;
import lk.ijse.dep.entity.Order;
import lk.ijse.dep.entity.OrderDetail;
import lk.ijse.dep.util.OrderDetailTM;
import lk.ijse.dep.util.OrderTM;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class OrderBOImpl implements OrderBO {
   private OrderDAO orderDAO = DAOFactory.getInstance().getDAO(DAOType.ORDER);
   private OrderDetailDAO orderDetailDAO = DAOFactory.getInstance().getDAO(DAOType.ORDERDETAIL);
   private ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);
   private CustomerDAO customerDAO = DAOFactory.getInstance().getDAO(DAOType.CUSTOMER);


    public String getNewOrderId() throws Exception{
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        orderDAO.setEntityManager(entityManager);
        String lastOrderId = null;


        try {
            entityManager.getTransaction().begin();
            lastOrderId = orderDAO.getLastOrderId();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }

        if (lastOrderId == null){
            return "OD001";
        }else{
            int maxId=  Integer.parseInt(lastOrderId.replace("OD",""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "OD00" + maxId;
            } else if (maxId < 100) {
                id = "OD0" + maxId;
            } else {
                id = "OD" + maxId;
            }
            return id;
        }
    }
    public void placeOrders(OrderTM order, List<OrderDetailTM> orderDetails) throws Exception {

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        orderDAO.setEntityManager(entityManager);
        orderDetailDAO.setEntityManager(entityManager);
        customerDAO.setEntityManager(entityManager);

        try {

            entityManager.getTransaction().begin();
            orderDAO.save(new Order(order.getOrderId(), Date.valueOf(order.getOrderDate()),
                  customerDAO.find(order.getCustomerId())));

            for (OrderDetailTM orderDetail : orderDetails) {
                 orderDetailDAO.save(new OrderDetail(order.getOrderId(),
                         orderDetail.getCode(), orderDetail.getQty(),
                         BigDecimal.valueOf(orderDetail.getUnitPrice())));

                Item item = itemDAO.find(orderDetail.getCode());
                item.setQtyOnHand(item.getQtyOnHand()-orderDetail.getQty());
                System.out.println(item.getQtyOnHand()-orderDetail.getQty());
               itemDAO.update(item);
                System.out.println(item);

            }
            entityManager.getTransaction().commit();
        } catch (Throwable t) {
            entityManager.getTransaction().rollback();
          throw  t;
        } finally {
            entityManager.close();
        }

    }
}
