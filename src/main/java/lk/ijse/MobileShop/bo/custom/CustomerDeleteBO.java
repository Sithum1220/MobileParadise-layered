package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dao.SuperDAO;
import lk.ijse.MobileShop.dto.CustomerDTO;

import java.sql.SQLException;

public interface CustomerDeleteBO extends SuperBO {
    boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

    CustomerDTO getCustomerData(String id) throws SQLException, ClassNotFoundException;
}
