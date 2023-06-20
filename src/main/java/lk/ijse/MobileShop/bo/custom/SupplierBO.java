package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<String> getSearchSupplierId(String text) throws SQLException, ClassNotFoundException;
    ArrayList<String> getSearchSupplierOrderId(String text) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSupplierId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSupplierOrderId() throws SQLException, ClassNotFoundException;
    String getAllSupplierCount() throws SQLException, ClassNotFoundException;
    String getAllTodaySupplierOrderCount() throws SQLException, ClassNotFoundException;
}
