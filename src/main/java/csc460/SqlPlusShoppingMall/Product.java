package csc460.SqlPlusShoppingMall;

import javax.validation.constraints.*;

public class Product {
    @Positive
    @NotNull(message = "ID cannot be blank!")
    private long id;
    @NotBlank(message = "Name cannot be blank!")
    private String name;
    private float retailPrice;
    private String category;
    private long memberDiscount;
    private String stockInfo;
    private long supplierID;

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

    public float getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(float retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getMemberDiscount() {
        return memberDiscount;
    }

    public void setMemberDiscount(long memberDiscount) {
        this.memberDiscount = memberDiscount;
    }

    public String getStockInfo() {
        return stockInfo;
    }

    public void setStockInfo(String stockInfo) {
        this.stockInfo = stockInfo;
    }

    public long getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(long supplierID) {
        this.supplierID = supplierID;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", retailPrice=" + retailPrice +
                ", category='" + category + '\'' +
                ", memberDiscount=" + memberDiscount +
                ", stockInfo='" + stockInfo + '\'' +
                ", supplierID=" + supplierID +
                '}';
    }
}
