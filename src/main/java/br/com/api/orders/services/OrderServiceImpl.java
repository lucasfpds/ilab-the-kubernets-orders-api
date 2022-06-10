package br.com.api.orders.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.api.orders.dao.OrdersDAO;
import br.com.api.orders.model.Order;

@Component
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrdersDAO dao;

    @Override
    public Order createOrder(Order newOrder) throws Exception {

        if(checkExistOrder(newOrder)) {
            Order order = new Order(newOrder.getIdUser(), 
                                    newOrder.getDescription(), newOrder.getTotalValue(), new Date());

            
            dao.save(order);
            return order;
        } 

        throw new Exception("{\"error\":\"Bad Request\"}");
    }

    @Override
    public List<Order> readOrders() {
        try {
            return (List<Order>) dao.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<Order> readOrderById(Integer id) {
        try {
            return dao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean checkExistOrder(Order order) {
        if (order.getIdUser() != null && order.getDescription() != null && order.getTotalValue() != null) {            
            return true;
        }

        return false;
    }
}
