package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.CustomerOrderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReportViewBO extends SuperBO {
    ArrayList<String> getAllEmployeeId() throws SQLException, ClassNotFoundException;
    String getEmployeeAttendanceCountThisMonth(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException;
    ArrayList<CustomerOrderDetailsDTO> getAllCustomerOrderDetailsData(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getMonthlyAllOrderId() throws SQLException, ClassNotFoundException;
    double getCustomerOrderPayment(int i,String month) throws SQLException, ClassNotFoundException;
    ArrayList<String> getSupplierOrderYear() throws SQLException, ClassNotFoundException;
    double getSupplerOrderPayment(String processDate,String month) throws SQLException, ClassNotFoundException;
}
