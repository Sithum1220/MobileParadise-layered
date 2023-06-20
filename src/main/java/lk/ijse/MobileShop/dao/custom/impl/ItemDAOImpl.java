package lk.ijse.MobileShop.dao.custom.impl;

import lk.ijse.MobileShop.dao.custom.ItemDAO;
import lk.ijse.MobileShop.dao.custom.impl.utill.SQLUtill;
import lk.ijse.MobileShop.dto.CustomEntityDTO;
import lk.ijse.MobileShop.dto.CustomerOrderDetailsDTO;
import lk.ijse.MobileShop.dto.ItemDTO;
import lk.ijse.MobileShop.dto.CustomerOrderDTO;
import lk.ijse.MobileShop.entity.CustomerOrder;
import lk.ijse.MobileShop.entity.CustomerOrderDetails;
import lk.ijse.MobileShop.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item item) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO item VALUES (?,?,?,?,?,?,?,?)",
                item.getItem_code(),
                item.getItem_name(),
                item.getCategory(),
                item.getUnit_price(),
                item.getQty(),
                item.getBrand(),
                item.getDescription(),
                item.getWarranty_month()
        );
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();
        ResultSet set = SQLUtill.execute("SELECT item_code FROM item");
        while (set.next()) {
            ids.add(set.getString(1));
        }
        return ids;
    }

    @Override
    public ArrayList<String> getSearchId(String id) throws SQLException, ClassNotFoundException {
        String SearchId = id + "%";
        ResultSet set = SQLUtill.execute("SELECT item_code FROM item where item.item_code LIKE ? or item.category LIKE ? or item.item_name LIKE ? or brand LIKE ?", SearchId, SearchId, SearchId, SearchId);
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public Item getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM item where item_code=?", id);
        Item item = new Item();
        if (set.next()) {
            item.setItem_code(set.getString(1));
            item.setItem_name(set.getString(2));
            item.setCategory(set.getString(3));
            item.setUnit_price(Double.parseDouble(set.getString(4)));
            item.setQty(Integer.parseInt(set.getString(5)));
            item.setBrand(set.getString(6));
            item.setDescription(set.getString(7));
            item.setWarranty_month(Integer.parseInt(set.getString(8)));
        }
        return item;
    }

    @Override
    public String getAllCount() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature yet to be developed");
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException("This feature yet to be developed");
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE item SET " +
                        "item_name=?," +
                        "category=?," +
                        "unit_price=? ," +
                        "qty=? ," +
                        "brand=? ," +
                        "description =?," +
                        "Warranty_month=?" +
                        "WHERE item_code=?",
                item.getItem_name(),
                item.getCategory(),
                item.getUnit_price(),
                item.getQty(),
                item.getBrand(),
                item.getDescription(),
                item.getWarranty_month(),
                item.getItem_code()
        );
    }

    @Override
    public String getItemLimitCount() throws SQLException, ClassNotFoundException {
        ResultSet set= SQLUtill.execute("SELECT COUNT(item_code) FROM item WHERE qty<5");
        if (set.next()){
            return set.getString(1);
        }
        return null;
    }

    @Override
    public boolean updateItemBySupplierOrder(ArrayList<CustomEntityDTO> list) throws SQLException, ClassNotFoundException {
        for (int i = 0; i < list.size(); i++) {
            if (SQLUtill.execute("UPDATE item SET unit_price=?,qty=qty+? WHERE item_code=?",
                    list.get(i).getItemSellingPrice(),
                    list.get(i).getItemQTY(),
                    list.get(i).getItemId()
            )) {

            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateItemByCustomerOrder(ArrayList<CustomerOrderDetails> list, CustomerOrder customerOrder) throws SQLException, ClassNotFoundException {
        boolean isAdded = false;
        for (int i = 0; i < list.size(); i++) {
            if (SQLUtill.execute("UPDATE item set qty=qty-? WHERE item_code=?",
                    list.get(i).getQty(),
                    list.get(i).getItem_id()
            )) {
                isAdded = true;
            } else {
                isAdded = false;
            }
        }
        return isAdded;
    }
}
