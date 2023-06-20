package lk.ijse.MobileShop.dao.custom.impl;

import lk.ijse.MobileShop.dao.custom.SupplierOrderDAO;
import lk.ijse.MobileShop.dao.custom.impl.utill.SQLUtill;
import lk.ijse.MobileShop.entity.SupplierOrder;
import lk.ijse.MobileShop.dao.custom.impl.utill.DateTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDAOImpl implements SupplierOrderDAO {
    @Override
    public boolean save(SupplierOrder supplierOrder) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO supplier_order VALUES (?,?,?,?,?)",
                supplierOrder.getSupplier_order_id(),
                supplierOrder.getSupplier_id(),
                supplierOrder.getTime(),
                supplierOrder.getDate(),
                supplierOrder.getPayment()
        );
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();
        ResultSet set = SQLUtill.execute("SELECT supplier_order_id FROM supplier_order ORDER BY LENGTH(supplier_order_id),supplier_order_id");
        while (set.next()) {
            ids.add(set.getString(1));
        }
        return ids;
    }

    @Override
    public ArrayList<String> getSearchId(String id) throws SQLException, ClassNotFoundException {
        String SearchId = id + "%";
        ResultSet set = SQLUtill.execute("SELECT supplier_order_id FROM supplier_order where supplier_order_id LIKE ? or supplier_id LIKE ? ", SearchId, SearchId);
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public SupplierOrder getData(String id) throws SQLException, ClassNotFoundException {
        SupplierOrder supplierOrder = new SupplierOrder();
        ResultSet set = SQLUtill.execute("SELECT * FROM supplier_order WHERE supplier_order_id = ?", id);
        while (set.next()) {
            supplierOrder.setSupplier_id(set.getString(2));
            supplierOrder.setSupplier_order_id(set.getString(1));
            supplierOrder.setDate(set.getString(4));
            supplierOrder.setTime(set.getString(3));
            supplierOrder.setPayment(Double.parseDouble(set.getString(5)));
        }
        return supplierOrder;
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
    public boolean update(SupplierOrder supplierOrder) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String getAllTodayOrderCount() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT COUNT(supplier_order_id) FROM supplier_order WHERE date=?", DateTimeUtil.dateNow());
        if (set.next()) {
            return set.getString(1);
        }
        return null;
    }

    @Override
    public ArrayList<String> getSupplierOrderYear() throws SQLException, ClassNotFoundException {
        ArrayList<String> year=new ArrayList<>();
        ResultSet set= SQLUtill.execute("SELECT date from supplier_order");
        while (set.next()){
            String dateNow = set.getString(1);
            String[] split = dateNow.split("-");
            String date=split[0];
            boolean isNotDuplicate=true;
            for (int i = 0; i < year.size(); i++) {

                if (year.get(i).equals(date)){
                    isNotDuplicate=false;
                }
            }
            if (isNotDuplicate){
                year.add(date);
            }
        }
        return year;
    }

    @Override
    public double getPayment(String processDate, String month) throws SQLException, ClassNotFoundException {
        String dateNow = DateTimeUtil.dateNow();
        String[] split = dateNow.split("-");

        String processMonth=null;
        if (month.length()==2){
            processMonth=month;
        }else {
            processMonth="0"+month;
        }

        ResultSet set=  SQLUtill.execute("SELECT  payment FROM supplier_order WHERE date LIKE ?", split[0]+"-"+processMonth+"-"+processDate);

        while (set.next()){
            return Double.parseDouble(set.getString(1));
        }
        return 0;
    }
}
