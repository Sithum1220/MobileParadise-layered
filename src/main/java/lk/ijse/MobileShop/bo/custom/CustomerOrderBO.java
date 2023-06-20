package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dao.SuperDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerOrderBO extends SuperBO {
    ArrayList<String> getCustomerOrderSearchId(String text) throws SQLException, ClassNotFoundException;
    ArrayList<String> getRepairSearchId(String text) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCustomerOrderId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllRepairId() throws SQLException, ClassNotFoundException;
    String getTodayCustomerOrderCount() throws SQLException, ClassNotFoundException;
}
