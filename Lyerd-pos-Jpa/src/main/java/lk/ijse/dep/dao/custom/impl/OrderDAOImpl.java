package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.dao.CrudDAOImpl;
import lk.ijse.dep.dao.custom.OrderDAO;
import lk.ijse.dep.entity.Order;

import java.util.List;

public class OrderDAOImpl extends CrudDAOImpl<Order,String> implements OrderDAO {



    public String getLastOrderId()throws Exception{

      List list= entityManager.createQuery("SELECT o.id FROM entity.Order o ORDER BY o.id DESC").setMaxResults(1).getResultList();

      return (list.size()>0) ?(String) list.get(0):null ;
    }


}
