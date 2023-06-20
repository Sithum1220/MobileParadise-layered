package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.EmployeeDTO;
import lk.ijse.MobileShop.dto.UserDTO;

import java.sql.SQLException;

public interface EmployeeUpdateBO extends SuperBO {
    EmployeeDTO getAllEmployeeData(String id) throws SQLException, ClassNotFoundException;
    UserDTO getAllUserData(String id) throws SQLException, ClassNotFoundException;
    boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
    boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;
}
