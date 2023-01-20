package JavaBeans;

import enums.Category;

import java.sql.Date;

public class Coupon {
    private int id, companyId;
    private Category category;
    private String title, description;
    private Date startDate, endDate;
    private int amount;
    private double price;
    private String image;


    //cons to select from db
    public Coupon(int id, int companyId, Category category, String title, String description, Date startDate,
                  Date endDate, int amount, double price, String image) {
        this.id = id;
        this.companyId = companyId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    //cons to insert into db
    public Coupon(int companyId, Category category, String title, String description, Date startDate,
                  Date endDate, int amount, double price, String image) {
        this.companyId = companyId;
        this.category = category;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Coupon's id: " + id +
                ", Company Id: " + companyId +
                ", Category: " + category +
                ", Title: " + title+
                ", Description: " + description+
                ", Start Date: " + startDate +
                ", End Date: " + endDate +
                ", Amount: " + amount +
                ", Price: " + price +
                ", Image: " + image;
    }
}
