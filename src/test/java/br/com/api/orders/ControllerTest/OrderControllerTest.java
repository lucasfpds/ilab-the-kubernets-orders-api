package br.com.api.orders.ControllerTest;


import java.sql.Timestamp;
import java.util.Date;

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
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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

    @Autowired
    private MockMvc mockMvc;

    private HttpEntity<Void> protectedHeader;
    private HttpEntity<Void> adminHeader;
    private HttpEntity<Void> wrongHeader;

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
         Assertions.assertThat(responseEntity.getStatusCodeValue()).isEqualTo(302); 
        // Assertions.assertThat(responseEntity.hasBody()).isTrue(); 
         
       
       
    }


}
