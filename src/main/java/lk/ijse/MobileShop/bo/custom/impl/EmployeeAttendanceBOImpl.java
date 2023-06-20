package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.EmployeeAttendanceBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.EmployeeAttendaceDAO;
import lk.ijse.MobileShop.dao.custom.impl.EmployeeAttendanceDAOImpl;
import lk.ijse.MobileShop.dto.EmployeeAttendanceDTO;
import lk.ijse.MobileShop.entity.EmployeeAttendance;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeAttendanceBOImpl implements EmployeeAttendanceBO {
    EmployeeAttendaceDAO employeeAttendaceDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE_ATTENDANCE);

    @Override
    public boolean employeeExist(String id) throws SQLException, ClassNotFoundException {
        return employeeAttendaceDAO.isEmployeeExsist(id);
    }

    @Override
    public boolean employeeTodayExist(String id) throws SQLException, ClassNotFoundException {
       return employeeAttendaceDAO.isEmployeeTodayExsist(id);
    }

    @Override
    public boolean saveEmployeeAttendance(EmployeeAttendanceDTO employeeAttendanceDTO) throws SQLException, ClassNotFoundException {
        return employeeAttendaceDAO.save(new EmployeeAttendance(
                employeeAttendanceDTO.getId(),
                employeeAttendanceDTO.getTime(),
                employeeAttendanceDTO.getDate()
        ));
    }

    @Override
    public ArrayList<String> getSearchId(String text) throws SQLException, ClassNotFoundException {
        return employeeAttendaceDAO.getSearchId(text);
    }

    @Override
    public ArrayList<String> getAllAttendanceEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeAttendaceDAO.getAllId();
    }

    @Override
    public String getAllTodayEmployeeAttendanceCount() throws SQLException, ClassNotFoundException {
        return employeeAttendaceDAO.getAllTodayAttendanceCount();
    }
}
