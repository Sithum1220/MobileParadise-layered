package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.ReportViewBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.*;
import lk.ijse.MobileShop.dao.custom.impl.*;
import lk.ijse.MobileShop.dto.CustomerOrderDetailsDTO;
import lk.ijse.MobileShop.entity.CustomerOrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReportViewBOImpl implements ReportViewBO {
    EmployeeDAO employeeDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE);
    EmployeeAttendaceDAO employeeAttendaceDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE_ATTENDANCE);
    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    CustomerOrderDetailsDAO customerOrderDetailsDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER_ORDER_DETAILS);
    CustomerOrderDAO customerOrderDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER_ORDER);
    SupplierOrderDAO supplierOrderDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER_ORDER);


    @Override
    public ArrayList<String> getAllEmployeeId() throws SQLException, ClassNotFoundException {
        return employeeDAO.getAllId();
    }

    @Override
    public String getEmployeeAttendanceCountThisMonth(String id) throws SQLException, ClassNotFoundException {
        return employeeAttendaceDAO.getAttendanceCountThisMonth(id);
    }

    @Override
    public ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllId();
    }

    @Override
    public ArrayList<CustomerOrderDetailsDTO> getAllCustomerOrderDetailsData(String id) throws SQLException, ClassNotFoundException {
        ArrayList<CustomerOrderDetails> customerOrderDetails = customerOrderDetailsDAO.getData(id);
        ArrayList<CustomerOrderDetailsDTO> customerOrderDetailsDTOS = new ArrayList<>();
        for (CustomerOrderDetails orderDetails : customerOrderDetails) {
            customerOrderDetailsDTOS.add(new CustomerOrderDetailsDTO(
                    orderDetails.getItem_id(),
                    orderDetails.getCustomer_order_id(),
                    String.valueOf(orderDetails.getQty()),
                    String.valueOf(orderDetails.getPrice())
            ));
        }

        return customerOrderDetailsDTOS;
    }

    @Override
    public ArrayList<String> getMonthlyAllOrderId() throws SQLException, ClassNotFoundException {
        return customerOrderDAO.getMonthlyAllOrderId();
    }

    @Override
    public double getCustomerOrderPayment(int i, String month) throws SQLException, ClassNotFoundException {
        return customerOrderDAO.getCustomerOrderPayment(i, month);
    }

    @Override
    public ArrayList<String> getSupplierOrderYear() throws SQLException, ClassNotFoundException {
        return supplierOrderDAO.getSupplierOrderYear();
    }

    @Override
    public double getSupplerOrderPayment(String processDate, String month) throws SQLException, ClassNotFoundException {
        return supplierOrderDAO.getPayment(processDate, month);
    }
}
