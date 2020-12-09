package csc460.SqlPlusShoppingMall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

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


    /**
     * Write a query that displays the member by searching the phone number or the ID. The results should
     * display the member name, date of birth, and reward points
     */
    @PostMapping("/1")
    public String query1(@RequestParam(value = "id", required = false) long id,
                         @RequestParam(value = "phone", required = false) long phone,
                         Model model) {
        
        String sql = String.format("SELECT * FROM chaonengquan.Supplier WHERE id = %d OR Phone = %d", id, phone);

        List<Member> memberList = this.jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    Member member = new Member();
                    member.setFirstName(rs.getString("FirstName"));
                    member.setLastName(rs.getString("LastName"));
                    member.setDateOfBirth(rs.getDate("DateOfBirth"));
                    member.setRewardPoint(rs.getLong("RewardPoint"));
                    return member;
                });

        model.addAttribute("memberList", memberList);
        return "query1";
    }

    @PostMapping("/3")
    public String query3(Model model) {
        System.out.println("Clicked Query#3");
        return "hello";
    }

    @PostMapping("/4")
    public String query4(Model model) {
        System.out.println("Clicked Query#4");
        return "hello";
    }


}
