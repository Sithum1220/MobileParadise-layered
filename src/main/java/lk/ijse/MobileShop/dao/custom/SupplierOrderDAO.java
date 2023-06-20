package lk.ijse.MobileShop.dao.custom;

import lk.ijse.MobileShop.dao.CrudDAO;
import lk.ijse.MobileShop.dto.SupplierOrderDTO;
import lk.ijse.MobileShop.entity.SupplierOrder;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderDAO extends CrudDAO<SupplierOrder,String> {

     String getAllTodayOrderCount() throws SQLException, ClassNotFoundException;
     ArrayList<String> getSupplierOrderYear() throws SQLException, ClassNotFoundException;

     double getPayment(String processDate,String month) throws SQLException, ClassNotFoundException;
}
