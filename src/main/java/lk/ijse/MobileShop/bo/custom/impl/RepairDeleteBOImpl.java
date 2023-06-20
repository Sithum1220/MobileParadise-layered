package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.RepairDeleteBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.CustomerDAO;
import lk.ijse.MobileShop.dao.custom.RepairDAO;
import lk.ijse.MobileShop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.RepairDAOImpl;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.dto.RepairDTO;
import lk.ijse.MobileShop.entity.Customer;
import lk.ijse.MobileShop.entity.Repair;

import java.sql.SQLException;

public class RepairDeleteBOImpl implements RepairDeleteBO {
    CustomerDAO customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    RepairDAO repairDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.REPAIR);


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
    public boolean deleteRepair(String id) throws SQLException, ClassNotFoundException {
        return repairDAO.delete(id);
    }

    @Override
    public RepairDTO getAllRepairData(String id) throws SQLException, ClassNotFoundException {
        Repair repair = repairDAO.getData(id);
        return new RepairDTO(
                repair.getRepair_id(),
                repair.getCustomer_id(),
                repair.getStatus(),
                repair.getImei_number(),
                repair.getModel_number(),
                repair.getError_type(),
                repair.getGiven_date(),
                repair.getReturn_give_date()
        );
    }
}
