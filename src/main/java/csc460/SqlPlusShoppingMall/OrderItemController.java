package csc460.SqlPlusShoppingMall;

import com.sun.tools.corba.se.idl.constExpr.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.jws.WebParam;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/orderItem")
public class OrderItemController {


    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //Add
    @PostMapping("/add")
    public String addOrderItem(@ModelAttribute OrderItem toAdd) {
        String sql = "INSERT INTO chaonengquan.OrderItem (SalesRecordID, ProductID, PaidPrice, Quantity) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, toAdd.getSalesRecordId(), toAdd.getProductId(), toAdd.getPaidPrice(), toAdd.getQuantity());
        return "redirect:all";
    }

    //Delete
    @PostMapping("/delete")
    public String deleteOrderItem(@ModelAttribute OrderItem toDelete) {
        String sql = "DELETE FROM chaonengquan.OrderItem WHERE SalesRecordId = ? AND ProductId = ?";
        jdbcTemplate.update(sql, toDelete.getSalesRecordId(), toDelete.getProductId());
        return "redirect:all";
    }

    //Update
    @GetMapping("/update")
    public String updateOrderItemForm(@ModelAttribute OrderItem toUpdate, Model model) {
        model.addAttribute("toUpdate", toUpdate);
        return "updateEmployee";
    }

    @PostMapping("/update")
    public String updateOrderItem(@ModelAttribute OrderItem toUpdate) {
        String sql = "UPDATE chaonengquan.OrderItem SET PaidPrice = ?, Quantity = ? WHERE SalesRecordId = ? AND ProductId = ?";
        jdbcTemplate.update(sql, toUpdate.getPaidPrice(), toUpdate.getQuantity(), toUpdate.getSalesRecordId(), toUpdate.getProductId());
        return "redirect:all";
    }

    //Get all
    @GetMapping("/all")
    public String getAllOrderItem(Model model) {
        model.addAttribute("toAdd", new OrderItem());

        List<OrderItem> orderItemList = this.jdbcTemplate.query(
                "select * from chaonengquan.OrderItem",
                new RowMapper<OrderItem>() {
                    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
                        OrderItem orderItem = new OrderItem();
                        orderItem.setSalesRecordId(rs.getLong("SalesRecordId"));
                        orderItem.setProductId(rs.getLong("ProductId"));
                        orderItem.setPaidPrice(rs.getFloat("PaidPrice"));
                        orderItem.setQuantity(rs.getLong("Quantity"));
                        return orderItem;
                    }
                });
        model.addAttribute("orderItemList", orderItemList);
        return "allOrderItem";
    }


}
