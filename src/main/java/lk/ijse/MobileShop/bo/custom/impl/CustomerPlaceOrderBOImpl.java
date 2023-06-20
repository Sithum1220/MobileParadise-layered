package lk.ijse.MobileShop.bo.custom.impl;

import com.google.protobuf.StringValue;
import lk.ijse.MobileShop.bo.custom.CustomerPlaceOrderBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.CustomerDAO;
import lk.ijse.MobileShop.dao.custom.CustomerOrderDAO;
import lk.ijse.MobileShop.dao.custom.CustomerOrderDetailsDAO;
import lk.ijse.MobileShop.dao.custom.ItemDAO;
import lk.ijse.MobileShop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.CustomerOrderDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.CustomerOrderDetailsDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.MobileShop.db.DBConnection;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.dto.CustomerOrderDetailsDTO;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.dto.CustomerOrderDTO;
import lk.ijse.MobileShop.entity.Customer;
import lk.ijse.MobileShop.entity.CustomerOrder;
import lk.ijse.MobileShop.entity.CustomerOrderDetails;
import lk.ijse.MobileShop.entity.Item;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerPlaceOrderBOImpl implements CustomerPlaceOrderBO {
    CustomerDAO customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    CustomerOrderDAO customerOrderDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER_ORDER);
    CustomerOrderDetailsDAO customerOrderDetailsDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER_ORDER_DETAILS);
    @Override
    public CustomerDTO getCustomerData(String id) throws SQLException, ClassNotFoundException {
       Customer customer =  customerDAO.getData(id);
       return new CustomerDTO(
               customer.getCustomer_id(),
               customer.getFirst_name(),
               customer.getLast_name(),
               customer.getStreet(),
               customer.getCity(),
               customer.getLane(),
               customer.getDate(),
               customer.getNic(),
               customer.getEmail(),
               customer.getContact_number()
       );
    }

    @Override
    public ArrayList<String> getAllCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllId();
    }

    @Override
    public boolean placeCustomerOrder(ArrayList<CustomerOrderDetailsDTO> list, CustomerOrderDTO customerOrderDTO) throws SQLException {

        ArrayList<CustomerOrderDetails> customerOrderDetails = new ArrayList<>();
        for (CustomerOrderDetailsDTO customerOrderDetailsDTO : list){
            customerOrderDetails.add(new CustomerOrderDetails(
                    customerOrderDetailsDTO.getItem_id(),
                    customerOrderDetailsDTO.getCustomer_order_id(),
                    Integer.parseInt(customerOrderDetailsDTO.getQty()),
                    Double.parseDouble(customerOrderDetailsDTO.getPrice())
                    ));
        }
        Connection connection = null;
        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (customerOrderDAO.save(new CustomerOrder(
                    customerOrderDTO.getCustomer_order_id(),
                    customerOrderDTO.getCustomer_id(),
                    customerOrderDTO.getTime(),
                    customerOrderDTO.getDate(),
                    Double.parseDouble(customerOrderDTO.getPayment()),
                    null
            ))) {
                if (customerOrderDetailsDAO.save(customerOrderDetails)) {
                    if (itemDAO.updateItemByCustomerOrder(customerOrderDetails, new CustomerOrder(
                            customerOrderDTO.getCustomer_order_id(),
                            customerOrderDTO.getCustomer_id(),
                            customerOrderDTO.getTime(),
                            customerOrderDTO.getDate(),
                            Double.parseDouble(customerOrderDTO.getPayment()),
                            customerOrderDTO.getRepair_id()
                    ))) {
                        return true;
                    } else {
                        connection.rollback();
                        return false;
                    }
                } else {
                    connection.rollback();
                    return false;
                }
            } else {
                connection.rollback();
                return false;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            connection.commit();
            connection.setAutoCommit(true);
        }
    }

    @Override
    public ItemDTO getItemData(String id) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.getData(id);
        return new ItemDTO(
                item.getItem_code(),
                item.getItem_name(),
                item.getCategory(),
                item.getUnit_price(),
                item.getQty(),
                item.getBrand(),
                item.getDescription(),
                String.valueOf(item.getWarranty_month())
        );
    }

    @Override
    public ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllId();
    }

    @Override
    public ArrayList<String> getAllCustomerOrderId() throws SQLException, ClassNotFoundException {
        return customerOrderDAO.getAllId();
    }
}
