package lk.ijse.MobileShop.dao.custom.impl;

import lk.ijse.MobileShop.dao.custom.SupplierDAO;
import lk.ijse.MobileShop.dao.custom.impl.utill.SQLUtill;
import lk.ijse.MobileShop.dto.SupplierDTO;
import lk.ijse.MobileShop.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO supplier VALUES (?,?,?,?,?)",
                supplier.getSupplier_id(),
                supplier.getCompany_name(),
                supplier.getContact_number(),
                supplier.getEmail(),
                supplier.getLocation()
        );
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT supplier_id FROM supplier ORDER BY LENGTH(supplier_id),supplier_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getSearchId(String id) throws SQLException, ClassNotFoundException {
        String SearchId = id + "%";
        ResultSet set = SQLUtill.execute("SELECT supplier_id FROM supplier where supplier.supplier_id LIKE ? or supplier.company_name LIKE ? or supplier.location LIKE ?", SearchId, SearchId, SearchId);
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public Supplier getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM supplier where supplier_id=?", id);
        Supplier supplier =new Supplier();
        if (set.next()) {
            supplier.setSupplier_id(set.getString(1));
            supplier.setCompany_name(set.getString(2));
            supplier.setContact_number(set.getString(3));
            supplier.setEmail(set.getString(4));
            supplier.setLocation(set.getString(5));
        }
        return supplier;
    }

    @Override
    public String getAllCount() throws SQLException, ClassNotFoundException {
       ResultSet set = SQLUtill.execute("SELECT COUNT(supplier_id) FROM supplier");
       if (set.next()){
           return set.getString(1);
       }
       return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM supplier WHERE supplier_id=?",id);
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE supplier SET company_name=? , contact_number=? ,email=? ,location=? WHERE supplier_id=?",
                supplier.getCompany_name(),
                supplier.getContact_number(),
                supplier.getEmail(),
                supplier.getLocation(),
                supplier.getSupplier_id()

        );
    }
}
