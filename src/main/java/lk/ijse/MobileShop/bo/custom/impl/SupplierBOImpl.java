package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.SupplierBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.SupplierDAO;
import lk.ijse.MobileShop.dao.custom.SupplierOrderDAO;
import lk.ijse.MobileShop.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.SupplierOrderDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);
    SupplierOrderDAO supplierOrderDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER_ORDER);

    @Override
    public ArrayList<String> getSearchSupplierId(String text) throws SQLException, ClassNotFoundException {
        return supplierDAO.getSearchId(text);
    }

    @Override
    public ArrayList<String> getSearchSupplierOrderId(String text) throws SQLException, ClassNotFoundException {
        return supplierOrderDAO.getSearchId(text);
    }

    @Override
    public ArrayList<String> getAllSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAllId();
    }

    @Override
    public ArrayList<String> getAllSupplierOrderId() throws SQLException, ClassNotFoundException {
        return supplierOrderDAO.getAllId();
    }

    @Override
    public String getAllSupplierCount() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAllCount();
    }

    @Override
    public String getAllTodaySupplierOrderCount() throws SQLException, ClassNotFoundException {
        return supplierOrderDAO.getAllTodayOrderCount();
    }
}
