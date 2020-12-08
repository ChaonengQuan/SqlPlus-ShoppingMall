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


    @PostMapping("/add")
    public String addProductFormPost(@ModelAttribute @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "redirect:all";
        } else {
            String sql = "INSERT INTO chaonengquan.Product (id, Name, RetailPrice, Category, MemberDiscount, StockInfo, SupplierID) VALUES (?, ?, ? ,? ,? ,? ,?)";
            jdbcTemplate.update(sql, product.getId(), product.getName(), product.getRetailPrice(), product.getCategory(), product.getMemberDiscount(), product.getStockInfo(), product.getSupplierID());
            return "redirect:all";
        }
    }

    @PostMapping("/delete")
    public String deleteProductFormPost(@ModelAttribute Product product) {
        String sql = "DELETE FROM chaonengquan.Product WHERE id = ?";
        jdbcTemplate.update(sql, product.getId());
        return "redirect:all";
    }


    @GetMapping("/update")
    public String updateFormGet(@ModelAttribute Product toUpdate, Model model) {
        model.addAttribute("toUpdate", toUpdate);
        return "updateProduct";
    }

    @PostMapping("/update")
    public String updateFormPost(@ModelAttribute @Valid Product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "updateProduct";
        } else {
            String sql = "UPDATE chaonengquan.Product SET Name = ? , RetailPrice = ?, Category = ?, MemberDiscount = ?, StockInfo = ?, SupplierID = ? WHERE id = ?";
            jdbcTemplate.update(sql, product.getName(), product.getRetailPrice(), product.getCategory(), product.getMemberDiscount(), product.getStockInfo(), product.getSupplierID(), product.getId());
            return "redirect:all";
        }
    }


    @GetMapping("/all")
    public String getAllProduct(Model model) {
        model.addAttribute("toAdd", new Product()); //for later insertion

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
