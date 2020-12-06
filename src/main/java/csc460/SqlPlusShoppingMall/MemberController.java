package csc460.SqlPlusShoppingMall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.validation.Valid;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("")
    public String memberFormGet(Model model) {
        model.addAttribute("member", new Member());
        return "member";
    }

    @PostMapping()
    public String memberFormSubmit(@ModelAttribute @Valid Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "member";
        } else {
            String sql = "INSERT INTO chaonengquan.Member (FirstName, LastName, DateOfBirth, Address, Phone, RewardPoint) VALUES (?, ?, ? ,? ,? ,?)";
            jdbcTemplate.update(sql, member.getFirstName(), member.getLastName(), member.getDateOfBirth(), member.getAddress(), member.getPhone(), member.getRewardPoint());
            return "memberResult";
        }
    }


}
