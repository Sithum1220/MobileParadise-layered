package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.ItemCashierViewBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.ItemDAO;
import lk.ijse.MobileShop.dao.custom.impl.ItemDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemCashierViewBOImpl implements ItemCashierViewBO {
    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public ArrayList<String> getSearchId(String text) throws SQLException, ClassNotFoundException {
        return itemDAO.getSearchId(text);
    }

    @Override
    public ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllId();
    }

    @Override
    public String getItemLimitCount() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemLimitCount();
    }
}
