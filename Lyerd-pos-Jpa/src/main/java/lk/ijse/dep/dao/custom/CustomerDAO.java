package lk.ijse.dep.dao.custom;

import lk.ijse.dep.entity.Customer;

public interface CustomerDAO extends CrudDAO<Customer,String> {
    public String getLastCustomerId() throws Exception;
/*    public List<Object> findAll();
    public Object find(Object id);
    public boolean save(Object customer);
    public boolean update(Object customer);
    public boolean delete(Object id);*/

    }
