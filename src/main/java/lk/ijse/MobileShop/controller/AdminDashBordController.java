package lk.ijse.MobileShop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.AdminDashBoardBO;
import lk.ijse.MobileShop.bo.custom.impl.AdminDashBoardBOImpl;
import lk.ijse.MobileShop.controller.utill.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class AdminDashBordController implements Initializable {

    public Text txtLimitedStock;
    public Text mm;
    public Text hh;
    public Text date;
    @FXML
    private Text txtAttendence;
    AdminDashBoardBO adminDashBoardBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.ADMIN_DASHBOARD);
    public void closeAction(MouseEvent mouseEvent) {
        Navigation.exit();
    }

    public void btnEmployeeOnAction(ActionEvent event) {
        try {
            Navigation.switchNavigation("EmployeeForm.fxml", event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnSupplierOnAction(ActionEvent event) {
        try {
            Navigation.switchNavigation("SupplierForm.fxml", event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void btnStockOnAction(ActionEvent event) {
        try {
            Navigation.switchNavigation("ItemForm.fxml", event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setAttendanceCount();
        setItemStockLimited();
        time();
    }

    private void time() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm");
            try {
                while (true) {
                    Thread.sleep(1000);
                    String format1 = format.format(new Date());
                    String[] split = format1.split(":");
                    mm.setText(split[1]);
                    hh.setText(split[0]);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();

        SimpleDateFormat format = new SimpleDateFormat("EEE, MMM d,");
        date.setText(format.format(new Date()));
    }

    private void setAttendanceCount() {
        try {
            String count = adminDashBoardBO.getTodayEmployeeAttendanceCount();
            System.out.println("Attendance Count "+count);
            txtAttendence.setText(count);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setItemStockLimited() {
        String count = null;
        try {
            count = adminDashBoardBO.getItemLimitCount();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        if (count != null) {
            txtLimitedStock.setText(count);

        }
    }

    public void btnreportOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("ReportForm.fxml", actionEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logOutOnMouseClick(MouseEvent mouseEvent) {
        try {
            Navigation.switchNavigation("LoginForm.fxml", mouseEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
