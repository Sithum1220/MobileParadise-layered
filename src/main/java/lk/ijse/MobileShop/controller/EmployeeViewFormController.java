package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.EmployeeViewBO;
import lk.ijse.MobileShop.bo.custom.impl.EmployeeViewBOImpl;
import lk.ijse.MobileShop.dto.EmployeeDTO;
import lk.ijse.MobileShop.dto.UserDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeViewFormController implements Initializable {
    private static String id = null;
    public JFXButton btncancel;
    public Text txtPassword;
    public Text lblPassword;
    public Text txtUserName;
    public Text lblUserName;
    public Text txtContactNumber;
    public Text txtEmail;
    public Text txtNIC;
    public Text txtDate;
    public Text txtRole;
    public Text txtCity;
    public Text txtLane;
    public Text txtSteet;
    public Text txtName;
    public Text txtId;
    EmployeeViewBO employeeViewBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.EMPLOYEE_VIEW);

    public static void setId(String id) {
        EmployeeViewFormController.id = id;
    }

    public void CloseOnAction(ActionEvent actionEvent) {
        EmployeeFormController.getInstance().deleteIcon.setVisible(false);
        EmployeeFormController.getInstance().newIcon.setVisible(true);
        EmployeeFormController.getInstance().detailsIcon.setVisible(false);
        EmployeeFormController.getInstance().optionLbl.setText("New");
        EmployeeFormController.getInstance().tempPane.getChildren().clear();
        EmployeeFormController.getInstance().tempPane.setTranslateX(-380);
        EmployeeFormController.getInstance().closePane.setTranslateX(-390);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }

    private void setData() {
        try {

            EmployeeDTO employeeDTO = employeeViewBO.getEmployeeData(id);
            UserDTO userDTO = employeeViewBO.getUserData(id);
            txtName.setText(employeeDTO.getFirst_name() + " " + employeeDTO.getLast_name());
            txtId.setText(employeeDTO.getEmployee_id());
            txtSteet.setText(employeeDTO.getStreet());
            txtLane.setText(employeeDTO.getLane());
            txtCity.setText(employeeDTO.getCity());
            txtEmail.setText(employeeDTO.getEmail());
            txtNIC.setText(employeeDTO.getNic());
            txtContactNumber.setText(employeeDTO.getContact_number());
            txtRole.setText(employeeDTO.getRole());
            if (txtRole.getText().equals("Admin") || txtRole.getText().equals("Cashier")) {
                lblUserName.setVisible(true);
                lblPassword.setVisible(true);
                txtUserName.setVisible(true);
                txtPassword.setVisible(true);
                txtUserName.setText(userDTO.getUser_name());
                txtPassword.setText(userDTO.getPassword());
            } else {
                lblUserName.setVisible(false);
                lblPassword.setVisible(false);
                txtUserName.setVisible(false);
                txtPassword.setVisible(false);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
