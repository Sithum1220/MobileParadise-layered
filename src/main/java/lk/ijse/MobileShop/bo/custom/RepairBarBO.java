package lk.ijse.MobileShop.bo.custom;

import lk.ijse.MobileShop.bo.SuperBO;
import lk.ijse.MobileShop.dto.RepairDTO;

import java.sql.SQLException;

public interface RepairBarBO extends SuperBO {
    RepairDTO getRepairData(String id) throws SQLException, ClassNotFoundException;
}
