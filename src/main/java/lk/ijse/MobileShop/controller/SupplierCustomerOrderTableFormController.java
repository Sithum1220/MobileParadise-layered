package lk.ijse.MobileShop.controller;

import javafx.scene.layout.VBox;

public class SupplierCustomerOrderTableFormController {
    public VBox vBox;
    private static SupplierCustomerOrderTableFormController controller;

    public SupplierCustomerOrderTableFormController(){
        controller =this;
    }
    public static SupplierCustomerOrderTableFormController getInstance(){
        return controller;
    }
}
