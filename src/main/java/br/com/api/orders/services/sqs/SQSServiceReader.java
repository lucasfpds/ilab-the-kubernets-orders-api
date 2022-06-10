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
        GetQueueUrlResponse createResult = ConfigurationsSQS.getCreateResult();

        List<Message> messages = ReceiveMessage.receiveMessages(sqsClient, createResult.queueUrl());

        for (Message msg : messages) {
            String stringMessage = msg.body();
            
            OrderDTO jsonPedido = new Gson().fromJson(stringMessage, OrderDTO.class);

            if(jsonPedido.getStatus().equals("aberto")) {
                throw new Exception("{\"error\":\"O pedido não foi concluído com sucesso.\"}");
            }

            return jsonPedido;
        }

        sqsClient.close();
        return null;
    }
}
