package csc460.SqlPlusShoppingMall;

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
        //Get sales price from Product table
        String priceSql = String.format("SELECT RetailPrice, MemberDiscount FROM Product WHERE Product.id = %d", toAdd.getProductId());
        List<Product> productList = this.jdbcTemplate.query(priceSql,
                (rs, rowNum) -> {
                    Product product = new Product();
                    product.setRetailPrice(rs.getLong("RetailPrice"));
                    product.setMemberDicount(rs.getLong("MemberDiscount"));
                    return product;
                });

        String sql = "INSERT INTO chaonengquan.OrderItem (id, SalesRecordID, ProductID, PaidPrice, Quantity) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, toAdd.getId(), toAdd.getSalesRecordId(), toAdd.getProductId(), productList.get(0).getRetailPrice() - productList.get(0).getMemberDicount(), toAdd.getQuantity());

        //update SalesRecord totalAmount
        String updateSalesRecordSql = "UPDATE chaonengquan.SalesRecord SET TotalAmount = (SELECT SUM(OrderItem.PaidPrice * OrderItem.Quantity) FROM chaonengquan.OrderItem, chaonengquan.SalesRecord WHERE OrderItem.SalesRecordId = SalesRecord.Id)";
        jdbcTemplate.execute(updateSalesRecordSql);
        jdbcTemplate.execute("commit");

        //update Product stockInfo
        String updateSql = String.format("UPDATE chaonengquan.Product SET StockInfo = (SELECT Product.StockInfo - OrderItem.Quantity FROM chaonengquan.Product, chaonengquan.Orderitem WHERE Product.Id = OrderItem.ProductId AND OrderItem.Id = %d) WHERE Product.Id IN (SELECT OrderItem.ProductId FROM chaonengquan.OrderItem WHERE OrderItem.Id = %d)",
                toAdd.getId(), toAdd.getId());
        jdbcTemplate.execute(updateSql);
        jdbcTemplate.execute("commit");

        return "redirect:all";
    }

    //Delete
    @PostMapping("/delete")
    public String deleteOrderItem(@ModelAttribute OrderItem toDelete) {
        String sql = "DELETE FROM chaonengquan.OrderItem WHERE id = ?";
        jdbcTemplate.update(sql, toDelete.getId());
        return "redirect:all";
    }

    //Update
    @GetMapping("/update")
    public String updateOrderItemForm(@ModelAttribute OrderItem toUpdate, Model model) {
        model.addAttribute("toUpdate", toUpdate);
        return "updateOrderItem";
    }

    @PostMapping("/update")
    public String updateOrderItem(@ModelAttribute OrderItem toUpdate) {
        String sql = "UPDATE chaonengquan.OrderItem SET SalesRecordId = ?, ProductId = ?, PaidPrice = ?, Quantity = ? WHERE id = ?";
        jdbcTemplate.update(sql, toUpdate.getSalesRecordId(), toUpdate.getProductId(), toUpdate.getPaidPrice(), toUpdate.getQuantity(), toUpdate.getId());

        //update SalesRecord totalAmount
        String updateSalesRecordSql = "UPDATE chaonengquan.SalesRecord SET TotalAmount = (SELECT SUM(OrderItem.PaidPrice * OrderItem.Quantity) FROM chaonengquan.OrderItem, chaonengquan.SalesRecord WHERE OrderItem.SalesRecordId = SalesRecord.Id)";
        jdbcTemplate.execute(updateSalesRecordSql);
        jdbcTemplate.execute("commit");

        //update Product stockInfo
        String updateSql = String.format("UPDATE chaonengquan.Product SET StockInfo = (SELECT Product.StockInfo - OrderItem.Quantity FROM chaonengquan.Product, chaonengquan.Orderitem WHERE Product.Id = OrderItem.ProductId AND OrderItem.Id = %d) WHERE Product.Id IN (SELECT OrderItem.ProductId FROM chaonengquan.OrderItem WHERE OrderItem.Id = %d)",
                toUpdate.getId(), toUpdate.getId());
        jdbcTemplate.execute(updateSql);
        jdbcTemplate.execute("commit");

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
                        orderItem.setId(rs.getLong("id"));
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
