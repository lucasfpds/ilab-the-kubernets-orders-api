package br.com.api.orders.dao;

import org.springframework.data.repository.CrudRepository;

import br.com.api.orders.model.Order;

public interface OrdersDAO extends CrudRepository<Order, Integer> {
    
}
