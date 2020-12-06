package csc460.SqlPlusShoppingMall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.annotation.PostConstruct;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("")
    public String employeeFormGet(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee";
    }

    @PostMapping("")
    public String employeeAdd(@ModelAttribute Employee employee) {
        System.out.println(employee.toString());
        String sql = "INSERT INTO chaonengquan.Employee (FirstName, LastName, Gender, Address, Phone, EmployeeGroup, Salary) VALUES (?, ?, ? ,? ,? ,? ,?)";
        jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getGender(), employee.getAddress(), employee.getPhone(), employee.getEmployeeGroup(), employee.getSalary());
        return "resultEmployee";
    }


}
