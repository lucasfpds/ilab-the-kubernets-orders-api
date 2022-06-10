package br.com.api.orders.dto;

import java.sql.Timestamp;

public class OrderDTO {
    private Integer idUser;
    private String emailUser;
    private String description;
    private Integer totalValue;
    private Timestamp ordersDate;
    private String status = "aberto";
    
    public OrderDTO(Integer idUser, String emailUser, String description, Integer totalValue,
            Timestamp ordersDate) {
        this.idUser = idUser;
        this.emailUser = emailUser;
        this.description = description;
        this.totalValue = totalValue;
        this.ordersDate = ordersDate;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
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
}
