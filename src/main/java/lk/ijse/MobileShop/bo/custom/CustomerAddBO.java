package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dao.SuperDAO;
import lk.ijse.MobileShop.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerAddBO extends SuperBO {
    boolean saveCustomers(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllCustomersId() throws SQLException, ClassNotFoundException;
}
