package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemAddBO extends SuperBO {
    boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItem() throws SQLException, ClassNotFoundException;
}
