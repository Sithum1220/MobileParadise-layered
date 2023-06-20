package lk.ijse.MobileShop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class SupplierOrder {
    private String supplier_order_id;
    private String supplier_id;
    private String time;
    private String date;
    private double payment;
}
