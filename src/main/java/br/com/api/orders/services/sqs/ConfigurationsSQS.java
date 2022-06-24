package br.com.api.orders.services.sqs;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;

public class ConfigurationsSQS {
    static SqsClient getSqsClient() { 
        
        SqsClient sqsClient = SqsClient.builder()
            .region(Region.US_EAST_1)
            .credentialsProvider(AuthenticationSQS.getCredentials())
            .build();
        
        return sqsClient;
    }

    static GetQueueUrlRequest getUrlRequestSend() {
        GetQueueUrlRequest request = GetQueueUrlRequest.builder()
                .queueName("queue-grupo4").build();
        return request;
    }

    static GetQueueUrlRequest getUrlRequestReceive() {
        GetQueueUrlRequest request = GetQueueUrlRequest.builder()
                .queueName("queue-grupo4-order").build();
        return request;
    }

    static GetQueueUrlResponse getCreateResultSend() {
        GetQueueUrlResponse createResult = getSqsClient().getQueueUrl(getUrlRequestSend());
    
        return createResult;
    }

    static GetQueueUrlResponse getCreateResultReceive() {
        GetQueueUrlResponse createResult = getSqsClient().getQueueUrl(getUrlRequestReceive());
    
        return createResult;
    }
}
