package com.queues.quickstart;

import com.azure.identity.*;
import com.azure.storage.queue.*;
import com.azure.storage.queue.models.*;
import java.io.*;
import java.time.Duration;

public class App 
{
    public static void main( String[] args )
    {
        int number = 0;

        while(number == 0) {
            //Unique name for the queue
            String queueName = "quickstartqueues-test1";//+ java.util.UUID.randomUUID();

            QueueClient queueClient = Access(queueName);
            //CreateQueue(queueClient, queueName);
            //SendMessageResult result = AddMessages(queueClient);
            //PeekMessages(queueClient);
            //UpdateMessage(queueClient, result);
            long totalMessages = GetQueueLength(queueClient);
            System.out.println(String.format("Mensajes totales: %d", totalMessages));

            if (totalMessages > 0)
                ReceiveandDeleteMessages(queueClient);
            else
                System.out.println("No messages in the queue.");
        }
        //DeleteQueue(queueClient);
    }

    static QueueClient Access(String queueName){
        // Instantiate a QueueClient
        // We'll use this client object to create and interact with the queue
        // TODO: replace <storage-account-name> with the actual name
        String connectionString = "DefaultEndpointsProtocol=https;AccountName=proyectofinalmisw;"
                + "AccountKey=CXWn/WVFoVVVJ3MbD3HuePLgI1ACNP4YHoiKTXCPlASn2IBiDGolAJaH5cOB5tG2K3NyXOtFOqdA+ASt6eR2Yg==;EndpointSuffix=core.windows.net";

        QueueClient queueClient = new QueueClientBuilder()
                .connectionString(connectionString)
                .queueName(queueName)
                .buildClient();

        return queueClient;
    }

    static void CreateQueue(QueueClient queueClient, String queueName){
        System.out.println("Creating queue: " + queueName);

        // Create the queue
        queueClient.create();
    }

    static SendMessageResult AddMessages(QueueClient queueClient){
        System.out.println("\nAdding messages to the queue...");

        // Send several messages to the queue
        queueClient.sendMessage("First message");
        queueClient.sendMessage("Second message");

        // Save the result so we can update this message later
        SendMessageResult result = queueClient.sendMessage("Third message");

        return result;
    }

    static void PeekMessages(QueueClient queueClient){
        System.out.println("\nPeek at the messages in the queue...");

        // Peek at messages in the queue
        queueClient.peekMessages(1, null, null).forEach(
                peekedMessage -> System.out.println("Message: " + peekedMessage.getMessageText()));
    }

    static void UpdateMessage(QueueClient queueClient, SendMessageResult result){
        System.out.println("\nUpdating the third message in the queue...");

        // Update a message using the result that
        // was saved when sending the message
        queueClient.updateMessage(result.getMessageId(),
                result.getPopReceipt(),
                "Third message has been updated",
                Duration.ofSeconds(1));
    }

    static long GetQueueLength(QueueClient queueClient){
        QueueProperties properties = queueClient.getProperties();
        long messageCount = properties.getApproximateMessagesCount();

        System.out.println(String.format("Queue length: %d", messageCount));
        return messageCount;
    }

    static void ReceiveandDeleteMessages(QueueClient queueClient){
        // Get messages from the queue
        queueClient.receiveMessages(1).forEach(
                // "Process" the message
                receivedMessage -> {
                    try {
                        System.out.println("Message: " + receivedMessage.getMessageText());
                        // Let the service know we're finished with
                        //Error intencional
                        //int i = 1/0;

                        try {
                            Thread.sleep(6000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        // the messageand it can be safely deleted.
                        queueClient.deleteMessage(receivedMessage.getMessageId(), receivedMessage.getPopReceipt());
                        //Error intencionado 2
                        //queueClient.deleteMessage(String.valueOf(-10), receivedMessage.getPopReceipt());
                    }
                    catch (Exception ex){
                        System.out.println("Ocurrio un error: " + ex.getMessage());
                    }
                }
        );
    }

    static void DeleteQueue(QueueClient queueClient){
        System.out.println("\nPress Enter key to delete the queue...");
        System.console().readLine();

        // Clean up
        System.out.println("Deleting queue: " + queueClient.getQueueName());
        queueClient.delete();

        System.out.println("Done");
    }
}
