package ru.geekbrains.domodel.entities;

import javax.persistence.*;

/**
 * Сущность счета
 */
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "target", nullable = false)
    private String target;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "payment_status", nullable = false)
    private String paymentStatus;

    @Column(name = "credentials", nullable = false)
    private String credentials;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Bill() {
    }
}
