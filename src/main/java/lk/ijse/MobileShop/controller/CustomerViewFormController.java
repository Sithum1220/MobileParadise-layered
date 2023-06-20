package lk.ijse.MobileShop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.CustomerViewBO;
import lk.ijse.MobileShop.bo.custom.impl.CustomerViewBOImpl;
import lk.ijse.MobileShop.dto.CustomerDTO;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerViewFormController implements Initializable {
    private static String id;
    public Text txtContactNumber;
    public Text txtEmail;
    public Text txtNIC;
    public Text txtDate;
    public Text txtCity;
    public Text txtLane;
    public Text txtStreet;
    public Text txtName;
    public Text txtId;
    CustomerViewBO customerViewBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.CUSTOMER_VIEW);

    public static void setId(String id) {
        CustomerViewFormController.id = id;
    }

    public void CloseOnAction(ActionEvent event) {
        CustomerFormController.getInstance().deleteIcon.setVisible(false);
        CustomerFormController.getInstance().updateIcon.setVisible(false);
        CustomerFormController.getInstance().addIcon.setVisible(true);
        CustomerFormController.getInstance().detailsIcon.setVisible(false);
        CustomerFormController.getInstance().txtNew.setText("New");
        CustomerFormController.getInstance().closePane.setTranslateX(-1500);
        CustomerFormController.getInstance().addUpdatePane.setTranslateX(-1500);
        CustomerFormController.getInstance().addUpdatePane.getChildren().clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setData();
    }

    private void setData() {
        try {
            CustomerDTO customerDTO = customerViewBO.getCustomerData(id);
            System.out.println(customerDTO.toString());
            txtContactNumber.setText(customerDTO.getContact_number());
            txtEmail.setText(customerDTO.getEmail());
            txtNIC.setText(customerDTO.getNic());
            txtDate.setText(customerDTO.getDate());
            txtCity.setText(customerDTO.getCity());
            txtLane.setText(customerDTO.getLane());
            txtStreet.setText(customerDTO.getStreet());
            txtName.setText(customerDTO.getFirst_name() + " " + customerDTO.getLast_name());
            txtId.setText(customerDTO.getCustomer_id());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
