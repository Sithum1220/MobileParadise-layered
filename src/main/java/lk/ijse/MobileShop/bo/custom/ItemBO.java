package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    ArrayList<String> getSearchId(String text) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItem() throws SQLException, ClassNotFoundException;
    String getItemLimitCount() throws SQLException, ClassNotFoundException;
}
