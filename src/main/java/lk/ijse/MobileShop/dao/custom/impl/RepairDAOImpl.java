package lk.ijse.MobileShop.dao.custom.impl;

import lk.ijse.MobileShop.dao.custom.RepairDAO;
import lk.ijse.MobileShop.dao.custom.impl.utill.SQLUtill;
import lk.ijse.MobileShop.entity.CustomerOrder;
import lk.ijse.MobileShop.entity.Repair;
import lk.ijse.MobileShop.dao.custom.impl.utill.DateTimeUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepairDAOImpl implements RepairDAO {
    @Override
    public boolean save(Repair repair) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO repair VALUES (?,?,?,?,?,?,?,?)",
                repair.getRepair_id(),
                repair.getCustomer_id(),
                repair.getStatus(),
                repair.getImei_number(),
                repair.getModel_number(),
                repair.getError_type(),
                repair.getGiven_date(),
                repair.getReturn_give_date()
        );
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> ids = new ArrayList<>();
        ResultSet set = SQLUtill.execute("SELECT repair_id FROM repair ORDER BY LENGTH(repair_id),repair_id");
        while (set.next()) {
            ids.add(set.getString(1));
        }
        return ids;
    }

    @Override
    public ArrayList<String> getSearchId(String id) throws SQLException, ClassNotFoundException {
        String SearchId = id + "%";
        ResultSet set = SQLUtill.execute("SELECT repair_id FROM repair where repair_id LIKE ? or status LIKE ? or model_number LIKE ?", SearchId, SearchId, SearchId);
        ArrayList<String> list = new ArrayList<>();
        while (set.next()) {
            list.add(set.getString(1));
        }
        return list;
    }

    @Override
    public Repair getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT * FROM repair WHERE repair_id=?", id);
        Repair repair = new Repair();
        while (set.next()) {
            repair.setRepair_id(set.getString(1));
            repair.setCustomer_id(set.getString(2));
            repair.setStatus(set.getString(3));
            repair.setImei_number(set.getString(4));
            repair.setModel_number(set.getString(5));
            repair.setError_type(set.getString(6));
            repair.setGiven_date(set.getString(7));
            repair.setReturn_give_date(set.getString(8));

        }
        return repair;
    }

    @Override
    public String getAllCount() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM repair WHERE repair_id=?",id);

    }

    @Override
    public boolean update(Repair repair) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateReturnGiveDateByRepair(CustomerOrder customerOrder) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE repair SET return_give_date=? WHERE repair_id=?",
                DateTimeUtil.dateNow(),
                customerOrder.getRepair_id()
        );
    }

    @Override
    public boolean updateReturnGiveDateByWarranty(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE repair SET  return_give_date=? WHERE repair_id=?",DateTimeUtil.dateNow(),id);

    }

    @Override
    public String getStatus(String id) throws SQLException, ClassNotFoundException {
        ResultSet set=  SQLUtill.execute("SELECT status FROM repair WHERE repair_id=?",id);
        while (set.next()){
            return set.getString(1);
        }
        return null;
    }

    @Override
    public String getReturnGiveDate(String id) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT return_give_date FROM repair WHERE repair_id=?", id);
        while (set.next()){
            return set.getString(1);
        }
        return null;
    }
}
