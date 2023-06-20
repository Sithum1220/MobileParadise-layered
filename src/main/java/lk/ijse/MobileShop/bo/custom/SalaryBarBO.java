package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.SalaryDTO;

import java.sql.SQLException;

public interface SalaryBarBO extends SuperBO {
    SalaryDTO getAllSalaryData(String id) throws SQLException, ClassNotFoundException;
}
