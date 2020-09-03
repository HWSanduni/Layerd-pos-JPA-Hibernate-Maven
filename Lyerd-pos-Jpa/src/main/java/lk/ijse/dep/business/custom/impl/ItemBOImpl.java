package lk.ijse.dep.business.custom.impl;

import lk.ijse.dep.business.custom.ItemBO;
import lk.ijse.dep.dao.DAOFactory;
import lk.ijse.dep.dao.DAOType;
import lk.ijse.dep.dao.custom.ItemDAO;
import lk.ijse.dep.db.JPAUtil;
import lk.ijse.dep.entity.Item;
import lk.ijse.dep.util.ItemTM;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    private ItemDAO itemDAO = DAOFactory.getInstance().getDAO(DAOType.ITEM);

    public List<ItemTM> getAllItems() throws Exception{

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        List<Item> allItems = null;
        try{
            entityManager.getTransaction().begin();

            allItems = itemDAO.findAll();

            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }

        ArrayList<ItemTM> items = new ArrayList<>();
        for (Object i : allItems) {
            Item item = (Item) i;
            items.add(new ItemTM(item.getItemCode(),item.getDescription(),item.getQtyOnHand(),item.getUnitprice().doubleValue()));
        }
        return items;



    }

    public String getNewitemCode()throws Exception{

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        String lastitemCode = null;
        try{
            entityManager.getTransaction().begin();

            lastitemCode = itemDAO.getLastitemCode();

            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }
        if (lastitemCode == null){
            return "I001";
        }else{
            int maxId=  Integer.parseInt(lastitemCode.replace("I",""));
            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "I00" + maxId;
            } else if (maxId < 100) {
                id = "I0" + maxId;
            } else {
                id = "I" + maxId;
            }
            return id;
        }
    }

    public void saveItem(String code, String description, int qtyOnHand, double unitPrice)throws Exception{


        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        try{
            entityManager.getTransaction().begin();

            itemDAO.save(new Item(code, description, BigDecimal.valueOf(unitPrice), qtyOnHand));

            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }


    }

    public void deleteItem(String itemCode) throws Exception{


        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        try{
            entityManager.getTransaction().begin();

            itemDAO.delete(itemCode);

            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }

    }

    public void updateItem(String description, int qtyOnHand, double unitPrice, String itemCode)throws Exception{

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        itemDAO.setEntityManager(entityManager);
        try{
            entityManager.getTransaction().begin();

            itemDAO.update(new Item(itemCode, description, BigDecimal.valueOf(unitPrice),qtyOnHand));

            entityManager.getTransaction().commit();
        }catch (Exception e){
            entityManager.getTransaction().rollback();
            throw e;
        }finally {
            entityManager.close();
        }
    }
}
