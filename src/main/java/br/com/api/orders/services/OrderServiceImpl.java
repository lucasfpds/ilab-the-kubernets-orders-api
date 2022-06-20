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
import br.com.api.orders.util.GetAdminEmail;
import br.com.api.orders.util.GetUserEmail;
import br.com.api.orders.util.Users;

@Component
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrdersDAO dao;

    @Override
    public Order createOrder(Order newOrder) throws Exception {

        if (checkExistOrder(newOrder)) {
            Date date = new Date();
            Timestamp orderTimeStamp = new Timestamp(date.getTime());

            newOrder.setIdAdmin(1);

            new GetUserEmail();
            Users user = GetUserEmail.userExist(newOrder);

            // new GetAdminEmail();
            // String emailAdmin = GetAdminEmail.adminExist(newOrder);

            // OrderDTO orderDto = new OrderDTO(newOrder.getIdAdmin(), emailAdmin,
            // newOrder.getIdUser(), user.getName(), user.getEmail(),
            // newOrder.getDescription(), newOrder.getTotalValue(), orderTimeStamp);

            OrderDTO orderDto = new OrderDTO(newOrder.getIdAdmin(), "thekubernetes4@gmail.com", newOrder.getIdUser(),
                    user.getName(), user.getEmail(),
                    newOrder.getDescription(), newOrder.getTotalValue(), orderTimeStamp);

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                    .create();

            String jsonString = gson.toJson(orderDto);

            SQSServiceProducer.sendMessageProducer(jsonString);

            try {
                OrderDTO orderComplete = orderDto;

                Integer count = 0;

                do {
                    orderComplete = SQSServiceReader.messageReader(orderDto.getIdAdmin().toString());

                    count++;
                } while (orderComplete == null && count <= 5);

                count = 0;

                Order orderFinalizado = new Order(orderComplete.getIdUser(), orderComplete.getIdAdmin(),
                        orderComplete.getDescription(),
                        orderComplete.getTotalValue(), orderComplete.getOrdersDate(),
                        orderComplete.getStatus(), orderComplete.getStatusEmail());

                if (orderFinalizado.getStatus().equals("aberto")) {
                    throw new Exception("{\"message\":\"O pedido não foi finalizado com sucesso.\"}");
                }

                dao.save(orderFinalizado);

                return orderFinalizado;

            } catch (Exception e) {
                throw new Exception("{\"message\":\"" + e.getMessage() + "\"}");
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
