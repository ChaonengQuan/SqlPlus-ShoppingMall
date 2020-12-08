package csc460.SqlPlusShoppingMall;

import javax.validation.constraints.*;
import java.sql.Date;

public class Member {

    @NotNull
    private long id;
    @NotBlank(message = "First Name cannot be blank!")
    private String firstName;
    @NotBlank(message = "Last Name cannot be blank!")
    private String lastName;
    @PastOrPresent
    private Date DateOfBirth;
    private String Address;
    @NotNull
    private long phone;
    private long rewardPoint;
    private String membershipPaid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public long getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(long rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    public String getMembershipPaid() {
        return membershipPaid;
    }

    public void setMembershipPaid(String membershipPaid) {
        this.membershipPaid = membershipPaid;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", Address='" + Address + '\'' +
                ", phone=" + phone +
                ", rewardPoint=" + rewardPoint +
                ", membershipPaid='" + membershipPaid + '\'' +
                '}';
    }
}
