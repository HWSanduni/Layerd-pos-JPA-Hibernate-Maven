package lk.ijse.dep.dao.custom;

import lk.ijse.dep.dao.SuperDAO;
import lk.ijse.dep.entity.CustomEntity;

public interface QueryDAO extends SuperDAO {
    CustomEntity getOrderDetail(String orderId)throws Exception;
    CustomEntity getOrderDetail2(String customerId)throws Exception;
    CustomEntity getOrderDetail3(String orderId)throws Exception;

}
