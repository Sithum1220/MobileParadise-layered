package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.EmployeeBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.EmployeeAttendaceDAO;
import lk.ijse.MobileShop.dao.custom.EmployeeDAO;
import lk.ijse.MobileShop.dao.custom.impl.EmployeeAttendanceDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.EmployeeDAOImpl;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE);
    EmployeeAttendaceDAO employeeAttendaceDAO =DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE_ATTENDANCE);

    @Override
    public ArrayList<String> getSearchEmployeeId(String text) throws SQLException, ClassNotFoundException {
        return employeeDAO.getSearchId(text);
    }

    @Override
    public String getAllEmployeeCount() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAllCount();
    }

    @Override
    public ArrayList<String> getAllEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAllId();
    }

    @Override
    public ArrayList<String> getAllAttendanceEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeAttendaceDAO.getAllId();
    }

    @Override
    public ArrayList<String> getSearchAttendanceEmployeeId(String text) throws SQLException, ClassNotFoundException {
        return employeeAttendaceDAO.getSearchId(text);
    }

    @Override
    public String getAllTodayAttendanceCount() throws SQLException, ClassNotFoundException {
        return employeeAttendaceDAO.getAllTodayAttendanceCount();
    }
}
