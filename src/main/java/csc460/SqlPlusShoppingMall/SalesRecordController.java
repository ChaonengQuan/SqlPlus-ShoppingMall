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
@RequestMapping("/salesRecord")
public class SalesRecordController {
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/update")
    public String updateSalesRecord(@ModelAttribute SalesRecord toUpdate, Model model){
        model.addAttribute("toUpdate", toUpdate);
        return "updateSalesRecord";
    }

    @PostMapping("/update")
    public String updateSalesRecord(@ModelAttribute SalesRecord salesRecord){
        String sql = "UPDATE chaonengquan.SalesRecord SET OrderDate = ?, PaymentMethod = ?, TotalAmount = ?, MemberID = ? WHERE id = ?";
        //debug output
        jdbcTemplate.update(sql, salesRecord.getOrderDate(), salesRecord.getPaymentMethod(), salesRecord.getTotalAmount(), salesRecord.getMemberID(), salesRecord.getId());
        return  "redirect:all";
    }


    @PostMapping("/delete")
    public String deleteSalesRecord(@ModelAttribute SalesRecord salesRecord) {

        //Step1: delete the target
        String sql = "DELETE FROM chaonengquan.SalesRecord WHERE id = ?";
        jdbcTemplate.update(sql, salesRecord.getId());

        return "redirect:all";
    }


    @GetMapping("/all")
    public String getAllSalesRecord(Model model) {
        List<SalesRecord> salesRecordList = this.jdbcTemplate.query(
                "SELECT * FROM chaonengquan.SalesRecord",
                (rs, rowNum) -> {
                    SalesRecord salesRecord = new SalesRecord();

                    salesRecord.setId(rs.getLong("id"));
                    salesRecord.setOrderDate(rs.getDate("OrderDate"));
                    salesRecord.setPaymentMethod(rs.getString("PaymentMethod"));
                    salesRecord.setTotalAmount(rs.getLong("TotalAmount"));
                    salesRecord.setMemberID(rs.getLong("MemberId"));

                    return salesRecord;
                });
        model.addAttribute("salesRecordList", salesRecordList);
        return "allSalesRecordResult";
    }


}
