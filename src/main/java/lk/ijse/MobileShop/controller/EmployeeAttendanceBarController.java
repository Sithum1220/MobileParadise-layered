package lk.ijse.MobileShop.controller;

import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.EmployeeAttendanceBarBO;
import lk.ijse.MobileShop.bo.custom.impl.EmployeeAttendanceBarBOImpl;
import lk.ijse.MobileShop.dao.custom.QueryDAO;
import lk.ijse.MobileShop.dao.custom.impl.QueryDAOImpl;
import lk.ijse.MobileShop.dto.CustomEntityDTO;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class EmployeeAttendanceBarController {

    @FXML
    private Text txtId;

    @FXML
    private Text txtName;

    @FXML
    private Text txtTime;

    @FXML
    private Text txtDate;

    EmployeeAttendanceBarBO employeeAttendanceBarBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.EMPLOYEE_ATTENDANCE_BAR);

    public void setData(String id){
        try {
            CustomEntityDTO attendance= employeeAttendanceBarBO.getEmployeeAttendanceData(id);

            System.out.println(attendance.getEmployee_id());
            System.out.println(attendance.getEmployee_name());
            System.out.println(attendance.getTime());
            System.out.println(attendance.getDate());

            txtId.setText(attendance.getEmployee_id());
            txtName.setText(attendance.getEmployee_name());
            txtTime.setText(attendance.getTime());
            txtDate.setText(attendance.getDate());


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
