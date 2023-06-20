package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.CustomerOrderBO;
import lk.ijse.MobileShop.bo.custom.impl.CustomerOrderBOImpl;
import lk.ijse.MobileShop.controller.utill.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerOrderFormController implements Initializable {

    private static CustomerOrderFormController controller;
    public JFXRadioButton rBtnCustomerOrder;
    public JFXButton btnPayment;
    public Pane orderPane;
    public Pane tablePane;
    public Text txtMainPrice;
    public Pane AddUpdatePane;
    public Pane closePane;
    public ImageView addIcon;
    public ImageView detailsIcon;
    public Text txtNew;
    public Pane paymentPane;
    public Pane closePaymentPane;
    public ImageView deleteIcon;
    @FXML
    public JFXTextField txtSearch;
    @FXML
    public Text txtOrders;
    @FXML
    public VBox vBox;
    @FXML
    public Text btnNew;
    @FXML
    private Text txt1;
    @FXML
    private Text txt2;
    @FXML
    private Text txt4;
    @FXML
    private Text txt3;
    @FXML
    private ToggleGroup Order;
    @FXML
    private JFXRadioButton rBtnRepair;
    @FXML
    private Pane repairpane;

    CustomerOrderBO customerOrderBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.CUSTOMER_ORDER);
    public CustomerOrderFormController() {
        controller = this;
    }

    public static CustomerOrderFormController getInstance() {
        return controller;
    }

    @FXML
    void backOnAction(ActionEvent event) {
        try {
            Navigation.switchNavigation("CashierDashBordForm.fxml", event);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void closeOnMouseClick(MouseEvent event) {
        paymentPane.setTranslateX(-2000);
        closePaymentPane.setTranslateX(-2000);
        paymentPane.getChildren().clear();
    }

    @FXML
    void searchOnKeyReleased(KeyEvent event) {
        if (rBtnCustomerOrder.isSelected()) {
            try {
                vBox.getChildren().clear();
                ArrayList<String> ids = customerOrderBO.getCustomerOrderSearchId(txtSearch.getText());

                for (int i = 0; i < ids.size(); i++) {
                    loadAllOrders(ids.get(i));
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        if (rBtnRepair.isSelected()) {
            vBox.getChildren().clear();
            try {
                ArrayList<String> ids = customerOrderBO.getRepairSearchId(txtSearch.getText());
                for (int i = 0; i < ids.size(); i++) {
                    loadAllRepair(ids.get(i));
                }
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadTablaData();
        btnPayment.setVisible(false);
        repairpane.setVisible(false);
        txt1.setVisible(true);
        txt2.setVisible(true);
        txt3.setVisible(true);
        txt4.setVisible(true);
        txtMainPrice.setVisible(true);
        orderPane.setTranslateX(-1000);
        tablePane.setTranslateX(-1000);
        AddUpdatePane.setTranslateX(-2000);
        closePane.setTranslateX(-2000);
        paymentPane.setTranslateX(-2000);
        closePaymentPane.setTranslateX(-2000);
        setTodayOrderCount();
//        try {
//            chechReturnGiveDate();
//        } catch (SQLException | ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }

    public void setTodayOrderCount() {
        try {
            String count = customerOrderBO.getTodayCustomerOrderCount();
            txtOrders.setText(count);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTablaData() {
        if (rBtnCustomerOrder.isSelected()) {
            loadAllIds();
        }
        if (rBtnRepair.isSelected()) {
            loadRepair();
        }

    }

    private void loadRepair() {
        vBox.getChildren().clear();
        try {
            ArrayList<String> id = customerOrderBO.getAllRepairId();
            for (int i = 0; i < id.size(); i++) {
                loadAllRepair(id.get(i));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllRepair(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(CustomerOrderFormController.class.getResource("/view/RepairBarForm.fxml"));
            Parent root = loader.load();
            RepairBarFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadAllIds() {
        vBox.getChildren().clear();
        try {
            ArrayList<String> id = customerOrderBO.getAllCustomerOrderId();
            for (int i = 0; i < id.size(); i++) {
                loadAllOrders(id.get(i));
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllOrders(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(CustomerOrderFormController.class.getResource("/view/CustomerOderBarForm.fxml"));
            Parent root = loader.load();
            CustomerOrderBarFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnNewOrderOnAction(ActionEvent actionEvent) throws IOException {

        if (btnNew.getText().equals("New Order")) {
            orderPane.setTranslateX(50);
            FXMLLoader loader = new FXMLLoader(EmployeeFormController.class.getResource("/view/CustomerPlaceOrderForm.fxml"));
            Parent root = loader.load();
            orderPane.getChildren().add(root);
        } else {
//            try {
//                Navigation.popupNavigation("RepairAddForm.fxml");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
            AddUpdatePane.setTranslateX(-235);
            FXMLLoader loader = new FXMLLoader(EmployeeFormController.class.getResource("/view/RepairAddForm.fxml"));
            Parent root = loader.load();
            AddUpdatePane.getChildren().add(root);
            closePane.setTranslateX(0);
        }
    }

    public void allOrdersOnAction(ActionEvent actionEvent) {
        loadTablaData();
        if (rBtnCustomerOrder.isSelected()) {
            txtSearch.setPromptText("Search Id Or Name");
            repairpane.setVisible(false);
            txt1.setVisible(true);
            txt2.setVisible(true);
            txt3.setVisible(true);
            txt4.setVisible(true);
            txtMainPrice.setVisible(true);
            btnNew.setText("New Order");
            btnPayment.setVisible(false);

        }
        if (rBtnRepair.isSelected()) {
            txtSearch.setPromptText("Search Id, Model Number or Status");
            repairpane.setVisible(true);
            txt1.setVisible(false);
            txt2.setVisible(false);
            txt3.setVisible(false);
            txt4.setVisible(false);
            txtMainPrice.setVisible(false);
            btnNew.setText("New Repair");
            btnPayment.setVisible(true);
        }
    }

    public void RepairOnAction(ActionEvent actionEvent) {
        if (rBtnCustomerOrder.isSelected()) {
            txtSearch.setPromptText("Search Id Or Name");
            repairpane.setVisible(false);
            txt1.setVisible(true);
            txt2.setVisible(true);
            txt3.setVisible(true);
            txt4.setVisible(true);
            txtMainPrice.setVisible(true);
            btnNew.setText("New Order");
            btnPayment.setVisible(false);
        }
        if (rBtnRepair.isSelected()) {
            txtSearch.setPromptText("Search Id, Model Number or Status");
            repairpane.setVisible(true);
            txt1.setVisible(false);
            txt2.setVisible(false);
            txt3.setVisible(false);
            txt4.setVisible(false);
            txtMainPrice.setVisible(false);
            btnNew.setText("New Repair");
            btnPayment.setVisible(true);
        }
        loadTablaData();
    }

    public void btnPaymentOnAction(ActionEvent actionEvent) {
        try {

            paymentPane.setTranslateX(-220);
            FXMLLoader loader = new FXMLLoader(EmployeeFormController.class.getResource("/view/RepairPaymentForm.fxml"));
            Parent root = loader.load();
            paymentPane.getChildren().add(root);
            closePaymentPane.setTranslateX(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closePaneOnMouseClick(MouseEvent mouseEvent) {
        AddUpdatePane.setTranslateX(-2000);
        AddUpdatePane.getChildren().clear();
        closePane.setTranslateX(-2000);
        addIcon.setVisible(true);
        detailsIcon.setVisible(false);
        deleteIcon.setVisible(false);
        btnNew.setText("New Repair");
    }
}
