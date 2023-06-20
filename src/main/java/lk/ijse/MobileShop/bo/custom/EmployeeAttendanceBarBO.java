package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.CustomEntityDTO;

import java.sql.SQLException;

public interface EmployeeAttendanceBarBO extends SuperBO {

    CustomEntityDTO getEmployeeAttendanceData(String id) throws SQLException, ClassNotFoundException;
}
