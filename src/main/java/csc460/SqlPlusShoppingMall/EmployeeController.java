package csc460.SqlPlusShoppingMall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*--Add--*/
    @GetMapping("/addEmployee")
    public String addEmployeeFormGet(Model model) {
        model.addAttribute("employee", new Employee());
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployeeFormPost(@ModelAttribute @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "addEmployee";
        } else {
            String sql = "INSERT INTO chaonengquan.Employee (FirstName, LastName, Gender, Address, Phone, EmployeeGroup, Salary) VALUES (?, ?, ? ,? ,? ,? ,?)";
            jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getGender(), employee.getAddress(), employee.getPhone(), employee.getEmployeeGroup(), employee.getSalary());
            return "addEmployeeResult";
        }
    }

    /*--Delete--*/
    @GetMapping("/deleteEmployee")
    public String deleteEmployeeFormGet(Model model) {
        model.addAttribute("employee", new Employee());
        return "deleteEmployee";
    }

    @PostMapping("/deleteEmployee")
    public String deleteEmployeeFormPost(@ModelAttribute @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "deleteEmployee";
        } else {
            String sql = "DELETE FROM chaonengquan.Employee WHERE FirstName = ? AND LastName = ? AND Phone = ?";
            jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getPhone());
            return "deleteEmployeeResult";
        }
    }

    /*--Update--*/
    @GetMapping("/updateEmployee")
    public String updateEmployeeFormGet(Model model) {
        model.addAttribute("employee", new Employee());
        return "updateEmployee";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployeeFormPost(@ModelAttribute @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "updateEmployee";
        } else {
            String sql = "UPDATE chaonengquan.Employee SET Gender = ?, Address = ?, EmployeeGroup = ?, Salary = ? WHERE FirstName = ? AND LastName = ? AND Phone = ?";
            jdbcTemplate.update(sql, employee.getGender(), employee.getAddress(), employee.getEmployeeGroup(), employee.getSalary(), employee.getFirstName(), employee.getLastName(), employee.getPhone());
            return "updateEmployeeResult";
        }
    }

    /*--Query--*/
    @GetMapping("/allEmployee")
    public String queryResults(Model model) {
        List<Employee> allEmployee = this.jdbcTemplate.query(
                "select * from chaonengquan.Employee",
                new RowMapper<Employee>() {
                    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Employee employee = new Employee();
                        
                        String firstName = rs.getString("FirstName");
                        String lastName = rs.getString("LastName");
                        String gender = rs.getString("Gender");
                        String address = rs.getString("Address");
                        long phone = rs.getLong("Phone");   //long corresponds to integer in oracle
                        String employeeGroup = rs.getString("EmployeeGroup");
                        long salary = rs.getLong("Salary");
                        
                        employee.setFirstName(firstName);
                        employee.setLastName(lastName);
                        employee.setGender(gender);
                        employee.setAddress(address);
                        employee.setPhone(phone);
                        employee.setEmployeeGroup(employeeGroup);
                        employee.setSalary(salary);


                        return employee;
                    }
                });
        model.addAttribute("employeeList", allEmployee);
        return "allEmployeeResult";
    }


}
