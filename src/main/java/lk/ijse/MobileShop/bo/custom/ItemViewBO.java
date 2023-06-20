package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.ItemDTO;

import java.sql.SQLException;

public interface ItemViewBO extends SuperBO {
    ItemDTO gataItemData(String id) throws SQLException, ClassNotFoundException;
}
