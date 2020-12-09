package csc460.SqlPlusShoppingMall;

import javax.validation.constraints.NotNull;

public class OrderItem {

    @NotNull
    private long id;
    private long salesRecordId;
    private long productId;
    private float paidPrice;
    private long quantity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSalesRecordId() {
        return salesRecordId;
    }

    public void setSalesRecordId(long salesRecordId) {
        this.salesRecordId = salesRecordId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public float getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(float paidPrice) {
        this.paidPrice = paidPrice;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", salesRecordId=" + salesRecordId +
                ", productId=" + productId +
                ", paidPrice=" + paidPrice +
                ", quantity=" + quantity +
                '}';
    }
}
