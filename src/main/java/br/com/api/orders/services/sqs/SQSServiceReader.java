package br.com.api.orders.services.sqs;

import java.util.List;

import com.google.gson.Gson;

import br.com.api.orders.dto.OrderDTO;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.Message;

public class SQSServiceReader {
    public static OrderDTO messageReader() throws Exception {
        SqsClient sqsClient = ConfigurationsSQS.getSqsClient();
        GetQueueUrlResponse createResult = ConfigurationsSQS.getCreateResultReceive();

        List<Message> messages = ReceiveMessage.receiveMessages(sqsClient, createResult.queueUrl());
        
        for (Message msg : messages) {
            String stringMessage = msg.body(); 
            DeleteMessage.deleteMessages(sqsClient, createResult.queueUrl(), msg);   

            try {
                OrderDTO jsonPedido = new Gson().fromJson(stringMessage, OrderDTO.class);
                
                if(jsonPedido.getStatus().equals("aberto")) {
                    throw new Exception("O pedido não foi concluído com sucesso.");
                }
                
                sqsClient.close();
                return jsonPedido;
                
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
        
        return null;
    }
}
