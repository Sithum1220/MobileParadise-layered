package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierAddBO extends SuperBO {
    boolean supplierSave(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSupplierId() throws SQLException, ClassNotFoundException;
}
