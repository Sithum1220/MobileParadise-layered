package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.SupplierAllOrderBarBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.SupplierOrderDAO;
import lk.ijse.MobileShop.dao.custom.impl.SupplierOrderDAOImpl;
import lk.ijse.MobileShop.dto.SupplierOrderDTO;
import lk.ijse.MobileShop.entity.SupplierOrder;

import java.sql.SQLException;

public class SupplierAllOrderBarBOImpl implements SupplierAllOrderBarBO {
    SupplierOrderDAO supplierOrderDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER_ORDER);

    @Override
    public SupplierOrderDTO getAllSupplierOrderData(String id) throws SQLException, ClassNotFoundException {
        SupplierOrder supplierOrder = supplierOrderDAO.getData(id);
        return new SupplierOrderDTO(
                supplierOrder.getSupplier_order_id(),
                supplierOrder.getSupplier_id(),
                supplierOrder.getTime(),
                supplierOrder.getDate(),
                supplierOrder.getDate()
        );
    }
}
