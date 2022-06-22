package br.com.api.orders.services.sqs;

import java.util.List;

import com.google.gson.Gson;

import br.com.api.orders.dto.OrderDTO;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.MessageAttributeValue;

public class SQSServiceReader {
    public static OrderDTO messageReader(String idAdmin) throws Exception {
        SqsClient sqsClient = ConfigurationsSQS.getSqsClient();
        GetQueueUrlResponse createResultReceive = ConfigurationsSQS.getCreateResultReceive();
        
        try {
            List<Message> messages = ReceiveMessage.receiveMessages(sqsClient, createResultReceive.queueUrl());
            
            for (Message msg : messages) {
                String stringMessage = msg.body(); 
                MessageAttributeValue stringAttribute = msg.messageAttributes().get("Code");
                
                String attribute = stringAttribute.stringValue();
                
                if(attribute.equals(idAdmin)) {
                    DeleteMessage.deleteMessages(sqsClient, createResultReceive.queueUrl(), msg);   

                    OrderDTO jsonPedido = new Gson().fromJson(stringMessage, OrderDTO.class);
                    
                    sqsClient.close();
                    
                    return jsonPedido;                
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        
        return null;
    }
}
