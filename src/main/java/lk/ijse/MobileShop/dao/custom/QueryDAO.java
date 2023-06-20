package lk.ijse.MobileShop.dao.custom;

import lk.ijse.MobileShop.dao.SuperDAO;
import lk.ijse.MobileShop.dto.CustomEntityDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {

    CustomEntityDTO getCustomerOrderData(String id) throws SQLException, ClassNotFoundException;

    ArrayList<String> searchCustomerOrdersData(String id) throws SQLException, ClassNotFoundException;

    CustomEntityDTO getEmployeeAttendanceData(String id) throws SQLException, ClassNotFoundException;

    String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException;
}
