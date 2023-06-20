package lk.ijse.MobileShop.controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import lk.ijse.MobileShop.bo.BOFactory;
import lk.ijse.MobileShop.bo.custom.CashierDashboardBO;
import lk.ijse.MobileShop.bo.custom.impl.CashierDashboardBOImpl;
import lk.ijse.MobileShop.controller.utill.Navigation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class CashierDashbordFormController implements Initializable {
    private static CashierDashbordFormController controller;
    public Text txtAttendanceCount;
    public Text txtTodayOrders;
    public Text hh;
    public Text mm;
    public Text date;
    public Text txtLimitedStock;

    CashierDashboardBO cashierDashboardBO = BOFactory.getBoFactory().getBO(BOFactory.BOType.CASHIER_DASHBOARD);
    public CashierDashbordFormController() {
        controller = this;
    }

    public static CashierDashbordFormController getInstance() {
        return controller;
    }

    public void closeAction(MouseEvent mouseEvent) {
        Navigation.exit();
    }

    public void btnCustomerOnAction(ActionEvent event) {
        try {
            Navigation.switchNavigation("CustomerForm.fxml", event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stockViewOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("ItemCashierViewForm.fxml", event);
    }

    public void empAttendanceOnAction(ActionEvent event) throws IOException {
        Navigation.switchNavigation("EmployeeAttendance.fxml", event);
    }

    public void btnOderOnAction(ActionEvent actionEvent) {
        try {
            Navigation.switchNavigation("CustomerOderFrom.fxml", actionEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void logOutOnMouseClick(MouseEvent mouseEvent) {
        try {
            Navigation.switchNavigation("LoginForm.fxml", mouseEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCount() {
        try {
            String count = cashierDashboardBO.getTodayEmployeeAttendanceCount();

            txtAttendanceCount.setText(count);

            String count1 = cashierDashboardBO.getTodayOrdersCount();
            txtTodayOrders.setText(count1);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCount();
        time();
        setItemStockLimited();
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

    private void setItemStockLimited() {
        String count = null;
        try {
            count = cashierDashboardBO.getItemLimitCount();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        if (count != null) {
            txtLimitedStock.setText(count);
        }
    }
}
