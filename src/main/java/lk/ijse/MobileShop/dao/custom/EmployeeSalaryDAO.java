package lk.ijse.MobileShop.dao.custom;

import lk.ijse.MobileShop.dao.CrudDAO;
import lk.ijse.MobileShop.dto.SalaryDTO;
import lk.ijse.MobileShop.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeSalaryDAO extends CrudDAO<Salary,String>{
    ArrayList<String> getAllEmpId() throws SQLException, ClassNotFoundException;

}
