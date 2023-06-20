package lk.ijse.MobileShop.dao.custom;

import lk.ijse.MobileShop.dao.CrudDAO;
import lk.ijse.MobileShop.dto.CustomEntityDTO;
import lk.ijse.MobileShop.dto.SuplierOrderDetailsDTO;
import lk.ijse.MobileShop.dto.SupplierOrderDTO;
import lk.ijse.MobileShop.entity.SupplierOrder;
import lk.ijse.MobileShop.entity.SupplierOrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderDetailsDAO extends CrudDAO<SupplierOrderDetails, String> {

    boolean saveSupplierOrderDetails(ArrayList<CustomEntityDTO> list, SupplierOrder supplierOrder) throws SQLException, ClassNotFoundException;

    CustomEntityDTO getUnit_price(String id) throws SQLException, ClassNotFoundException;
}
