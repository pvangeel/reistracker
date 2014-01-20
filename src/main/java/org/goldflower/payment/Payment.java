package org.goldflower.payment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Payment {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private double amount;
    private Date paymentDate = new Date();
    private boolean approved = true;
    private String approvedBy = "System";

    public Payment() {}

    public Payment(String name, String email, double amount) {
        this.name = name;
        this.email = email;
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public double getAmount() {
        return amount;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public Long getId() {
        return id;
    }
}
