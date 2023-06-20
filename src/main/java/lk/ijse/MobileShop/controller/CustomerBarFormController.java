package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.CustomerBarBO;
import lk.ijse.MobileShop.bo.custom.impl.CustomerBarBOImpl;
import lk.ijse.MobileShop.dto.CustomerDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerBarFormController implements Initializable {

    public Text txtId;

    public Text txtName;
    public Text txtRole;

    public Text txtCity;

    public JFXButton btnupdate;

    public JFXButton btnDelete;

    public Text txtPhoneNumber;

    CustomerBarBO customerBarBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.CUSTOMER_BAR);

    public void setData(String id) {
        try {
            CustomerDTO customerDTO = customerBarBO.getCustomerData(id);
            txtId.setText(customerDTO.getCustomer_id());
            txtName.setText(customerDTO.getFirst_name() + " " + customerDTO.getLast_name());
            txtPhoneNumber.setText(customerDTO.getContact_number());
            txtCity.setText(customerDTO.getCity());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void mousEntered(MouseEvent mouseEvent) {
        btnDelete.setVisible(true);
        btnupdate.setVisible(true);

    }

    public void mouseExited(MouseEvent mouseEvent) {
        btnDelete.setVisible(false);
        btnupdate.setVisible(false);
    }

    public void ViewOnMouseClick(MouseEvent mouseEvent) {
        CustomerViewFormController.setId(txtId.getText());
        CustomerFormController.getInstance().deleteIcon.setVisible(false);
        CustomerFormController.getInstance().updateIcon.setVisible(false);
        CustomerFormController.getInstance().addIcon.setVisible(false);
        CustomerFormController.getInstance().detailsIcon.setVisible(true);
        CustomerFormController.getInstance().txtNew.setText("Details");
        CustomerDeleteFormController.setId(txtId.getText());
        CustomerFormController.getInstance().addUpdatePane.setTranslateX(-5);
        CustomerFormController.getInstance().closePane.setTranslateX(0);
        FXMLLoader loader = new FXMLLoader(CustomerFormController.class.getResource("/view/CustomerViewForm.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CustomerFormController.getInstance().addUpdatePane.getChildren().add(root);
    }

    public void updateOnAction(ActionEvent actionEvent) throws IOException {
        CustomerUpdateFormController.setId(txtId.getText());
        CustomerFormController.getInstance().deleteIcon.setVisible(false);
        CustomerFormController.getInstance().updateIcon.setVisible(true);
        CustomerFormController.getInstance().addIcon.setVisible(false);
        CustomerFormController.getInstance().detailsIcon.setVisible(false);

        CustomerFormController.getInstance().txtNew.setText("Update");
        CustomerDeleteFormController.setId(txtId.getText());
        CustomerFormController.getInstance().addUpdatePane.setTranslateX(-5);
        CustomerFormController.getInstance().closePane.setTranslateX(0);
        FXMLLoader loader = new FXMLLoader(CustomerFormController.class.getResource("/view/CustomerUpdateForm.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CustomerFormController.getInstance().addUpdatePane.getChildren().add(root);
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        CustomerFormController.getInstance().deleteIcon.setVisible(true);
        CustomerFormController.getInstance().updateIcon.setVisible(false);
        CustomerFormController.getInstance().addIcon.setVisible(false);
        CustomerFormController.getInstance().detailsIcon.setVisible(false);

        CustomerFormController.getInstance().txtNew.setText("Delete");
        CustomerDeleteFormController.setId(txtId.getText());
        CustomerFormController.getInstance().addUpdatePane.setTranslateX(-5);
        CustomerFormController.getInstance().closePane.setTranslateX(0);
        FXMLLoader loader = new FXMLLoader(CustomerFormController.class.getResource("/view/CustomerDeleteForm.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CustomerFormController.getInstance().addUpdatePane.getChildren().add(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnupdate.setVisible(false);
        btnDelete.setVisible(false);
    }
}
