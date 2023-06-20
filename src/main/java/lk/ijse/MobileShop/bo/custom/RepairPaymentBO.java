package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.CustomerOrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RepairPaymentBO extends SuperBO {
    ArrayList<String> getAllCustomerOrderId() throws SQLException, ClassNotFoundException;
    String getRepairWarrentyStatus(String id) throws SQLException, ClassNotFoundException;
    boolean repairPayment(CustomerOrderDTO customerOrderDTO) throws SQLException;
}
