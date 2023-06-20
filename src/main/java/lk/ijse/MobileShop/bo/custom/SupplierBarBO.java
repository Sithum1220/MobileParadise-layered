package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.SupplierDTO;

import java.sql.SQLException;

public interface SupplierBarBO extends SuperBO {
    SupplierDTO getAllSalaryData(String id) throws SQLException, ClassNotFoundException;
}
