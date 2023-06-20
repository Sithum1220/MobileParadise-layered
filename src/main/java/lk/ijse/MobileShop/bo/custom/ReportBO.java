package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;

import java.sql.SQLException;

public interface ReportBO extends SuperBO {
    String getAllSalaryPaidEmployeeCount() throws SQLException, ClassNotFoundException;
}
