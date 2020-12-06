package csc460.SqlPlusShoppingMall;

import javax.validation.constraints.*;

public class Employee {

    private int id;
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
    private int phone;
    @Size(max=50)
    private String employeeGroup;
    @PositiveOrZero
    private int salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmployeeGroup() {
        return employeeGroup;
    }

    public void setEmployeeGroup(String employeeGroup) {
        this.employeeGroup = employeeGroup;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
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
