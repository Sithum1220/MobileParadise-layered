package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.EmployeeDeleteBO;
import lk.ijse.MobileShop.bo.custom.impl.EmployeeDeleteBOImpl;
import lk.ijse.MobileShop.dto.EmployeeDTO;
import lk.ijse.MobileShop.dto.UserDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EmployeeDeleteFormController implements Initializable {
    private static String id = null;
    public Text txtId;
    public Text txtName;
    public Text txtSteet;
    public Text txtLane;
    public Text txtCity;
    public Text txtRole;
    public Text txtDate;
    public Text txtNIC;
    public Text txtEmail;
    public Text txtContactNumber;
    public Text lblUserName;
    public Text txtUserName;
    public Text lblPassword;
    public Text txtPassword;
    public JFXButton btnComform;
    public JFXButton btncancel;
    EmployeeDeleteBO employeeDeleteBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.EMPLOYEE_DELETE);

    public static void setId(String id) {
        EmployeeDeleteFormController.id = id;
    }

    public void ComformOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String count1 = employeeDeleteBO.getAdminCount();
        int count = -1;
        count = Integer.parseInt(count1);
        if (txtRole.getText().equals("Admin")) {
            if (count > 1) {
                employeeDeleteBO.deleteEmployee(id);
                employeeDeleteBO.deleteUsers(id);
                EmployeeFormController.getInstance().loadAllIds();
                EmployeeFormController.getInstance().setCount();
            } else {
                new Alert(Alert.AlertType.ERROR, "Only One Admin").show();
            }
        } else {
            employeeDeleteBO.deleteEmployee(id);
            EmployeeFormController.getInstance().loadAllIds();
            EmployeeFormController.getInstance().setCount();
        }
        EmployeeFormController.getInstance().tempPane.getChildren().clear();
        EmployeeFormController.getInstance().tempPane.setTranslateX(-380);
        EmployeeFormController.getInstance().closePane.setTranslateX(-390);
        EmployeeFormController.getInstance().optionLbl.setText("New");
        EmployeeFormController.getInstance().newIcon.setVisible(true);
        EmployeeFormController.getInstance().deleteIcon.setVisible(false);
    }

    public void CloseOnAction(ActionEvent actionEvent) {
        EmployeeFormController.getInstance().tempPane.getChildren().clear();
        EmployeeFormController.getInstance().tempPane.setTranslateX(-380);
        EmployeeFormController.getInstance().closePane.setTranslateX(-390);
        EmployeeFormController.getInstance().optionLbl.setText("New");
        EmployeeFormController.getInstance().newIcon.setVisible(true);
        EmployeeFormController.getInstance().deleteIcon.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        allData();
    }

    public void allData() {
        try {

            EmployeeDTO employeeDTO = employeeDeleteBO.getEmployeeData(id);
            UserDTO userDTO = employeeDeleteBO.getUsersData( id);
            System.out.println(employeeDTO.getEmployee_id());
            txtId.setText(employeeDTO.getEmployee_id());
            txtName.setText(employeeDTO.getFirst_name() + " " + employeeDTO.getLast_name());
            txtSteet.setText(employeeDTO.getStreet());
            txtCity.setText(employeeDTO.getCity());
            txtLane.setText(employeeDTO.getLane());
            txtContactNumber.setText(employeeDTO.getContact_number());
            txtNIC.setText(employeeDTO.getNic());
            txtEmail.setText(employeeDTO.getEmail());
            txtRole.setText(employeeDTO.getRole());
            txtDate.setText(employeeDTO.getDate());

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
