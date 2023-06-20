package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import java.sql.SQLException;

public interface AdminDashBoardBO extends SuperBO {
     String getTodayEmployeeAttendanceCount() throws SQLException, ClassNotFoundException;
     String getItemLimitCount() throws SQLException, ClassNotFoundException;
}
