package lk.ijse.MobileShop.dao.custom.impl;

import lk.ijse.MobileShop.dao.custom.SupplierOrderDetailsDAO;
import lk.ijse.MobileShop.dao.custom.impl.utill.SQLUtill;
import lk.ijse.MobileShop.dto.CustomEntityDTO;
import lk.ijse.MobileShop.dto.SuplierOrderDetailsDTO;
import lk.ijse.MobileShop.dto.SupplierOrderDTO;
import lk.ijse.MobileShop.entity.SupplierOrder;
import lk.ijse.MobileShop.entity.SupplierOrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDetailsDAOImpl implements SupplierOrderDetailsDAO {
    @Override
    public boolean save(SupplierOrderDetails suplierOrderDetails) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getSearchId(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public SupplierOrderDetails getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM supplier_order_details where supplier_order_id=?", id);
        SupplierOrderDetails supplierOrderDetails =new SupplierOrderDetails();
        while (set.next()) {
            supplierOrderDetails.setItem_code(set.getString(1));
            supplierOrderDetails.setSupplier_order_id(set.getString(2));
            supplierOrderDetails.setQuantity(Integer.parseInt(set.getString(3)));
            supplierOrderDetails.setUnit_price(Double.parseDouble(set.getString(4)));
        }
        return supplierOrderDetails;
    }

    @Override
    public String getAllCount() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(SupplierOrderDetails suplierOrderDetails) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean saveSupplierOrderDetails(ArrayList<CustomEntityDTO> list, SupplierOrder supplierOrder) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getItemId()+" "+supplierOrder.getSupplier_order_id()+" "+list.get(i).getItemQTY()+" "+list.get(i).getItemPrice());
            if (SQLUtill.execute("INSERT INTO supplier_order_details VALUES (?,?,?,?)",
                    list.get(i).getItemId(),
                    supplierOrder.getSupplier_order_id(),
                    list.get(i).getItemQTY(),
                    list.get(i).getItemPrice()
            )){

            }else {
                return false;
            }
        }
        return true;
    }

    @Override
    public CustomEntityDTO getUnit_price(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT unit_price FROM supplier_order_details where item_code=?",id);
        CustomEntityDTO entityDTO = new CustomEntityDTO();
        while (set.next()){
            entityDTO.setItemPrice(set.getString(1));
        }
        return entityDTO;
    }
}
