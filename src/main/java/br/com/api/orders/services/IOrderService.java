package br.com.api.orders.services;

import java.util.List;

import org.springframework.http.HttpHeaders;

import br.com.api.orders.model.Order;

public interface IOrderService {
    public Order createOrder(Order newOrder, HttpHeaders headers) throws Exception;

    public List<Order> readOrders();
}
