package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.SupplierAddBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.SupplierDAO;
import lk.ijse.MobileShop.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.MobileShop.dto.SupplierDTO;
import lk.ijse.MobileShop.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierAddBOImpl implements SupplierAddBO {
    SupplierDAO supplierDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);

    @Override
    public boolean supplierSave(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(
                supplierDTO.getSupplier_id(),
                supplierDTO.getCompany_name(),
                supplierDTO.getContact_number(),
                supplierDTO.getEmail(),
                supplierDTO.getLocation()
        ));
    }

    @Override
    public ArrayList<String> getAllSupplierId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getAllId();
    }
}
