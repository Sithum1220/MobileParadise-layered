package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.RepairViewBO;
import lk.ijse.MobileShop.bo.custom.impl.RepairViewBOImpl;
import lk.ijse.MobileShop.dto.CustomerDTO;
import lk.ijse.MobileShop.dto.RepairDTO;
import lk.ijse.MobileShop.controller.utill.Navigation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RepairViewFormController implements Initializable {

    public Text txtCustomerId;
    private static String id;
    public CheckBox checkGive;

    @FXML
    private JFXButton btncancel;

    @FXML
    private Text txtRepairId;

    @FXML
    private Text txtName;

    @FXML
    private Text txtSteet;

    @FXML
    private Text txtModelNum;

    @FXML
    private Text txtImeiNum;

    @FXML
    private Text txtStatus;

    @FXML
    private Text txtErrorType;

    @FXML
    private Text txtContactNumber;

    @FXML
    private Text txtreturnDate;

    RepairViewBO repairViewBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.REPAIR_VIEW);

    public void CloseOnAction(ActionEvent actionEvent) {
        CustomerOrderFormController.getInstance().AddUpdatePane.setTranslateX(-2000);
        CustomerOrderFormController.getInstance().AddUpdatePane.getChildren().clear();
        CustomerOrderFormController.getInstance().closePane.setTranslateX(-2000);
        CustomerOrderFormController.getInstance().addIcon.setVisible(true);
        CustomerOrderFormController.getInstance().detailsIcon.setVisible(false);
        CustomerOrderFormController.getInstance().btnNew.setText("New Repair");
    }

    public void colseMouserClick(MouseEvent mouseEvent) {
        Navigation.close(mouseEvent);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setData();
        setVisibleRedoButton();
    }

    private void setVisibleRedoButton() {
        try {
            if (repairViewBO.getReturnGiveDate(txtRepairId.getText())==null){
                checkGive.setVisible(true);
            }else {
                checkGive.setVisible(false);
            }
            System.out.println(txtRepairId.getText());
            if (repairViewBO.getRepairWarrentyStatus(txtRepairId.getText()).equals("Repair")){
                checkGive.setVisible(false);
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void setId(String id){
        RepairViewFormController.id=id;
    }



    private void setData() {
        try {
            RepairDTO repairDTO = repairViewBO.getRepairData(id);
            CustomerDTO customerDTO = repairViewBO.getCustomerData(repairDTO.getCustomer_id());
            txtName.setText(customerDTO.getFirst_name()+" "+ customerDTO.getLast_name());
            txtContactNumber.setText(customerDTO.getContact_number());

            txtRepairId.setText(repairDTO.getRepair_id());
            txtErrorType.setText(repairDTO.getError_type());
            txtreturnDate.setText(repairDTO.getReturn_give_date());
            txtStatus.setText(repairDTO.getStatus());
            txtCustomerId.setText(repairDTO.getCustomer_id());
            txtImeiNum.setText(repairDTO.getImei_number());
            txtModelNum.setText(repairDTO.getModel_number());


        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void giveOnAction(ActionEvent actionEvent) {
        try {
             if (repairViewBO.updateReturnGiveDateByWarranty(txtRepairId.getText())){
                 setVisibleRedoButton();
                 setData();
             }else {
                 setVisibleRedoButton();

             }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

    }
}
