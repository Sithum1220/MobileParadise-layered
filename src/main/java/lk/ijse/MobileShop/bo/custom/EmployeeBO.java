package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    ArrayList<String> getSearchEmployeeId(String text) throws SQLException, ClassNotFoundException;
    String getAllEmployeeCount() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllEmployeeId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllAttendanceEmployeeId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getSearchAttendanceEmployeeId(String text) throws SQLException, ClassNotFoundException;
    String getAllTodayAttendanceCount() throws SQLException, ClassNotFoundException;
}
