package br.com.api.orders.ControllerTest;


import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import org.assertj.core.api.Assertions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import br.com.api.orders.dao.OrdersDAO;
import br.com.api.orders.model.Order;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class OrderControllerTest {
    
    @Autowired
    private TestRestTemplate template;

    @MockBean
    private OrdersDAO ordersDAO;

    
    @Before
    public void configProtectedHeaders(){
         
    }

    @Test
    public void createdOrderReturnStatus201() {

       Date date = new Date();
        Order order = new Order(2, "pão com rapadura", 
                        250000,new Timestamp(date.getTime()) , 
                        "aberto", "não enviado");
         BDDMockito.when(ordersDAO.save(order)).thenReturn(order);
         ResponseEntity<String> responseEntity = template.postForEntity("/create-order",order, String.class);
         Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200); 
        // Assertions.assertThat(responseEntity.hasBody()).isTrue(); 
         
       
       
    }
    @Test
    public void readOrdersWithStatusCode200(){
        Date date = new Date();
        List<Order> orders = Arrays.asList(new Order(2, "pão com rapadura", 
                        250000,new Timestamp(date.getTime()) , 
                        "aberto", "não enviado"),new Order(3, "pão com rapadura", 
                        250000,new Timestamp(date.getTime()) , 
                        "aberto", "não enviado"));

        BDDMockito.when(ordersDAO.findAll()).thenReturn((List<Order>) orders);  
        
        ResponseEntity<String> responseEntity = template.getForEntity("/orders", String.class);
        Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }

}
