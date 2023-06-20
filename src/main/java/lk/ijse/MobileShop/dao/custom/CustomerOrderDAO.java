package lk.ijse.MobileShop.dao.custom;

import lk.ijse.MobileShop.dao.CrudDAO;
import lk.ijse.MobileShop.dto.CustomerOrderDTO;
import lk.ijse.MobileShop.entity.CustomerOrder;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerOrderDAO extends CrudDAO<CustomerOrder,String> {
    CustomerOrder getOrderDate(String id) throws SQLException, ClassNotFoundException;

    boolean saveRepairOrder(CustomerOrder customerOrder)throws SQLException, ClassNotFoundException;

    ArrayList<String> getMonthlyAllOrderId() throws SQLException, ClassNotFoundException;

    public String getTodayOrdersCount() throws SQLException, ClassNotFoundException;

   double getCustomerOrderPayment(int  i,String month) throws SQLException, ClassNotFoundException;
}
