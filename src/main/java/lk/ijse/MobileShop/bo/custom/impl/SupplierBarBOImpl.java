package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.SupplierBarBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.SupplierDAO;
import lk.ijse.MobileShop.dao.custom.impl.SupplierDAOImpl;
import lk.ijse.MobileShop.dto.SupplierDTO;
import lk.ijse.MobileShop.entity.Supplier;

import java.sql.SQLException;

public class SupplierBarBOImpl implements SupplierBarBO {
    SupplierDAO supplierDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);


    @Override
    public SupplierDTO getAllSalaryData(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier =  supplierDAO.getData(id);
        return new SupplierDTO(
                supplier.getSupplier_id(),
                supplier.getCompany_name(),
                supplier.getContact_number(),
                supplier.getEmail(),
                supplier.getLocation()
        );
    }
}
