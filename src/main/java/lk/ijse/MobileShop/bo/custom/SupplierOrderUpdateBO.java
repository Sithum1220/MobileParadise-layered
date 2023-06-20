package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.dto.SuplierOrderDetailsDTO;
import lk.ijse.MobileShop.dto.SupplierOrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderUpdateBO extends SuperBO {
    ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException;
    ItemDTO getAllItemData(String id) throws SQLException, ClassNotFoundException;
    SupplierOrderDTO getAllSupplierOrderData(String id) throws SQLException, ClassNotFoundException;
    SuplierOrderDetailsDTO getAllSupplierOrderDetailsData(String id) throws SQLException, ClassNotFoundException;
}
