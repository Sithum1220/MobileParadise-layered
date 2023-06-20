package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.EmployeeDTO;
import lk.ijse.MobileShop.dto.UserDTO;

import java.sql.SQLException;

public interface EmployeeViewBO extends SuperBO {
    EmployeeDTO getEmployeeData(String id) throws SQLException, ClassNotFoundException;
    UserDTO getUserData(String id) throws SQLException, ClassNotFoundException;
}
