package lk.ijse.MobileShop.controller;

import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.SalaryBarBO;
import lk.ijse.MobileShop.bo.custom.impl.SalaryBarBOImpl;
import lk.ijse.MobileShop.dto.SalaryDTO;

import java.sql.SQLException;

public class SalaryBarFormController {
    public Text txtEmployeeId;
    public Text txtSalaryId;
    public Text txtTime;
    public Text txtDate;
    public Text txtAttendance;
    public Text txtSalary;
    SalaryBarBO salaryBarBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.SALARY_BAR);

    public void setData(String id) {
        try {
            SalaryDTO salaryDTO = salaryBarBO.getAllSalaryData(id);
            txtEmployeeId.setText(salaryDTO.getEmployee_id());
            txtSalaryId.setText(salaryDTO.getSalary_id());
            txtTime.setText(salaryDTO.getTime());
            txtDate.setText(salaryDTO.getDate());
            txtAttendance.setText(salaryDTO.getMonthly_Attendance_count());
            txtSalary.setText(salaryDTO.getSalary());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
