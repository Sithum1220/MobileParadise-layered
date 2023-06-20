package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.CustomerOrderBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.CustomerOrderDAO;
import lk.ijse.MobileShop.dao.custom.RepairDAO;
import lk.ijse.MobileShop.dao.custom.impl.CustomerOrderDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.RepairDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderBOImpl implements CustomerOrderBO {
    CustomerOrderDAO customerOrderDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER_ORDER);
    RepairDAO repairDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.REPAIR);
    @Override
    public ArrayList<String> getCustomerOrderSearchId(String text) throws SQLException, ClassNotFoundException {
        return customerOrderDAO.getSearchId(text);
    }

    @Override
    public ArrayList<String> getRepairSearchId(String text) throws SQLException, ClassNotFoundException {
        return repairDAO.getSearchId(text);
    }

    @Override
    public ArrayList<String> getAllCustomerOrderId() throws SQLException, ClassNotFoundException {
        return customerOrderDAO.getAllId();
    }

    @Override
    public ArrayList<String> getAllRepairId() throws SQLException, ClassNotFoundException {
        return repairDAO.getAllId();
    }

    @Override
    public String getTodayCustomerOrderCount() throws SQLException, ClassNotFoundException {
        return customerOrderDAO.getTodayOrdersCount();
    }
}
