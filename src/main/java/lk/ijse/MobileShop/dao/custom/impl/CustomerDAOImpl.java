package lk.ijse.MobileShop.dao.custom.impl;

import lk.ijse.MobileShop.dao.custom.CustomerDAO;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.dao.custom.impl.utill.SQLUtill;
import lk.ijse.MobileShop.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("insert into customer VALUES (?,?,?,?,?,?,?,?,?,?)",
                customer.getCustomer_id(),
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getStreet(),
                customer.getCity(),
                customer.getLane(),
                customer.getDate(),
                customer.getNic(),
                customer.getEmail(),
                customer.getContact_number()
        );
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT customer_id FROM customer ORDER BY LENGTH(customer_id),customer_id");
        ArrayList<String> list = new ArrayList<>();

        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getSearchId(String id) throws SQLException, ClassNotFoundException {
        String SearchId = id + "%";
        ResultSet set = SQLUtill.execute("SELECT customer_id FROM customer where customer.customer_id LIKE ? or first_name LIKE ? or last_name LIKE ?", SearchId, SearchId, SearchId);
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public Customer getData(String id) throws SQLException, ClassNotFoundException {
        System.out.println(id);
        ResultSet set = SQLUtill.execute("SELECT * FROM customer where customer_id=?", id);
        Customer customer = new Customer();
        if (set.next()) {
            customer.setCustomer_id(set.getString(1));
            customer.setFirst_name(set.getString(2));
            customer.setLast_name(set.getString(3));
            customer.setStreet(set.getString(4));
            customer.setCity(set.getString(5));
            customer.setLane(set.getString(6));
            customer.setDate(set.getString(7));
            customer.setNic(set.getString(8));
            customer.setEmail(set.getString(9));
            customer.setContact_number(set.getString(10));
        }
        return customer;
    }

    @Override
    public String getAllCount() throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT COUNT(customer_id) from customer");
        String count = null;
        if (set.next()){
            count = set.getString(1);
        }

        return count;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM customer WHERE customer_id=?", id);
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE  customer SET " +
                        "first_name=?," +
                        "last_name=?," +
                        "street=? ," +
                        "city=? ," +
                        "lane=? ," +
                        "nic=?," +
                        "email=? " +
                        "WHERE customer_id=?",
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getStreet(),
                customer.getCity(),
                customer.getLane(),
                customer.getNic(),
                customer.getEmail(),
                customer.getCustomer_id()
        );
    }
}
