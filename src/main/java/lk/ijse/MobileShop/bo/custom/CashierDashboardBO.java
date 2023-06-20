package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dao.SuperDAO;

import java.sql.SQLException;

public interface CashierDashboardBO extends SuperBO {

    String getTodayEmployeeAttendanceCount() throws SQLException, ClassNotFoundException;
     String getItemLimitCount() throws SQLException, ClassNotFoundException;
     String getTodayOrdersCount() throws SQLException, ClassNotFoundException;
}
