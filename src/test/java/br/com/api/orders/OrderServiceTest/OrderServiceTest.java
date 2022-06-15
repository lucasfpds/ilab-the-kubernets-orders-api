package br.com.api.orders.OrderServiceTest;

import java.sql.Timestamp;

import java.util.Date;


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
       System.out.println(date);
        Order order = new Order(2, "pão com rapadura", 
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

    
}
