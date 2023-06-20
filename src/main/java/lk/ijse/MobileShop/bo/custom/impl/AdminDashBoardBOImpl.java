package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.AdminDashBoardBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.EmployeeAttendaceDAO;
import lk.ijse.MobileShop.dao.custom.ItemDAO;
import lk.ijse.MobileShop.dao.custom.impl.EmployeeAttendanceDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.ItemDAOImpl;

import java.sql.SQLException;

public class AdminDashBoardBOImpl implements AdminDashBoardBO {
    EmployeeAttendaceDAO employeeAttendaceDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE_ATTENDANCE);
    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public String getTodayEmployeeAttendanceCount() throws SQLException, ClassNotFoundException {
      return employeeAttendaceDAO.getAllTodayAttendanceCount();
    }

    @Override
    public String getItemLimitCount() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemLimitCount();
    }
}
