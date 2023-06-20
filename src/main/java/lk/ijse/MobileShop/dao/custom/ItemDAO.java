package lk.ijse.MobileShop.dao.custom;

import lk.ijse.MobileShop.dao.CrudDAO;
import lk.ijse.MobileShop.dto.*;
import lk.ijse.MobileShop.entity.CustomerOrder;
import lk.ijse.MobileShop.entity.CustomerOrderDetails;
import lk.ijse.MobileShop.entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item,String> {
     String getItemLimitCount() throws SQLException, ClassNotFoundException;
     boolean updateItemBySupplierOrder(ArrayList<CustomEntityDTO> list) throws SQLException, ClassNotFoundException;

     boolean updateItemByCustomerOrder(ArrayList<CustomerOrderDetails> list, CustomerOrder customerOrder) throws SQLException, ClassNotFoundException;
}
