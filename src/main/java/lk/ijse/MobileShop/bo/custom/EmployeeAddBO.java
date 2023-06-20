package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.EmployeeDTO;
import lk.ijse.MobileShop.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeAddBO extends SuperBO {
    boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;
    boolean saveUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    String getAllEmployeeCount() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllEmployeeId() throws SQLException, ClassNotFoundException;
}
