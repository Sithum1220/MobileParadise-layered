package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.CashierDashboardBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.CustomerOrderDAO;
import lk.ijse.MobileShop.dao.custom.EmployeeAttendaceDAO;
import lk.ijse.MobileShop.dao.custom.ItemDAO;
import lk.ijse.MobileShop.dao.custom.impl.CustomerOrderDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.EmployeeAttendanceDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.ItemDAOImpl;

import java.sql.SQLException;

public class CashierDashboardBOImpl implements CashierDashboardBO {
    EmployeeAttendaceDAO employeeAttendaceDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE_ATTENDANCE);
    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    CustomerOrderDAO customerOrderDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER_ORDER);

    @Override
    public String getTodayEmployeeAttendanceCount() throws SQLException, ClassNotFoundException {
        String count = employeeAttendaceDAO.getAllTodayAttendanceCount();
        return count;
    }

    @Override
    public String getItemLimitCount() throws SQLException, ClassNotFoundException {
        String count = itemDAO.getItemLimitCount();
        return count;
    }

    @Override
    public String getTodayOrdersCount() throws SQLException, ClassNotFoundException {
        String count = customerOrderDAO.getTodayOrdersCount();
        return count;
    }
}
