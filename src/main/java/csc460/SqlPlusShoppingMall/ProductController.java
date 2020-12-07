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
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/add")
    public String addProductFormGet(Model model) {
        model.addAttribute("product", new Product());
        return "addProduct";
    }

    @PostMapping("/add")
    public String addProductFormPost(@ModelAttribute @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "addProduct";
        } else {
            String sql = "INSERT INTO chaonengquan.Product (id, Name, RetailPrice, Category, MemberDiscount, StockInfo, SupplierID) VALUES (?, ?, ? ,? ,? ,? ,?)";
            jdbcTemplate.update(sql, product.getId(), product.getName(), product.getRetailPrice(), product.getCategory(), product.getMemberDiscount(), product.getStockInfo(), product.getSupplierID());
            return "addProductResult";
        }
    }


    @GetMapping("/all")
    public String getAllProduct(Model model) {
        List<Product> allProduct = this.jdbcTemplate.query(
                "select * from chaonengquan.Product",
                (rs, rowNum) -> {
                    Product product = new Product();

                    product.setId(rs.getLong("id"));
                    product.setName(rs.getString("Name"));
                    product.setRetailPrice(rs.getFloat("RetailPrice"));
                    product.setCategory(rs.getString("Category"));
                    product.setMemberDiscount(rs.getLong("MemberDiscount"));
                    product.setStockInfo(rs.getString("StockInfo"));
                    product.setSupplierID(rs.getLong("SupplierID"));

                    return product;
                });
        model.addAttribute("allProduct", allProduct);
        return "allProductResult";
    }


}
