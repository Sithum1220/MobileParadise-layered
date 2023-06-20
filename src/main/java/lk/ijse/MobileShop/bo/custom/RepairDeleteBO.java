package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.dto.RepairDTO;

import java.sql.SQLException;

public interface RepairDeleteBO extends SuperBO {
    CustomerDTO getCustomerData(String id) throws SQLException, ClassNotFoundException;
    boolean deleteRepair(String id) throws SQLException, ClassNotFoundException;
    RepairDTO getAllRepairData(String id) throws SQLException, ClassNotFoundException;
}
