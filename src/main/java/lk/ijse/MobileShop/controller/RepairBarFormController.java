package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.RepairBarBO;
import lk.ijse.MobileShop.bo.custom.impl.RepairBarBOImpl;
import lk.ijse.MobileShop.dto.RepairDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RepairBarFormController implements Initializable {

    RepairBarBO repairBarBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.REPAIR_BAR);
    public static RepairBarFormController controller;
    @FXML
    public Text txtError;
    @FXML
    public JFXButton btnupdate;
    @FXML
    public JFXButton btnDelete;
    @FXML
    private Text txtRepairId;
    @FXML
    private Text txtCustomerId;
    @FXML
    private Text txtImei;
    @FXML
    private Text txtModelNumber;
    @FXML
    private Text txtWarrenty;

    public RepairBarFormController() {
        controller = this;
    }

    public static RepairBarFormController getInstance() {
        return controller;
    }

    public void updateOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws IOException {
        RepairDeleteFormController.setId(txtRepairId.getText());
        RepairDeleteFormController.setDeleteBtn(btnDelete);
        CustomerOrderFormController.getInstance().AddUpdatePane.setTranslateX(-235);
        FXMLLoader loader = new FXMLLoader(RepairDeleteFormController.class.getResource("/view/RepairDeleteForm.fxml"));
        Parent root = loader.load();
        CustomerOrderFormController.getInstance().AddUpdatePane.getChildren().add(root);
        CustomerOrderFormController.getInstance().closePane.setTranslateX(0);
        CustomerOrderFormController.getInstance().detailsIcon.setVisible(false);
        CustomerOrderFormController.getInstance().addIcon.setVisible(false);
        CustomerOrderFormController.getInstance().deleteIcon.setVisible(true);
        CustomerOrderFormController.getInstance().btnNew.setText("Delete");
    }

    public void setData(String id) {
        try {
            RepairDTO repairDTO = repairBarBO.getRepairData(id);
            txtRepairId.setText(repairDTO.getRepair_id());
            txtCustomerId.setText(repairDTO.getCustomer_id());
            txtError.setText(repairDTO.getError_type());
            txtImei.setText(repairDTO.getImei_number());
            txtWarrenty.setText(repairDTO.getStatus());
            txtModelNumber.setText(repairDTO.getModel_number());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void viewClickOnMouseClicked(MouseEvent mouseEvent) throws IOException {

        RepairViewFormController.setId(txtRepairId.getText());
        CustomerOrderFormController.getInstance().AddUpdatePane.setTranslateX(-235);
        FXMLLoader loader = new FXMLLoader(EmployeeFormController.class.getResource("/view/RepairViewForm.fxml"));
        Parent root = loader.load();
        CustomerOrderFormController.getInstance().AddUpdatePane.getChildren().add(root);
        CustomerOrderFormController.getInstance().closePane.setTranslateX(0);
        CustomerOrderFormController.getInstance().detailsIcon.setVisible(true);
        CustomerOrderFormController.getInstance().addIcon.setVisible(false);
        CustomerOrderFormController.getInstance().btnNew.setText("Details");

    }

    public void viewOnMouseEnterd(MouseEvent mouseEvent) {
        btnupdate.setVisible(true);
        btnDelete.setVisible(true);
    }

    public void viewOnMouseExit(MouseEvent mouseEvent) {
        btnupdate.setVisible(false);
        btnDelete.setVisible(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnupdate.setVisible(false);
        btnDelete.setVisible(false);
    }

}
