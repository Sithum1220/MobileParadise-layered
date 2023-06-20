package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.CustomerAddBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.CustomerDAO;
import lk.ijse.MobileShop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerAddBOImpl implements CustomerAddBO {

    CustomerDAO customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public boolean saveCustomers(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
       return customerDAO.save(new Customer(
               customerDTO.getCustomer_id(),
               customerDTO.getFirst_name(),
               customerDTO.getLast_name(),
               customerDTO.getStreet(),
               customerDTO.getCity(),
               customerDTO.getLane(),
               customerDTO.getDate(),
               customerDTO.getNic(),
               customerDTO.getEmail(),
               customerDTO.getContact_number()));
    }

    @Override
    public ArrayList<String> getAllCustomersId() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllId();
    }
}
