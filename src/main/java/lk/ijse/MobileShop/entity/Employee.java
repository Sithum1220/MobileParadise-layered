package lk.ijse.MobileShop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Employee {
    private String employee_id;
    private String first_name;
    private String last_name;
    private String street;
    private String city;
    private String lane;
    private String role;
    private String date;
    private String nic;
    private String email;
    private String contact_number;
}
