package br.com.api.orders.OrderServiceTest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.api.orders.dao.OrdersDAO;
import br.com.api.orders.model.Order;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderServiceTest {

    @Autowired
    private OrdersDAO ordersDAO;

    Date date;

    @Test
    public void createOrderTestWithOrderModel(){
       date = new Date();
        Order order = new Order(2,1, "pão com rapadura", 
                        250000,new Timestamp(date.getTime()) , 
                        "aberto", "não enviado");

        ordersDAO.save(order);

        Assertions.assertThat(order.getIdOrder()).isNotNull();
        Assertions.assertThat(order.getIdUser()).isEqualTo(2);
        Assertions.assertThat(order.getDescription()).isEqualTo("pão com rapadura");
        Assertions.assertThat(order.getTotalValue()).isEqualTo(250000);
        Assertions.assertThat(order.getOrdersDate()).isEqualTo(new Timestamp(date.getTime()));
        Assertions.assertThat(order.getStatus()).isEqualTo("aberto");
        Assertions.assertThat(order.getStatusEmail()).isEqualTo("não enviado");


    }

   @Test
   public void listAllOrderTest(){
    date = new Date();
    Order orderFirst = new Order(2,1, "pão com rapadura", 
                    250000,new Timestamp(date.getTime()) , 
                    "aberto", "não enviado");
    
    
    Order orderSecond = new Order(3,1, "pão com feijão", 
                                    300000,new Timestamp(date.getTime()) , 
                                    "finalizado", "enviado"); 
                                    
    ordersDAO.save(orderFirst);
    ordersDAO.save(orderSecond);

    List<Order> ordersList = new ArrayList();

    for (Order order : ordersDAO.findAll()) {
        
        ordersList.add(order);
    }
    
    Assertions.assertThat(ordersList.size()).isEqualTo(2);
   }
}
