package lk.ijse.MobileShop.dao.custom.impl;

import lk.ijse.MobileShop.dao.custom.CustomerOrderDetailsDAO;
import lk.ijse.MobileShop.dao.custom.impl.utill.SQLUtill;
import lk.ijse.MobileShop.dto.CustomerOrderDetailsDTO;
import lk.ijse.MobileShop.entity.CustomerOrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderDetailsDAOImpl implements CustomerOrderDetailsDAO {

    @Override
    public boolean save(ArrayList<CustomerOrderDetails> list) throws SQLException, ClassNotFoundException {
        boolean isAdded = false;
        for (int i = 0; i < list.size(); i++) {
            isAdded = SQLUtill.execute("INSERT INTO customer_order_detail VALUES (?,?,?,?)",
                    list.get(i).getItem_id(),
                    list.get(i).getCustomer_order_id(),
                    list.get(i).getQty(),
                    list.get(i).getPrice()
            );
        }
        return isAdded;
    }

    @Override
    public ArrayList<CustomerOrderDetails> getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM customer_order_detail WHERE customer_order_id=?",id);

        ArrayList<CustomerOrderDetails> customerOrderDetails = new ArrayList<>();

        CustomerOrderDetails details = new CustomerOrderDetails();

        while (set.next()) {
            details.setItem_id(set.getString(1));
            details.setCustomer_order_id(set.getString(2));
            details.setQty(Integer.parseInt(set.getString(3)));
            details.setPrice(Double.parseDouble(set.getString(4)));

            customerOrderDetails.add(details);
        }
        return customerOrderDetails;
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
    public boolean update(ArrayList<CustomerOrderDetails> customerOrderDetails) throws SQLException, ClassNotFoundException {
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
}
