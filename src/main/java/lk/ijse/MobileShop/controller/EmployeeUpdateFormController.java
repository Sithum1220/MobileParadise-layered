package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.EmployeeUpdateBO;
import lk.ijse.MobileShop.bo.custom.impl.EmployeeUpdateBOImpl;
import lk.ijse.MobileShop.dto.EmployeeDTO;
import lk.ijse.MobileShop.dto.UserDTO;
import lk.ijse.MobileShop.dao.custom.impl.utill.DateTimeUtil;
import lk.ijse.MobileShop.controller.utill.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeUpdateFormController implements Initializable {

    static String id;
    public JFXTextField txtFName;
    public JFXTextField txtLname;
    public JFXTextField txtStreet;
    public JFXTextField txtLane;
    public JFXTextField txtCity;
    public JFXTextField txtNIC;
    public JFXTextField txtEmail;
    public JFXTextField txtContactNumber;
    public JFXComboBox cmbRole;
    public JFXTextField txtUserName;
    public JFXTextField txtPassword;
    public JFXButton btnAdd;
    EmployeeUpdateBO employeeUpdateBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.EMPLOYEE_UPDATE);

    public static void setId(String id) {
        EmployeeUpdateFormController.id = id;
    }

    private void setData() {
        try {
            EmployeeDTO employeeDTO = employeeUpdateBO.getAllEmployeeData(id);
            UserDTO userDTO = employeeUpdateBO.getAllUserData(id);
            txtFName.setText(employeeDTO.getFirst_name());
            txtLname.setText(employeeDTO.getLast_name());
            txtCity.setText(employeeDTO.getCity());
            txtStreet.setText(employeeDTO.getStreet());
            txtLane.setText(employeeDTO.getLane());
            txtEmail.setText(employeeDTO.getEmail());
            txtContactNumber.setText(employeeDTO.getContact_number());
            txtNIC.setText(employeeDTO.getNic());
            cmbRole.setValue(employeeDTO.getRole());

            if (cmbRole.getValue().equals("Admin") || cmbRole.getValue().equals("Cashier")) {
                txtUserName.setVisible(true);
                txtPassword.setVisible(true);
                txtUserName.setText(userDTO.getUser_name());
                txtPassword.setText(userDTO.getPassword());
            } else {
                txtUserName.setVisible(false);
                txtPassword.setVisible(false);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        EmployeeFormController.getInstance().tempPane.getChildren().clear();
        EmployeeFormController.getInstance().tempPane.setTranslateX(-380);
        EmployeeFormController.getInstance().closePane.setTranslateX(-390);
        EmployeeFormController.getInstance().updateIcon.setVisible(false);
        EmployeeFormController.getInstance().optionLbl.setText("New");
        EmployeeFormController.getInstance().newIcon.setVisible(true);
        EmployeeFormController.getInstance().deleteIcon.setVisible(false);

        if (cmbRole.getValue().equals("Admin") || cmbRole.getValue().equals("Cashier")) {
            try {
                if (employeeUpdateBO.updateEmployee(new EmployeeDTO(
                        id,
                        txtFName.getText(),
                        txtLname.getText(),
                        txtStreet.getText(),
                        txtCity.getText(),
                        txtLane.getText(),
                        getRole(),
                        DateTimeUtil.dateNow(),
                        txtNIC.getText(),
                        txtEmail.getText(),
                        txtContactNumber.getText()
                )) && employeeUpdateBO.updateUser(new UserDTO(
                        id,
                        txtUserName.getText(),
                        txtPassword.getText(),
                        (String) cmbRole.getValue()))) {

                    new Alert(Alert.AlertType.CONFIRMATION, "OK").show();
                    EmployeeFormController.getInstance().loadAllIds();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                if (employeeUpdateBO.updateEmployee(new EmployeeDTO(
                        id,
                        txtFName.getText(),
                        txtLname.getText(),
                        txtStreet.getText(),
                        txtCity.getText(),
                        txtLane.getText(),
                        getRole(),
                        DateTimeUtil.dateNow(),
                        txtNIC.getText(),
                        txtEmail.getText(),
                        txtContactNumber.getText()
                ))) {
                    new Alert(Alert.AlertType.CONFIRMATION, "OK").show();
                    EmployeeFormController.getInstance().loadAllIds();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Error").show();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private void setdataInCoboBox() {
        ArrayList<String> roles = new ArrayList<>();
        roles.add("Admin");
        roles.add("Cashier");
        roles.add("Other");
        roles.add("Salesman");
        roles.add("Other");
        cmbRole.getItems().addAll(roles);
    }

    public void cmbRoleOnAction(ActionEvent actionEvent) {
    }

    private String getRole() {
        return String.valueOf(cmbRole.getSelectionModel().getSelectedItem());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setdataInCoboBox();
        setData();
    }

    public void fNameKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtFName, txtFName.getText(), "^[a-zA-Z-'`]+[ a-zA-Z-'`]$", "-fx-text-fill: white");

    }

    public void lNameKeyRelease(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtLname, txtLname.getText(), "^[a-zA-Z-'`]+[ a-zA-Z-'`]$", "-fx-text-fill: white");

    }

    public void cityKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtCity, txtCity.getText(), "^[a-zA-Z-'`]+[ a-zA-Z-'`]$", "-fx-text-fill: white");

    }

    public void nicKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtNIC, txtNIC.getText(), "^(?:19|20)?\\d{2}[0-9]{10}|[0-9]{9}[x|X|v|V]$", "-fx-text-fill: white");

    }

    public void contactKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtContactNumber, txtContactNumber.getText(), "^(?:0|94|\\+94)?(?:7(0|1|2|4|5|6|7|8)\\d)\\d{6}$", "-fx-text-fill: white");

    }

    public void userNameKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtUserName, txtUserName.getText(), "^[a-zA-Z0-9_]{4,16}$", "-fx-text-fill: white");

    }

    public void passwordKeyRelease(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtPassword, txtPassword.getText(), "^(?=.{8,})(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$", "-fx-text-fill: white");

    }

    public void emailKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnAdd, txtEmail, txtEmail.getText(), "^([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}$", "-fx-text-fill: white");

    }
}
