package br.com.api.orders.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.api.orders.dao.OrdersDAO;
import br.com.api.orders.dto.OrderDTO;
import br.com.api.orders.model.Order;
import br.com.api.orders.services.sqs.SQSServiceProducer;
import br.com.api.orders.services.sqs.SQSServiceReader;
import br.com.api.orders.util.GetUserEmail;
import br.com.api.orders.util.Users;


@Component
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrdersDAO dao;

    @Override
    public Order createOrder(Order newOrder) throws Exception {

        if(checkExistOrder(newOrder)) {
            Date date = new Date(); 
            Timestamp orderTimeStamp = new Timestamp(date.getTime());

            new GetUserEmail();
            Users user = GetUserEmail.userExist(newOrder);

            OrderDTO orderDto = new OrderDTO(2, newOrder.getIdUser(), user.getName(), user.getEmail(),
                    newOrder.getDescription(), newOrder.getTotalValue(), orderTimeStamp);
           
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                    .create();

            String jsonString = gson.toJson(orderDto);

            SQSServiceProducer.sendMessageProducer(jsonString);
            
            try {
                OrderDTO orderComplete = orderDto;

                do {
                    orderComplete = SQSServiceReader.messageReader(orderDto.getIdAdmin().toString());
                } while(orderComplete == null);
    
                Order orderFinalizado = new Order(orderComplete.getIdUser(), orderComplete.getDescription(), 
                                                    orderComplete.getTotalValue(), orderComplete.getOrdersDate(), 
                                                    orderComplete.getStatus(), orderComplete.getStatusEmail());
    
                dao.save(orderFinalizado);
    
                return orderFinalizado;
                
            } catch (Exception e) {
                throw new Exception("{\"message\":\"" +e.getMessage()+ "\"}"); //TO-DO: mudar a mensagem para email não enviado
            }
        } 

        throw new Exception("{\"message\":\"Bad Request\"}");
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

    private boolean checkExistOrder(Order order) {
        if (order.getIdUser() != null && order.getDescription() != null && order.getTotalValue() != null) {            
            return true;
        }

        return false;
    }
}
