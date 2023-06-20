package lk.ijse.MobileShop.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.ItemCashierViewBO;
import lk.ijse.MobileShop.bo.custom.impl.ItemCashierViewBOImpl;
import lk.ijse.MobileShop.controller.utill.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemCashierViewFormController implements Initializable {
    public JFXTextField txtSearch;
    public Text txtLimitedItem;
    @FXML
    private VBox vBox;
    ItemCashierViewBO itemCashierViewBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.ITEM_CASHIER_VIEW);
    @FXML
    void backOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("CashierDashBordForm.fxml", event);
    }

    @FXML
    void searchOnKeyReleased(KeyEvent event) throws SQLException, ClassNotFoundException {
        vBox.getChildren().clear();
        ArrayList<String> ids = itemCashierViewBO.getSearchId(txtSearch.getText());

        for (int i = 0; i < ids.size(); i++) {
            loadAllItems(ids.get(i));
        }
    }

    private void loadAllItems(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(ItemFormController.class.getResource("/view/ItemCashierViewBarForm.fxml"));
            Parent root = loader.load();
            ItemCashierViewBarFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllIds();
        setItemStockLimited();
    }

    private void loadAllIds() {
        try {
            vBox.getChildren().clear();
            ArrayList<String> ids = itemCashierViewBO.getAllItemId();
            for (int i = 0; i < ids.size(); i++) {
                loadAllItems(ids.get(i));
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeOnMouseClick(MouseEvent mouseEvent) {
        Navigation.exit();
    }

    private void setItemStockLimited() {
        String count = null;
        try {
            count = itemCashierViewBO.getItemLimitCount();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        if (count != null) {
            txtLimitedItem.setText(count);

        }
    }
}
