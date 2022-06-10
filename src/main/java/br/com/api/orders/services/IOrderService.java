package br.com.api.orders.services;

import java.util.List;
import java.util.Optional;

import br.com.api.orders.model.Order;

public interface IOrderService {
    public Order createOrder(Order newOrder) throws Exception;
    public List<Order> readOrders();
    public Optional<Order> readOrderById(Integer id);
}
