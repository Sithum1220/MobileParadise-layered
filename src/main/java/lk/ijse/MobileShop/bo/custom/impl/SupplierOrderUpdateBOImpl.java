package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.SupplierOrderUpdateBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.ItemDAO;
import lk.ijse.MobileShop.dao.custom.SupplierOrderDAO;
import lk.ijse.MobileShop.dao.custom.SupplierOrderDetailsDAO;
import lk.ijse.MobileShop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.SupplierOrderDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.SupplierOrderDetailsDAOImpl;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.dto.SuplierOrderDetailsDTO;
import lk.ijse.MobileShop.dto.SupplierOrderDTO;
import lk.ijse.MobileShop.entity.Item;
import lk.ijse.MobileShop.entity.SupplierOrder;
import lk.ijse.MobileShop.entity.SupplierOrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderUpdateBOImpl implements SupplierOrderUpdateBO {
    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    SupplierOrderDAO supplierOrderDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER_ORDER);
    SupplierOrderDetailsDAO supplierOrderDetailsDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER_ORDER_DETAILS);

    @Override
    public ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllId();
    }

    @Override
    public ItemDTO getAllItemData(String id) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.getData(id);
        return new ItemDTO(
                item.getItem_code(),
                item.getItem_name(),
                item.getCategory(),
                item.getUnit_price(),
                item.getQty(),
                item.getBrand(),
                item.getDescription(),
                String.valueOf(item.getWarranty_month())
        );
    }

    @Override
    public SupplierOrderDTO getAllSupplierOrderData(String id) throws SQLException, ClassNotFoundException {
        SupplierOrder supplierOrder = supplierOrderDAO.getData(id);
        return new SupplierOrderDTO(
                supplierOrder.getSupplier_order_id(),
                supplierOrder.getSupplier_id(),
                supplierOrder.getTime(),
                supplierOrder.getDate(),
                String.valueOf(supplierOrder.getPayment())
        );
    }

    @Override
    public SuplierOrderDetailsDTO getAllSupplierOrderDetailsData(String id) throws SQLException, ClassNotFoundException {
        SupplierOrderDetails supplierOrderDetails =  supplierOrderDetailsDAO.getData(id);
        return new SuplierOrderDetailsDTO(
                supplierOrderDetails.getItem_code(),
                supplierOrderDetails.getSupplier_order_id(),
                String.valueOf(supplierOrderDetails.getQuantity()),
                String.valueOf(supplierOrderDetails.getUnit_price())
        );
    }
}
