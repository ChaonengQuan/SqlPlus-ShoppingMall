package csc460.SqlPlusShoppingMall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class PersonController {

    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/addPerson")
    public String personForm(Model model) {
        model.addAttribute("person", new Person());
        return "addPerson";
    }

    @PostMapping("/addPerson")
    public String personSubmit(@ModelAttribute Person person) {
        jdbcTemplate.update("insert into baileynottingham.people values (?, ?)", person.getFirstName(), person.getLastName());

        return "resultPerson";
    }

    @GetMapping("/deletePerson")
    public String personFormDelete(Model model) {
        model.addAttribute("person", new Person());
        return "deletePerson";
    }

    @PostMapping("/deletePerson")
    public String personDelete(@ModelAttribute Person person) {
        jdbcTemplate.update("delete from baileynottingham.people where first_name = ? and last_name = ?", person.getFirstName(), person.getLastName());

        return "deletePersonResult";
    }

    @GetMapping("/queryResults")
    public String queryResults(Model model) {
        List<String> allNames = this.jdbcTemplate.query(
                "select * from baileynottingham.people",
                new RowMapper<String>() {
                    public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String first_name = rs.getString("first_name");
                        String last_name = rs.getString("last_name");
                        return (first_name + " " + last_name);
                    }
                });
        model.addAttribute("names", allNames);
        return "/queryResults";
    }

}
