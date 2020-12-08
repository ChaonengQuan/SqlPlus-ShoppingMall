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

    /*--Add--*/
    @PostMapping("/add")
    public String memberFormSubmit(@ModelAttribute @Valid Member toAdd, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            String sql = "INSERT INTO chaonengquan.Member (id, FirstName, LastName, DateOfBirth, Address, Phone, RewardPoint, MembershipPaid) VALUES (?, ?, ?, ? ,? ,? ,?, ?)";
            jdbcTemplate.update(sql, toAdd.getId(), toAdd.getFirstName(), toAdd.getLastName(), toAdd.getDateOfBirth(), toAdd.getAddress(), toAdd.getPhone(), toAdd.getRewardPoint(), toAdd.getMembershipPaid());
        }
        //if inputs are not valid
        return "redirect:all";
    }

    /*--Delete--*/
    @PostMapping("/delete")
    public String memberDelete(@ModelAttribute Member member) {
        String sql = "DELETE FROM chaonengquan.Member WHERE id = ?";
        jdbcTemplate.update(sql, member.getId());
        return "redirect:all";
    }

    /*--Update--*/
    @GetMapping("/update")
    public String memberFormUpdate(@ModelAttribute Member toUpdate, Model model) {
        model.addAttribute("member", toUpdate);
        return "updateMember";
    }

    @PostMapping("/update")
    public String memberUpdate(@ModelAttribute @Valid Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "updateMember";
        } else {
            String sql = "UPDATE chaonengquan.Member SET FirstName = ?, LastName = ?, Phone = ?, DateOfBirth = ?, Address = ?, RewardPoint = ?, MembershipPaid = ? WHERE id = ?";
            jdbcTemplate.update(sql, member.getFirstName(), member.getLastName(), member.getPhone(), member.getDateOfBirth(), member.getAddress(), member.getRewardPoint(), member.getMembershipPaid(), member.getId());
            return "redirect:all";
        }
    }

    /*--Query--*/
    @GetMapping("/all")
    public String getAllMember(Model model) {
        model.addAttribute("toAdd", new Member());  //for the later insert
        List<Member> allMember = this.jdbcTemplate.query(
                "select * from chaonengquan.Member",
                (rs, rowNum) -> {
                    Member member = new Member();
                    member.setId(rs.getLong("id"));
                    member.setFirstName(rs.getString("FirstName"));
                    member.setLastName(rs.getString("LastName"));
                    member.setDateOfBirth(rs.getDate("DateOfBirth"));
                    member.setAddress(rs.getString("Address"));
                    member.setPhone(rs.getLong("Phone"));
                    member.setRewardPoint(rs.getLong("RewardPoint"));
                    member.setMembershipPaid(rs.getString("MembershipPaid"));
                    return member;
                });
        model.addAttribute("allMember", allMember);
        return "allMemberResult";
    }


}
