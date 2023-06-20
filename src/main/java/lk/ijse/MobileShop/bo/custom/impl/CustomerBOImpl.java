package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.CustomerBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.CustomerDAO;
import lk.ijse.MobileShop.dao.custom.impl.CustomerDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public ArrayList<String> getSearchCustomerId(String text) throws SQLException, ClassNotFoundException {
        return customerDAO.getSearchId(text);
    }

    @Override
    public ArrayList<String> getAllCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllId();
    }

    @Override
    public String getAllCustomerCount() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllCount();
    }
}
