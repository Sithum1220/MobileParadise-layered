package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dao.SuperDAO;
import lk.ijse.MobileShop.dto.CustomerDTO;

import java.sql.SQLException;

public interface CustomerBarBO extends SuperBO {

    CustomerDTO getCustomerData(String id) throws SQLException, ClassNotFoundException;
}
