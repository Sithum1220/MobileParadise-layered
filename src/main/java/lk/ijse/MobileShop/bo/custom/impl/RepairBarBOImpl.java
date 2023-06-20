package lk.ijse.MobileShop.bo.custom.impl;

import lk.ijse.MobileShop.bo.custom.RepairBarBO;
import lk.ijse.MobileShop.dao.DAOFactory;
import lk.ijse.MobileShop.dao.custom.RepairDAO;
import lk.ijse.MobileShop.dao.custom.impl.RepairDAOImpl;
import lk.ijse.MobileShop.dto.RepairDTO;
import lk.ijse.MobileShop.entity.Repair;

import java.sql.SQLException;

public class RepairBarBOImpl implements RepairBarBO {
    RepairDAO repairDAO = DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.REPAIR);

    @Override
    public RepairDTO getRepairData(String id) throws SQLException, ClassNotFoundException {
        Repair repair = repairDAO.getData(id);
        return new RepairDTO(
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
}
