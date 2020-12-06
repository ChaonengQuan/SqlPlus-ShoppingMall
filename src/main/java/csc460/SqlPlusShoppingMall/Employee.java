package csc460.SqlPlusShoppingMall;

import javax.validation.constraints.*;

public class Employee {

    private long id;
    @NotBlank(message = "First Name cannot be blank!")
    @Size(max=50)
    private String firstName;
    @NotBlank(message = "Last Name cannot be blank!")
    @Size(max=50)
    private String lastName;
    @Size(max=10)
    private String gender;
    @Size(max=100)
    private String address;
    @NotNull
    private long phone;
    @Size(max=50)
    private String employeeGroup;
    @PositiveOrZero
    private long salary;

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmployeeGroup() {
        return employeeGroup;
    }

    public void setEmployeeGroup(String employeeGroup) {
        this.employeeGroup = employeeGroup;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phone=" + phone +
                ", employeeGroup='" + employeeGroup + '\'' +
                ", salary=" + salary +
                '}';
    }
}
