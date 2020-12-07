package csc460.SqlPlusShoppingMall;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Date;

public class Supplier {

    @NotNull
    private long id;
    @NotBlank(message = "Name cannot be blank!")
    private String name;
    private Date restockDate;
    private long supplyPrice;
    private long amount;
    private long productID;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRestockDate() {
        return restockDate;
    }

    public void setRestockDate(Date restockDate) {
        this.restockDate = restockDate;
    }

    public long getSupplyPrice() {
        return supplyPrice;
    }

    public void setSupplyPrice(long supplyPrice) {
        this.supplyPrice = supplyPrice;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getProductID() {
        return productID;
    }

    public void setProductID(long productID) {
        this.productID = productID;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", restockDate=" + restockDate +
                ", supplyPrice=" + supplyPrice +
                ", amount=" + amount +
                ", productID=" + productID +
                '}';
    }
}
