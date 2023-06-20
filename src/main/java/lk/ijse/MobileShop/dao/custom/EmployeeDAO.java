package lk.ijse.MobileShop.dao.custom;

import lk.ijse.MobileShop.dao.CrudDAO;
import lk.ijse.MobileShop.dto.EmployeeDTO;
import lk.ijse.MobileShop.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee,String> {

     String getAllAdminCount() throws SQLException, ClassNotFoundException;
}
