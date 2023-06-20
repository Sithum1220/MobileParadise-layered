package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.CustomerDTO;

import java.sql.SQLException;

public interface CustomerUpdateBO extends SuperBO {

    CustomerDTO getCustomerData(String id) throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;
}
