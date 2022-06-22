package br.com.api.orders.ControllerTest;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.api.orders.dao.OrdersDAO;
import br.com.api.orders.model.Order;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class OrderControllerTest {

    @MockBean
    private OrdersDAO ordersDAO;

    private String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0ZV9hb192aXZvQHRlc3RlLmNvbSIsImlzcyI6InRoZUt1YmVybmV0ZXNBUEkiLCJleHAiOjE2NTU4MzA5Njl9.q0Kdq_fdI2fZkw3G0u0dni9wvFbpqAwwY7ZR2QRdDz4";
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void createdOrderReturnStatus201() throws Exception{

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
        
        Date date = new Date();
        Timestamp orderTimeStamp = new Timestamp(date.getTime());
        Order order = new Order(2,1, "pão com rapadura", 
                        250000,orderTimeStamp , 
                        "aberto", "não enviado");

                        
                        Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                    .create();
                        
            String newOrder = gson.toJson(order);

        mockMvc.perform(MockMvcRequestBuilders.post("/create-order")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .characterEncoding("UTF-8")
                                                .headers(headers)
                                                .content(newOrder)
                                                ).andExpect(MockMvcResultMatchers.status().isBadRequest());
        
         
       
       
    }
    @Test
    public void readOrdersWithStatusCode200() throws Exception{
       
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);
       
        
        Date date = new Date();
        List<Order> orders = Arrays.asList(new Order(2,1, "pão com rapadura", 
                        250000,new Timestamp(date.getTime())),new Order(3,1, "pão com rapadura", 
                        250000,new Timestamp(date.getTime())));

        BDDMockito.when(ordersDAO.findAll()).thenReturn((List<Order>) orders);  

        mockMvc.perform(MockMvcRequestBuilders.get("/orders")
                                             .contentType(MediaType.APPLICATION_JSON)
                                             .characterEncoding("UTF-8")
                                             .headers(headers)
                                             ).andExpect((MockMvcResultMatchers.status().isOk())
                                             );                                                                
    }
}
