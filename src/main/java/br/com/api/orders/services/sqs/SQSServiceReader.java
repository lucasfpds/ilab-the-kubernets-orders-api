package br.com.api.orders.services.sqs;

import java.util.List;

import com.google.gson.Gson;

import br.com.api.orders.model.Order;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.Message;

public class SQSServiceReader {
    public static Order messageReader(String status) throws Exception {
        SqsClient sqsClient = ConfigurationsSQS.getSqsClient();
        GetQueueUrlResponse createResult = ConfigurationsSQS.getCreateResult();

        List<Message> messages = ReceiveMessage.receiveMessages(sqsClient, createResult.queueUrl());

        for (Message msg : messages) {
            String stringMessage = msg.body();
            
            Order jsonPedido = new Gson().fromJson(stringMessage, Order.class);

            if(jsonPedido.getStatus().equals("aberto")) {
                throw new Exception("{\"error\":\"O pedido não foi concluído com sucesso.\"}");
            }

            return jsonPedido;
        }

        sqsClient.close();
        return null;
    }
}
