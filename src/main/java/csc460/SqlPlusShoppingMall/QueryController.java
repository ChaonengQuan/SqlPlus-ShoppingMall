package csc460.SqlPlusShoppingMall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Controller
@RequestMapping("/query")
public class QueryController {
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @PostMapping("/1")
    public String query1(Model model){
        System.out.println("Clicked Query#1");
        return "hello";
    }

    @PostMapping("/3")
    public String query3(Model model){
        System.out.println("Clicked Query#3");
        return "hello";
    }

    @PostMapping("/4")
    public String query4(Model model){
        System.out.println("Clicked Query#4");
        return "hello";
    }




}
