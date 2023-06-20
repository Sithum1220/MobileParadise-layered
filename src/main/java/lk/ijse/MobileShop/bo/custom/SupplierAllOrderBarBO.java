package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.SupplierOrderDTO;

import java.sql.SQLException;

public interface SupplierAllOrderBarBO extends SuperBO {
    SupplierOrderDTO getAllSupplierOrderData(String id) throws SQLException, ClassNotFoundException;
}
