package csc460.SqlPlusShoppingMall;

public class OrderItem {

    private long salesRecordId;
    private long productId;
    private float paidPrice;
    private long quantity;

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
                "salesRecordId=" + salesRecordId +
                ", productId=" + productId +
                ", paidPrice=" + paidPrice +
                ", quantity=" + quantity +
                '}';
    }
}
