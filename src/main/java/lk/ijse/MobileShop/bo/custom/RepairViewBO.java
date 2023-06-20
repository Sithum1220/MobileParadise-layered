package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.dto.RepairDTO;

import java.sql.SQLException;

public interface RepairViewBO extends SuperBO {

   CustomerDTO getCustomerData(String id) throws SQLException, ClassNotFoundException;
   RepairDTO getRepairData(String id) throws SQLException, ClassNotFoundException;
   boolean updateReturnGiveDateByWarranty(String id) throws SQLException, ClassNotFoundException;

   String getReturnGiveDate(String id) throws SQLException, ClassNotFoundException;
    String getRepairWarrentyStatus(String id) throws SQLException, ClassNotFoundException;


}
