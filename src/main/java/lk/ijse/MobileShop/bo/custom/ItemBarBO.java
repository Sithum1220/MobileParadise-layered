package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.ItemDTO;

import java.sql.SQLException;

public interface ItemBarBO extends SuperBO {
    ItemDTO getItemData(String id) throws SQLException, ClassNotFoundException;
}
