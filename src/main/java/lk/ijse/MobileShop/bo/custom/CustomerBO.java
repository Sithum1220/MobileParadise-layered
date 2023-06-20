package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dao.SuperDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<String> getSearchCustomerId(String text) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCustomerId() throws SQLException, ClassNotFoundException;
    String getAllCustomerCount() throws SQLException, ClassNotFoundException;
}
