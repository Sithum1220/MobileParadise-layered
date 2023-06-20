package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.EmployeeDTO;
import lk.ijse.MobileShop.dto.UserDTO;

import java.sql.SQLException;

public interface EmployeeDeleteBO extends SuperBO {
    String getAdminCount() throws SQLException, ClassNotFoundException;
    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;
    EmployeeDTO getEmployeeData(String id) throws SQLException, ClassNotFoundException;
    UserDTO getUsersData(String id) throws SQLException, ClassNotFoundException;
    boolean deleteUsers(String id) throws SQLException, ClassNotFoundException;
}
