package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import lk.ijse.MobileShop.bo.custom.CustomerUpdateBO;
import lk.ijse.MobileShop.bo.custom.impl.CustomerUpdateBOImpl;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.controller.utill.RegexUtil;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerUpdateFormController implements Initializable {

    public static String id;
    @FXML
    private JFXTextField txtFName;
    @FXML
    private JFXTextField txtLname;
    @FXML
    private JFXTextField txtStreet;
    @FXML
    private JFXTextField txtLane;
    @FXML
    private JFXTextField txtCity;
    @FXML
    private JFXTextField txtNIC;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private JFXTextField txtContactNumber;
    @FXML
    private JFXButton btnUpdate;

    CustomerUpdateBO customerUpdateBO = new CustomerUpdateBOImpl();

    public static void setId(String id) {
        CustomerUpdateFormController.id = id;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadCustomer();
    }

    private void loadCustomer() {
        try {
            CustomerDTO customerDTO = customerUpdateBO.getCustomerData(id);
            txtContactNumber.setText(customerDTO.getContact_number());
            txtEmail.setText(customerDTO.getEmail());
            txtNIC.setText(customerDTO.getNic());
            txtCity.setText(customerDTO.getCity());
            txtLane.setText(customerDTO.getLane());
            txtStreet.setText(customerDTO.getStreet());
            txtFName.setText(customerDTO.getFirst_name());
            txtLname.setText(customerDTO.getLast_name());

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setContact_number(txtContactNumber.getText());
        customerDTO.setEmail(txtEmail.getText());
        customerDTO.setNic(txtNIC.getText());
        customerDTO.setCity(txtCity.getText());
        customerDTO.setLane(txtLane.getUserAgentStylesheet());
        customerDTO.setStreet(txtStreet.getText());
        customerDTO.setFirst_name(txtFName.getText());
        customerDTO.setLast_name(txtLname.getText());
        customerDTO.setCustomer_id(id);
        if (customerUpdateBO.updateCustomer(customerDTO)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Done").show();
            CustomerFormController.getInstance().loadAllIds();
        } else {
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }
        CustomerFormController.getInstance().deleteIcon.setVisible(false);
        CustomerFormController.getInstance().updateIcon.setVisible(false);
        CustomerFormController.getInstance().addIcon.setVisible(true);
        CustomerFormController.getInstance().txtNew.setText("New");
        CustomerFormController.getInstance().closePane.setTranslateX(-1500);
        CustomerFormController.getInstance().addUpdatePane.setTranslateX(-1500);
        CustomerFormController.getInstance().addUpdatePane.getChildren().clear();
    }

    public void fNameKeyRelased(KeyEvent keyEvent) {
        RegexUtil.regex(btnUpdate, txtFName, txtFName.getText(), "^[a-zA-Z-'`]+[ a-zA-Z-'`]$", "-fx-text-fill: white");

    }

    public void lNameKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnUpdate, txtLname, txtLname.getText(), "^[a-zA-Z-'`]+[ a-zA-Z-'`]$", "-fx-text-fill: white");

    }

    public void cityKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnUpdate, txtCity, txtCity.getText(), "^[a-zA-Z-'`]+[ a-zA-Z-'`]$", "-fx-text-fill: white");

    }

    public void nicKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnUpdate, txtNIC, txtNIC.getText(), "^(?:19|20)?\\d{2}[0-9]{10}|[0-9]{9}[x|X|v|V]$", "-fx-text-fill: white");

    }

    public void emailKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnUpdate, txtEmail, txtEmail.getText(), "^([\\w\\.\\-_]+)?\\w+@[\\w-_]+(\\.\\w+){1,}$", "-fx-text-fill: white");

    }

    public void contactKeyReleased(KeyEvent keyEvent) {
        RegexUtil.regex(btnUpdate, txtContactNumber, txtContactNumber.getText(), "^(?:0|94|\\+94)?(?:7(0|1|2|4|5|6|7|8)\\d)\\d{6}$", "-fx-text-fill: white");

    }
}
