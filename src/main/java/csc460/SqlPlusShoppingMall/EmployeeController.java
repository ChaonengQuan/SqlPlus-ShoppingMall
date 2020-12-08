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
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /*--Add--*/
    @PostMapping("/add")
    public String addEmployeeFormPost(@ModelAttribute @Valid Employee toAdd, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "addEmployee";
        } else {
            String sql = "INSERT INTO chaonengquan.Employee (id, FirstName, LastName, Gender, Address, Phone, EmployeeGroup, Salary) VALUES (?, ?, ?, ? ,? ,? ,? ,?)";
            jdbcTemplate.update(sql, toAdd.getId(), toAdd.getFirstName(), toAdd.getLastName(), toAdd.getGender(), toAdd.getAddress(), toAdd.getPhone(), toAdd.getEmployeeGroup(), toAdd.getSalary());
            return "redirect:all";
        }
    }

    /*--Delete--*/
    @PostMapping("/delete")
    public String deleteEmployeeFormPost(@ModelAttribute Employee employee) {
        String sql = "DELETE FROM chaonengquan.Employee WHERE id = ?";
        jdbcTemplate.update(sql, employee.getId());
        return "redirect:all";
    }

    /*--Update--*/
    @GetMapping("/update")
    public String updateEmployeeFormGet(@ModelAttribute Employee toUpdate, Model model) {
        model.addAttribute("toUpdate", toUpdate);
        return "updateEmployee";
    }

    @PostMapping("/update")
    public String updateEmployeeFormPost(@ModelAttribute @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "updateEmployee";
        } else {
            String sql = "UPDATE chaonengquan.Employee SET FirstName = ?, LastName = ?, Phone = ?,  Gender = ?, Address = ?, EmployeeGroup = ?, Salary = ? WHERE id = ?";
            jdbcTemplate.update(sql, employee.getFirstName(), employee.getLastName(), employee.getPhone(), employee.getGender(), employee.getAddress(), employee.getEmployeeGroup(), employee.getSalary(), employee.getId());
            return "redirect:all";
        }
    }

    /*--Query--*/
    @GetMapping("/all")
    public String queryResults(Model model) {
        model.addAttribute("toAdd", new Employee());
        List<Employee> allEmployee = this.jdbcTemplate.query(
                "select * from chaonengquan.Employee",
                new RowMapper<Employee>() {
                    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Employee employee = new Employee();
                        employee.setFirstName(rs.getString("FirstName"));
                        employee.setLastName(rs.getString("LastName"));
                        employee.setGender(rs.getString("Gender"));
                        employee.setAddress(rs.getString("Address"));
                        employee.setPhone(rs.getLong("Phone")); //long corresponds to integer in oracle
                        employee.setEmployeeGroup(rs.getString("EmployeeGroup"));
                        employee.setSalary(rs.getLong("Salary"));
                        return employee;
                    }
                });
        model.addAttribute("employeeList", allEmployee);
        return "allEmployeeResult";
    }


}
