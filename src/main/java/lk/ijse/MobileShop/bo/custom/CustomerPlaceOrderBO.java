package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dao.SuperDAO;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.dto.CustomerOrderDetailsDTO;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.dto.CustomerOrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerPlaceOrderBO extends SuperBO {

    CustomerDTO getCustomerData(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCustomerId() throws SQLException, ClassNotFoundException;
    boolean placeCustomerOrder(ArrayList<CustomerOrderDetailsDTO> list, CustomerOrderDTO customerOrderDTO) throws SQLException;

    ItemDTO getItemData(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCustomerOrderId() throws SQLException, ClassNotFoundException;
}
