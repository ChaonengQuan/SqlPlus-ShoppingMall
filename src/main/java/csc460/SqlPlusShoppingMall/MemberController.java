package csc460.SqlPlusShoppingMall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

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

    @GetMapping("/add")
    public String memberFormGet(Model model) {
        model.addAttribute("member", new Member());
        return "addMember";
    }

    /*--Add--*/
    @PostMapping("/add")
    public String memberFormSubmit(@ModelAttribute @Valid Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "addMember";
        } else {
            String sql = "INSERT INTO chaonengquan.Member (FirstName, LastName, DateOfBirth, Address, Phone, RewardPoint, MembershipPaid) VALUES (?, ?, ? ,? ,? ,?, ?)";
            jdbcTemplate.update(sql, member.getFirstName(), member.getLastName(), member.getDateOfBirth(), member.getAddress(), member.getPhone(), member.getRewardPoint(), member.getMembershipPaid());
            return "addMemberResult";
        }
    }

    /*--Delete--*/
    @GetMapping("/delete")
    public String memberFormDelete(Model model) {
        model.addAttribute("member", new Member());
        return "deleteMember";
    }

    @PostMapping("/delete")
    public String memberDelete(@ModelAttribute @Valid Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "deleteMember";
        } else {
            String sql = "DELETE FROM chaonengquan.Member WHERE FirstName = ? AND LastName = ? AND Phone = ?";
            jdbcTemplate.update(sql, member.getFirstName(), member.getLastName(), member.getPhone());
            return "deleteMemberResult";
        }
    }

    /*--Update--*/
    @GetMapping("/update")
    public String memberFormUpdate(Model model){
        model.addAttribute("member", new Member());
        return "updateMember";
    }

    @PostMapping("/update")
    public String memberUpdate(@ModelAttribute @Valid Member member, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "updateMember";
        } else {
            String sql = "UPDATE chaonengquan.Member SET DateOfBirth = ?, Addree = ?, RewardPoint = ?, MembershipPaid = ? WHERE FirstName = ? AND LastName = ? AND Phone = ?";
            jdbcTemplate.update(sql, member.getDateOfBirth(), member.getAddress(), member.getRewardPoint(), member.getMembershipPaid(), member.getFirstName(), member.getLastName(), member.getPhone());
            return "updateMemberResult";
        }
    }

    /*--Query--*/
    @GetMapping("/all")
    public String getAllMember(Model model) {
        List<Member> allMember = this.jdbcTemplate.query(
                "select * from chaonengquan.Member",
                (rs, rowNum) -> {
                    Member member = new Member();
                    String firstName = rs.getString("FirstName");
                    String lastName = rs.getString("LastName");
                    Date dateOfBirth = rs.getDate("DateOfBirth");
                    String address = rs.getString("Address");
                    long phone = rs.getLong("Phone");   //long corresponds to integer in oracle
                    long rewardPoint = rs.getLong("RewardPoint");
                    String membershipPaid = rs.getString("MembershipPaid");
                    member.setFirstName(firstName);
                    member.setLastName(lastName);
                    member.setDateOfBirth(dateOfBirth);
                    member.setAddress(address);
                    member.setPhone(phone);
                    member.setRewardPoint(rewardPoint);
                    member.setMembershipPaid(membershipPaid);
                    return member;
                });
        model.addAttribute("allMember", allMember);
        return "allMemberResult";
    }


}
