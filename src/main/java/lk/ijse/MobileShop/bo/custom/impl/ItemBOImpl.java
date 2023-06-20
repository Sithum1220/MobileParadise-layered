package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.ItemBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.ItemDAO;
import lk.ijse.MobileShop.dao.custom.impl.ItemDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public ArrayList<String> getSearchId(String text) throws SQLException, ClassNotFoundException {
        return itemDAO.getAllId();
    }

    @Override
    public ArrayList<String> getAllItem() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllId();
    }

    @Override
    public String getItemLimitCount() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemLimitCount();
    }
}
