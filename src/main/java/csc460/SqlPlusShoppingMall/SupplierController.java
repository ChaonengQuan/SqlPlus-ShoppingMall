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
import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
    @Autowired
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @GetMapping("/add")
    public String addSupplierFormGet(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "addSupplier";
    }

    @PostMapping("/add")
    public String addSupplierFormPost(@ModelAttribute @Valid Supplier supplier, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "addSupplier";
        } else {
            String sql = "INSERT INTO chaonengquan.Supplier (id, Name, RestockDate, SupplyPrice, Amount, ProductID) VALUES (?, ?, ? ,? ,? ,?)";
            jdbcTemplate.update(sql, supplier.getId(), supplier.getName(), supplier.getRestockDate(), supplier.getSupplyPrice(), supplier.getAmount(), supplier.getProductID());
            return "addSupplierResult";
        }
    }

    @GetMapping("/delete")
    public String deleteSupplierFormGet(Model model){
        model.addAttribute("supplier", new Supplier());
        return "deleteSupplier";
    }

    @PostMapping("/delete")
    public String deleteSupplierFormPost(@ModelAttribute @Valid Supplier supplier, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "deleteSupplier";
        } else {
            String sql = "DELETE FROM chaonengquan.Supplier WHERE id = ?";
            jdbcTemplate.update(sql, supplier.getId());
            return "deleteSupplierResult";
        }
    }

    @GetMapping("/update")
    public String updateFormGet(Model model){
        model.addAttribute("supplier", new Supplier());
        return "updateSupplier";
    }

    @PostMapping("/update")
    public String updateFormPost(@ModelAttribute @Valid Supplier supplier, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {    //if inputs are not valid
            return "updateSupplier";
        } else {
            String sql = "UPDATE chaonengquan.Supplier SET Name = ? , RestockDate = ?, SupplyPrice = ?, Amount = ?, ProductID = ? WHERE id = ?";
            jdbcTemplate.update(sql, supplier.getName(), supplier.getRestockDate(), supplier.getSupplyPrice(), supplier.getAmount(), supplier.getProductID(), supplier.getId());
            return "updateSupplierResult";
        }
    }

    @GetMapping("/all")
    public String getAllSupplier(Model model) {
        List<Supplier> allSupplier = this.jdbcTemplate.query(
                "SELECT * FROM chaonengquan.Supplier",
                (rs, rowNum) -> {
                    Supplier supplier = new Supplier();
                    supplier.setId(rs.getLong("id"));
                    supplier.setName(rs.getString("Name"));
                    supplier.setRestockDate(rs.getDate("RestockDate"));
                    supplier.setSupplyPrice(rs.getLong("SupplyPrice"));
                    supplier.setAmount(rs.getLong("Amount"));
                    supplier.setProductID(rs.getLong("ProductID"));
                    return supplier;
                });
        model.addAttribute("allSupplier", allSupplier);
        return "allSupplierResult";

    }


}
