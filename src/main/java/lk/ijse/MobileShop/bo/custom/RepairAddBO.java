package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.dto.CustomerOrderDTO;
import lk.ijse.MobileShop.dto.RepairDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepairAddBO extends SuperBO {
    CustomerDTO getCustomerData(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCustomerId() throws SQLException, ClassNotFoundException;
    ItemDTO getAllItemData(String id) throws SQLException, ClassNotFoundException;
    CustomerOrderDTO getOrderDate(String id) throws SQLException, ClassNotFoundException;
    boolean saveRepair(RepairDTO repairDTO) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllRepairId() throws SQLException, ClassNotFoundException;
}
