package br.com.api.orders.model;

import java.sql.Timestamp;

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
    private Timestamp ordersDate;

    @Column(name = "status", length = 20, nullable = false)
    @org.hibernate.annotations.ColumnDefault("aberto")
    private String status = "aberto";

    @Column(name = "status_email", length = 50, nullable = false)
    @org.hibernate.annotations.ColumnDefault("não enviado")
    private String statusEmail = " não enviado";

    public Order() {
        
    }

    public Order(Integer idUser, String description, Integer totalValue, Timestamp ordersDate) {
        this.idUser = idUser;
        this.description = description;
        this.totalValue = totalValue;
        this.ordersDate = ordersDate;
    }


    public Order(Integer idUser, String description, Integer totalValue, Timestamp ordersDate, String status, String statusEmail) {
        this.idUser = idUser;
        this.description = description;
        this.totalValue = totalValue;
        this.ordersDate = ordersDate;
        this.status = status;
        this.statusEmail = statusEmail;
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

    public Timestamp getOrdersDate() {
        return ordersDate;
    }

    public void setOrdersDate(Timestamp ordersDate) {
        this.ordersDate = ordersDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusEmail() {
        return statusEmail;
    }

    public void setStatusEmail(String statusEmail) {
        this.statusEmail = statusEmail;
    }

    @Override
    public String toString() {
        return "{\"idOrder\": \"" + idOrder + "\",\"idUser\": \"" + idUser + "\",\"description\": \"" + description
                + "\",\"totalValue\": \"" + totalValue + "\",\"ordersDate\": \"" + ordersDate 
                + "\",\"status\": \"" + status + "\",\"statusEmail\": \"" + statusEmail + "\"}";
    }
}
