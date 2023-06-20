package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dao.SuperDAO;
import lk.ijse.MobileShop.dto.CustomEntityDTO;

import java.sql.SQLException;

public interface CustomerOrderBarBO extends SuperBO {

    CustomEntityDTO getCustomerOrderData(String id) throws SQLException, ClassNotFoundException;
}
