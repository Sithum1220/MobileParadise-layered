package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.EmployeeDTO;

import java.sql.SQLException;

public interface EmployeeBarBO extends SuperBO {
    EmployeeDTO getEmployeeData(String id) throws SQLException, ClassNotFoundException;
}
