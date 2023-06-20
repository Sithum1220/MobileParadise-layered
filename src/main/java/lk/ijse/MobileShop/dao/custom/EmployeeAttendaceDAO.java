package lk.ijse.MobileShop.dao.custom;

import lk.ijse.MobileShop.dao.CrudDAO;
import lk.ijse.MobileShop.dto.EmployeeAttendanceDTO;
import lk.ijse.MobileShop.entity.EmployeeAttendance;

import java.sql.SQLException;

public interface EmployeeAttendaceDAO extends CrudDAO<EmployeeAttendance, String> {

    boolean isEmployeeExsist(String id) throws SQLException, ClassNotFoundException;

    boolean isEmployeeTodayExsist (String id) throws SQLException, ClassNotFoundException;
    String getAttendanceCountThisMonth(String employee_id) throws SQLException, ClassNotFoundException;
    String getAllTodayAttendanceCount() throws SQLException, ClassNotFoundException;
}
