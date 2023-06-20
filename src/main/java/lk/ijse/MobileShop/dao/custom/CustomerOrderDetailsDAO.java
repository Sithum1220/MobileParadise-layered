package lk.ijse.MobileShop.dao.custom;

import lk.ijse.MobileShop.dao.CrudDAO;
import lk.ijse.MobileShop.dto.CustomerOrderDetailsDTO;
import lk.ijse.MobileShop.entity.CustomerOrderDetails;

import java.util.ArrayList;

public interface CustomerOrderDetailsDAO extends CrudDAO<ArrayList<CustomerOrderDetails>,String> {
}
