package lk.ijse.MobileShop.dao.custom.impl;

import lk.ijse.MobileShop.dao.custom.CustomerOrderDAO;
import lk.ijse.MobileShop.dao.custom.impl.utill.SQLUtill;
import lk.ijse.MobileShop.entity.CustomerOrder;
import lk.ijse.MobileShop.dao.custom.impl.utill.DateTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderDAOImpl implements CustomerOrderDAO {
    @Override
    public boolean save(CustomerOrder customerOrder) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO customer_order VALUES (?,?,?,?,?,?)",
                customerOrder.getCustomer_order_id(),
                customerOrder.getCustomer_id(),
                customerOrder.getTime(),
                customerOrder.getDate(),
                customerOrder.getPayment(),
                null
        );
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT customer_order_id FROM customer_order ORDER BY LENGTH(customer_order_id),customer_order_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getSearchId(String id) throws SQLException, ClassNotFoundException {
       return null;
    }

    @Override
    public CustomerOrder getData(String id) throws SQLException, ClassNotFoundException {
        return null;
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
    public boolean update(CustomerOrder customerOrder) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public CustomerOrder getOrderDate(String id) throws SQLException, ClassNotFoundException {
        System.out.println("Ado Ado");
        ResultSet set = SQLUtill.execute("SELECT date FROM customer_order where customer_order_id=?", id);
        CustomerOrder customerOrder = new CustomerOrder();
        if (set.next()) {
            customerOrder.setDate(set.getString(4));
        }
        return customerOrder;
    }

    @Override
    public boolean saveRepairOrder(CustomerOrder customerOrder) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO customer_order VALUES (?,?,?,?,?,?)",
                customerOrder.getCustomer_order_id(),
                customerOrder.getCustomer_id(),
                customerOrder.getTime(),
                customerOrder.getDate(),
                customerOrder.getPayment(),
                customerOrder.getRepair_id()
        );
    }

    @Override
    public ArrayList<String> getMonthlyAllOrderId() throws SQLException, ClassNotFoundException {
        String date = DateTimeUtil.dateNow();
        String[] dateSplit = date.split("-");
        ResultSet set = SQLUtill.execute("SELECT customer_order_id FROM customer_order WHERE date LIKE ? ORDER BY LENGTH(customer_order_id),customer_order_id ", dateSplit[0] + "-" + dateSplit[1] + "-%");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public String getTodayOrdersCount() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT COUNT(customer_order_id) FROM customer_order WHERE date=?", DateTimeUtil.dateNow());

        if (set.next()) {
            return set.getString(1);
        }
        return null;
    }

    @Override
    public double getCustomerOrderPayment(int i, String month) throws SQLException, ClassNotFoundException {
        String dateNow = DateTimeUtil.dateNow();
        String[] split = dateNow.split("-");

        String processMonth=null;
        if (month.length()==2){
            processMonth=month;
        }else {
            processMonth="0"+month;
        }

        String processDate=null;
        if (String.valueOf(i).length() ==2){
            processDate= String.valueOf(i);
        }else {
            processDate="0"+String.valueOf(i);
        }

        //System.out.println(split[0]+"-"+processMonth+"-"+processDate);
        ResultSet set=  SQLUtill.execute("SELECT  payment FROM customer_order WHERE date LIKE ?", split[0]+"-"+processMonth+"-"+processDate);

        while (set.next()){
            return Double.parseDouble(set.getString(1));
        }
        return 0;
    }
}
