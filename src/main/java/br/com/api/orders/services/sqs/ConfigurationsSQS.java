package br.com.api.orders.services.sqs;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.*;

public class ConfigurationsSQS {
    static SqsClient getSqsClient() { 
        
        SqsClient sqsClient = SqsClient.builder()
            .region(Region.US_EAST_1)
            .credentialsProvider(AuthenticationSQS.getCredentials())
            .build();
        
        return sqsClient;
    }

    static GetQueueUrlRequest getUrlRequest() {
        GetQueueUrlRequest request = GetQueueUrlRequest.builder()
                .queueName("queue-grupo4").build();
        return request;
    }

    static GetQueueUrlResponse getCreateResult() {
        GetQueueUrlResponse createResult = getSqsClient().getQueueUrl(getUrlRequest());
    
        return createResult;
    }
}
