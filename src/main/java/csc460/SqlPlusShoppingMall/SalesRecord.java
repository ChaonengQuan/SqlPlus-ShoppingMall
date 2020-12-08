package csc460.SqlPlusShoppingMall;

import javax.validation.constraints.NotNull;
import java.sql.Date;


public class SalesRecord {
    @NotNull
    private long id;
    private Date orderDate;
    private String paymentMethod;
    private float totalAmount;
    private long memberID;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public long getMemberID() {
        return memberID;
    }

    public void setMemberID(long memberID) {
        this.memberID = memberID;
    }

    @Override
    public String toString() {
        return "SalesRecord{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", totalAmount=" + totalAmount +
                ", memberID=" + memberID +
                '}';
    }
}
