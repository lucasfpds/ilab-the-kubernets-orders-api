package br.com.api.orders.services.sqs;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;

public class SQSServiceProducer {
    public static void sendMessageProducer(String order) {
        SqsClient sqsClient = ConfigurationsSQS.getSqsClient();
        GetQueueUrlResponse createResult = ConfigurationsSQS.getCreateResult();               
        
        SendMessage.sendMessage(sqsClient, createResult.queueUrl(), order);        

        sqsClient.close();
    }
}