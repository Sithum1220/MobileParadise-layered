package lk.ijse.MobileShop.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <C,S> extends SuperDAO{
    boolean save(C c) throws SQLException, ClassNotFoundException;

    ArrayList<S> getAllId() throws SQLException, ClassNotFoundException;

    ArrayList<S> getSearchId(S id) throws SQLException, ClassNotFoundException;

    C getData(S id) throws SQLException, ClassNotFoundException;

    S getAllCount() throws SQLException, ClassNotFoundException;

    boolean delete(S id) throws SQLException, ClassNotFoundException;

    boolean update(C c) throws SQLException, ClassNotFoundException;
}
