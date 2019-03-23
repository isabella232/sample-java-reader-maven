package io.kafkaesque;

import org.apache.pulsar.client.api.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class simpleReader {

    private static final String SERVICE_URL = "pulsar://asiaeast2.gcp.kafkaesque.io:6650";

    public static void main(String[] args) throws IOException {

        // Create client object
        PulsarClient client = PulsarClient.builder()
                .serviceUrl(SERVICE_URL)
                .build();

        // Create a reader on a topic starting at the earliest retained message
        // No subscription is necessary. Depending on retention policy, the
        // earliest message may be days old
        Reader<byte[]> reader = client.newReader()
                .topic("mytenant2/local-asiaeast2-gcp/test-topic")
                .startMessageId(MessageId.earliest)
                .create();

        boolean receivedMsg = false;
        // Loop until a message is received
        do {
            // Block for up to 1 second for a message
            Message msg = reader.readNext(1, TimeUnit.SECONDS);

            if(msg != null){
                System.out.printf("Message received: %s%n",  new String(msg.getData()));

                receivedMsg = true;
            }

        } while (!receivedMsg);

        //Close the reader
        reader.close();

        // Close the client
        client.close();

    }
}
