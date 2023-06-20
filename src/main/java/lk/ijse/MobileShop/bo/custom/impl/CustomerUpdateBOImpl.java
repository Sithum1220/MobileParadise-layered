package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.CustomerUpdateBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.CustomerDAO;
import lk.ijse.MobileShop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.entity.Customer;

import java.sql.SQLException;

public class CustomerUpdateBOImpl implements CustomerUpdateBO {
    CustomerDAO customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public CustomerDTO getCustomerData(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.getData(id);
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
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(
                customerDTO.getCustomer_id(),
                customerDTO.getFirst_name(),
                customerDTO.getLast_name(),
                customerDTO.getStreet(),
                customerDTO.getCity(),
                customerDTO.getLane(),
                customerDTO.getDate(),
                customerDTO.getNic(),
                customerDTO.getEmail(),
                customerDTO.getContact_number()
        ));
    }
}
