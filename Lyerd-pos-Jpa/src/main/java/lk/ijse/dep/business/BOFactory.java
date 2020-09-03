package lk.ijse.dep.business;

import lk.ijse.dep.business.custom.impl.CustomerBOImpl;
import lk.ijse.dep.business.custom.impl.ItemBOImpl;
import lk.ijse.dep.business.custom.impl.OrderBOImpl;

public class BOFactory {
    private static BOFactory bofactory;

    public BOFactory() {
    }
    public static BOFactory getInstance(){
        return (bofactory == null) ? bofactory = new BOFactory() : bofactory;

    }

    public <T extends SuperBO> T getBO(BOType boType) {
        switch (boType) {
            case CUSTOMER:
                return (T) new CustomerBOImpl();
            case ITEM:
                return (T) new ItemBOImpl();
            case ORDER:
                return (T) new OrderBOImpl();
            default:
                return null;
        }
    }
}
