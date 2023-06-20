package lk.ijse.MobileShop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SupplierOrderDetails {
    private String item_code;
    private String supplier_order_id;
    private int quantity;
    private double unit_price;
}
