package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;

import java.sql.SQLException;

public interface LoginScene2BO extends SuperBO {
    String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException;
}
