package lk.ijse.dep.dao.custom.impl;

import lk.ijse.dep.dao.CrudDAOImpl;
import lk.ijse.dep.dao.custom.ItemDAO;
import lk.ijse.dep.entity.Item;

import java.util.List;

public class ItemDAOImpl extends CrudDAOImpl<Item,String> implements ItemDAO {


    public String getLastitemCode() throws Exception{
       List list = entityManager.createQuery("SELECT i.itemCode FROM entity.Item i ORDER BY i.itemCode DESC").setMaxResults(1).getResultList();
        return (list.size()>0) ? (String) list.get(0):null;
    }


}
