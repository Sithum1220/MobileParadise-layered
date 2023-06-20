package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.EmployeeAttendanceDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeAttendanceBO extends SuperBO {

    boolean employeeExist(String id) throws SQLException, ClassNotFoundException;
    boolean employeeTodayExist(String id) throws SQLException, ClassNotFoundException;
    boolean saveEmployeeAttendance(EmployeeAttendanceDTO employeeAttendanceDTO) throws SQLException, ClassNotFoundException;
    ArrayList<String> getSearchId(String text) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllAttendanceEmployeeId() throws SQLException, ClassNotFoundException;
    String getAllTodayEmployeeAttendanceCount() throws SQLException, ClassNotFoundException;
}
