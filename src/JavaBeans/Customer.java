package JavaBeans;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private int id;
    private String firstName, lastName, email, password;
    private List<Coupon> coupons = new ArrayList<>();

    //constructor to select from db
    public Customer(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    //constructor to insert into db
    public Customer(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    @Override
    public String toString() {
        return "Customer's Id: " + id +
                ", First Name: " + firstName+
                ", Last Name: " + lastName+
                ", Email: " + email+
                ", Password: " + password +
                ", Coupons: " + (coupons.size() > 0 ? coupons : "0");
    }

    public void setId(int id) {
        this.id = id;
    }
}
