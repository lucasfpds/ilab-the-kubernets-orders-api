package br.com.api.orders.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Integer idOrder;

    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @Column(name = "total_value", nullable = false)
    private Integer totalValue;

    @Column(name = "orders_date", columnDefinition = "TIMESTAMP", nullable = false)
    private Date ordersDate;

    @Column(name = "status", length = 20, nullable = false)
    @org.hibernate.annotations.ColumnDefault("aberto")
    private String status = "aberto";

    public Order() {
        
    }

    public Order(Integer idUser, String description, Integer totalValue, Date ordersDate) {
        this.idUser = idUser;
        this.description = description;
        this.totalValue = totalValue;
        this.ordersDate = ordersDate;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Integer totalValue) {
        this.totalValue = totalValue;
    }

    public Date getOrdersDate() {
        return ordersDate;
    }

    public void setOrdersDate(Date ordersDate) {
        this.ordersDate = ordersDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
