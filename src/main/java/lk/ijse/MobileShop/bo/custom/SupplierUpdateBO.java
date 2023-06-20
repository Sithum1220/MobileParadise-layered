package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.SupplierDTO;

import java.sql.SQLException;

public interface SupplierUpdateBO extends SuperBO {
    boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
    SupplierDTO getAllSupplierData(String id) throws SQLException, ClassNotFoundException;
}
