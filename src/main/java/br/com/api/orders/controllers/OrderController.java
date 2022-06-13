package br.com.api.orders.controllers;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.orders.model.Order;
import br.com.api.orders.services.IOrderService;

@RestController
public class OrderController<Json> {
    @Autowired
    private IOrderService iservice;

    Gson gson = new Gson();

    @PostMapping("/create-order")
    public ResponseEntity<?> createNewOrder(@RequestBody Order newOrder) throws Exception {
        try {
            final Order order = iservice.createOrder(newOrder);
            return ResponseEntity.status(201).body(order);
        
        } catch (Exception e) {
            Object error = gson.fromJson(e.getMessage(), Object.class);

            return ResponseEntity.status(400).body(gson.toJson(error));
        }
    }

    @GetMapping("orders")
    public ResponseEntity<?> readOrders() {
        return ResponseEntity.status(200).body(iservice.readOrders());
    }

    @GetMapping("order/{id}")
    public ResponseEntity<?> readOrder(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(iservice.readOrderById(id));
    }
}