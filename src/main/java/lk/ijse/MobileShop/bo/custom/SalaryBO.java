package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.EmployeeDTO;
import lk.ijse.MobileShop.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
    ArrayList<String> getAllEmployeeId() throws SQLException, ClassNotFoundException;
    EmployeeDTO getAllEmployeeData(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllEmployeeIdBySalary() throws SQLException, ClassNotFoundException;
    boolean saveEmloyeeSalary(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException;
    SalaryDTO getAllSalaryData(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSalaryId() throws SQLException, ClassNotFoundException;
    String getAttendanceCountThisMonth(String id) throws SQLException, ClassNotFoundException;
}
