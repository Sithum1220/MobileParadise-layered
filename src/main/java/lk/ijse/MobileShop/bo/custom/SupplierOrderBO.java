package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.CustomEntityDTO;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.dto.SupplierDTO;
import lk.ijse.MobileShop.dto.SupplierOrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderBO extends SuperBO {
    ItemDTO getAllItemData(String id) throws SQLException, ClassNotFoundException;
    SupplierDTO getAllSupplierData(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSupplierId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSupplierOrderId() throws SQLException, ClassNotFoundException;
    CustomEntityDTO getUnitPrice(String id) throws SQLException, ClassNotFoundException;
    boolean placeSupplierOrder(ArrayList<CustomEntityDTO> list, SupplierOrderDTO supplierOrderDTO) throws SQLException;
}
