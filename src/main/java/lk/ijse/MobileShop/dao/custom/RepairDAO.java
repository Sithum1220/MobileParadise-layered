package lk.ijse.MobileShop.dao.custom;

import lk.ijse.MobileShop.dao.CrudDAO;
import lk.ijse.MobileShop.dto.CustomerOrderDTO;
import lk.ijse.MobileShop.dto.RepairDTO;
import lk.ijse.MobileShop.entity.CustomerOrder;
import lk.ijse.MobileShop.entity.Repair;

import java.sql.SQLException;

public interface RepairDAO extends CrudDAO<Repair,String> {

    boolean updateReturnGiveDateByRepair(CustomerOrder customerOrder) throws SQLException, ClassNotFoundException;

    boolean updateReturnGiveDateByWarranty(String id) throws SQLException, ClassNotFoundException;
    String getStatus(String id) throws SQLException, ClassNotFoundException;
     String getReturnGiveDate(String id) throws SQLException, ClassNotFoundException;
}
