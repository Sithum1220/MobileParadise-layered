package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.SupplierOrderBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.ItemDAO;
import lk.ijse.MobileShop.dao.custom.SupplierDAO;
import lk.ijse.MobileShop.dao.custom.SupplierOrderDAO;
import lk.ijse.MobileShop.dao.custom.SupplierOrderDetailsDAO;
import lk.ijse.MobileShop.dao.custom.impl.ItemDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.SupplierOrderDAOImpl;
import lk.ijse.MobileShop.dao.custom.impl.SupplierOrderDetailsDAOImpl;
import lk.ijse.MobileShop.db.DBConnection;
import lk.ijse.MobileShop.dto.CustomEntityDTO;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.dto.SupplierDTO;
import lk.ijse.MobileShop.dto.SupplierOrderDTO;
import lk.ijse.MobileShop.entity.Item;
import lk.ijse.MobileShop.entity.Supplier;
import lk.ijse.MobileShop.entity.SupplierOrder;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderBOImpl implements SupplierOrderBO {
    ItemDAO itemDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    SupplierDAO supplierDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);
    SupplierOrderDAO supplierOrderDAO =DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER_ORDER);
    SupplierOrderDetailsDAO supplierOrderDetailsDAO =DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER_ORDER_DETAILS);

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
    public SupplierDTO getAllSupplierData(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.getData(id);
        return new SupplierDTO(
                supplier.getSupplier_id(),
                supplier.getCompany_name(),
                supplier.getContact_number(),
                supplier.getEmail(),
                supplier.getLocation()
        );
    }

    @Override
    public ArrayList<String> getAllItemId() throws SQLException, ClassNotFoundException {
        return itemDAO.getAllId();
    }

    @Override
    public ArrayList<String> getAllSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAllId();
    }

    @Override
    public ArrayList<String> getAllSupplierOrderId() throws SQLException, ClassNotFoundException {
        return supplierOrderDAO.getAllId();
    }

    @Override
    public CustomEntityDTO getUnitPrice(String id) throws SQLException, ClassNotFoundException {
        return supplierOrderDetailsDAO.getUnit_price(id);
    }

    @Override
    public boolean placeSupplierOrder(ArrayList<CustomEntityDTO> list, SupplierOrderDTO supplierOrderDTO) throws SQLException {
        Connection connection=null;
        System.out.println(supplierOrderDTO.getSupplier_order_id()+" "+supplierOrderDTO.getSupplier_id()+" "+supplierOrderDTO.getTime()+" "+supplierOrderDTO.getDate()+" "+Double.parseDouble(supplierOrderDTO.getPayment()));
        System.out.println(list.get(0).getItemId()+" "+list.get(0).getItemQTY()+" "+list.get(0).getItemPrice()+"rrr");
        try {
            connection= DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (supplierOrderDAO.save(new SupplierOrder(
                    supplierOrderDTO.getSupplier_order_id(),
                    supplierOrderDTO.getSupplier_id(),
                    supplierOrderDTO.getTime(),
                    supplierOrderDTO.getDate(),
                    Double.parseDouble(supplierOrderDTO.getPayment())
            ))){
                System.out.println("oq");
                if (supplierOrderDetailsDAO.saveSupplierOrderDetails(list,new SupplierOrder(
                        supplierOrderDTO.getSupplier_order_id(),
                        supplierOrderDTO.getSupplier_id(),
                        supplierOrderDTO.getTime(),
                        supplierOrderDTO.getDate(),
                        Double.parseDouble(supplierOrderDTO.getPayment())
                ))){
                    System.out.println("supplier order details");
                    if (itemDAO.updateItemBySupplierOrder(list)){
                        return true;
                    }else {
                        connection.rollback();
                        return false;
                    }
                }else {
                    connection.rollback();
                }
            }else {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            connection.commit();
            connection.setAutoCommit(true);

        }
        return false;
    }
}
