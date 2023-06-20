package lk.ijse.MobileShop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Salary {
    private String employee_id;
    private String salary_id;
    private double salary;
    private String monthly_Attendance_count;
    private String date;
    private String time;
}
