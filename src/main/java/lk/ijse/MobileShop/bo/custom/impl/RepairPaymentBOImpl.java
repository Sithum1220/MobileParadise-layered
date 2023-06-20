package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.RepairPaymentBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.CustomerOrderDAO;
import lk.ijse.MobileShop.dao.custom.RepairDAO;
import lk.ijse.MobileShop.dao.custom.impl.CustomerOrderDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.RepairDAOImpl;
import lk.ijse.MobileShop.db.DBConnection;
import lk.ijse.MobileShop.dto.CustomerOrderDTO;
import lk.ijse.MobileShop.entity.CustomerOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairPaymentBOImpl implements RepairPaymentBO {
    CustomerOrderDAO customerOrderDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER_ORDER);
    RepairDAO repairDAO =DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.REPAIR);

    @Override
    public ArrayList<String> getAllCustomerOrderId() throws SQLException, ClassNotFoundException {
        return customerOrderDAO.getAllId();
    }

    @Override
    public String getRepairWarrentyStatus(String id) throws SQLException, ClassNotFoundException {
        return repairDAO.getStatus(id);
    }

    @Override
    public boolean repairPayment(CustomerOrderDTO customerOrderDTO) throws SQLException {
        System.out.println(customerOrderDTO);
        CustomerOrderDAO customerOrderDAO = new CustomerOrderDAOImpl();
        RepairDAO repairDAO = new RepairDAOImpl();

        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            if (customerOrderDAO.saveRepairOrder(new CustomerOrder(
                    customerOrderDTO.getCustomer_order_id(),
                    customerOrderDTO.getCustomer_id(),
                    customerOrderDTO.getTime(),
                    customerOrderDTO.getDate(),
                    Double.parseDouble(customerOrderDTO.getPayment()),
                    customerOrderDTO.getRepair_id()))) {
                System.out.println("Order Ok");
                if (repairDAO.updateReturnGiveDateByRepair(new CustomerOrder(
                        customerOrderDTO.getCustomer_id(),
                        customerOrderDTO.getCustomer_id(),
                        customerOrderDTO.getTime(),
                        customerOrderDTO.getDate(),
                        Double.parseDouble(customerOrderDTO.getPayment()),
                        customerOrderDTO.getRepair_id()
                ))) {
                    System.out.println("update");
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                }
            } else {
                connection.rollback();
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            connection.setAutoCommit(true);
        }
        return false;
    }
}
