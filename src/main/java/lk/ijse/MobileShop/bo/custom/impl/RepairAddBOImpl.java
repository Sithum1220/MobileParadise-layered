package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.ItemBO;
import lk.ijse.MobileShop.bo.custom.RepairAddBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.CustomerDAO;
import lk.ijse.MobileShop.dao.custom.CustomerOrderDAO;
import lk.ijse.MobileShop.dao.custom.ItemDAO;
import lk.ijse.MobileShop.dao.custom.RepairDAO;
import lk.ijse.MobileShop.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.CustomerOrderDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.RepairDAOImpl;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.dto.CustomerOrderDTO;
import lk.ijse.MobileShop.dto.RepairDTO;
import lk.ijse.MobileShop.entity.Customer;
import lk.ijse.MobileShop.entity.CustomerOrder;
import lk.ijse.MobileShop.entity.Item;
import lk.ijse.MobileShop.entity.Repair;

import java.sql.SQLException;
import java.util.ArrayList;

public class RepairAddBOImpl implements RepairAddBO {
    CustomerDAO customerDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    CustomerOrderDAO customerOrderDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER_ORDER);
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
    public ArrayList<String> getAllCustomerId() throws SQLException, ClassNotFoundException {
        return customerDAO.getAllId();
    }

    @Override
    public ItemDTO getAllItemData(String id) throws SQLException, ClassNotFoundException {
        Item item= itemDAO.getData(id);
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
    public CustomerOrderDTO getOrderDate(String id) throws SQLException, ClassNotFoundException {
        CustomerOrder customerOrder = customerOrderDAO.getOrderDate(id);
        return new CustomerOrderDTO(
                customerOrder.getCustomer_order_id(),
                customerOrder.getCustomer_id(),
                customerOrder.getTime(),
                customerOrder.getDate(),
                customerOrder.getRepair_id(),
                String.valueOf(customerOrder.getPayment())
        );
    }

    @Override
    public boolean saveRepair(RepairDTO repairDTO) throws SQLException, ClassNotFoundException {
        return repairDAO.save(new Repair(
                repairDTO.getRepair_id(),
                repairDTO.getCustomer_id(),
                repairDTO.getStatus(),
                repairDTO.getImei_number(),
                repairDTO.getModel_number(),
                repairDTO.getError_type(),
                repairDTO.getGiven_date(),
                repairDTO.getReturn_give_date()
        ));
    }

    @Override
    public ArrayList<String> getAllRepairId() throws SQLException, ClassNotFoundException {
        return repairDAO.getAllId();
    }
}
